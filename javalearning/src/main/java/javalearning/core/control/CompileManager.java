package javalearning.core.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

public class CompileManager {

	private final DiagnosticListener<? super JavaFileObject> listener  = new ErrorListener();
	private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	private final List<Exception> errorList;
	private final String[] mainParams;
	
	public CompileManager() {
		errorList = new ArrayList<>();
		this.mainParams = new String[0];
	}
	public CompileManager(String... mainParams) {
		errorList = new ArrayList<>();
		this.mainParams = mainParams;
	}
	
	public void run(String src) {
		Class<?> c = this.compile("Main", src);
		// コンパイエラーが存在する場合、実行しない
		if (c == null || hasError()) {
			errorList.stream().forEach(e -> {
				e.printStackTrace();
			});
			return;
		}

		// インスタンスを生成して呼び出す
		try {
			Method main = c.getMethod("main", String[].class);
			main.invoke(c, new Object[] {mainParams});
			System.out.println("実行できました!");
		} 
		catch (IllegalArgumentException e) {errorList.add(e);} 
		catch (NoSuchMethodException e) {errorList.add(e);} 
		catch (SecurityException e) {errorList.add(e);} 
		catch (IllegalAccessException e) {errorList.add(e);} 
		catch (InvocationTargetException e) {errorList.add(e);}
		
		if(hasError()) {
			errorList.stream().forEach(e -> {
				e.printStackTrace();
			});
		}
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
