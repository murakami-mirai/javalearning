package javalearning.core.control;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import javalearning.core.listener.ErrorListener;
import javalearning.core.model.JavaSourceCode;
import javalearning.gui.MainFrame;
import javalearning.learning.core.ILearning;

public class CompileManager {

	private final DiagnosticListener<? super JavaFileObject> listener  = new ErrorListener();
	private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	private final List<Exception> errorList;
	
	public CompileManager() {
		errorList = new ArrayList<>();
	}
	
	public void run(String src) {
		CompileManager manager = new CompileManager();
		Class<ILearning> c = manager.compile("sample.string.Sample3", src);
		
		// コンパイエラーが存在する場合、実行しない
		if (c == null || hasError()) {
			return;
		}

		// インスタンスを生成して呼び出す
		ILearning s = null;
		try {
			s = c.getDeclaredConstructor().newInstance();
		} 
		catch (InstantiationException e) {errorList.add(e);} 
		catch (IllegalAccessException e) {errorList.add(e);} 
		catch (IllegalArgumentException e) {errorList.add(e);} 
		catch (InvocationTargetException e) {errorList.add(e);} 
		catch (NoSuchMethodException e) {errorList.add(e);} 
		catch (SecurityException e) {errorList.add(e);}
		System.out.println(s.getValue());
		
		new MainFrame().doProcess();
	}
	
	private <T> Class<T> compile(String className, String sourceCode) {
		JavaFileObject fileObject = new JavaSourceCode(className, sourceCode);

		// コンパイル対象
		List<JavaFileObject> compilationUnits = Arrays.asList(fileObject);
		// コンパイラーオプション
		List<String> options = Arrays.asList(
			"-classpath", System.getProperty("java.class.path")
		);
		// クラスファイルの出力設定
		JavaFileManager manager = new ClassFileManager(compiler, listener);
		CompilationTask task = compiler.getTask(
						null,
						manager,
						listener,
						options,
						null,
						compilationUnits
					);

		//コンパイル実行
		boolean successCompile = task.call();
		if (!successCompile) {
			errorList.add(new RuntimeException("コンパイル失敗：" + className));
		}

		ClassLoader cl = manager.getClassLoader(null);
		try {
			@SuppressWarnings("unchecked")
			Class<T> c = (Class<T>)cl.loadClass(className);
			return c;
		} catch (ClassNotFoundException e) {
			errorList.add(e);
			return null;
		}
	}
	
	public boolean hasError() {
		return !errorList.isEmpty();
	}
}
