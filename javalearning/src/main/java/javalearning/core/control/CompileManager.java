package javalearning.core.control;

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

	private DiagnosticListener<? super JavaFileObject> listener  = new ErrorListener();
	private JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	
	public <T> Class<T> compile(String class_name, String source_code) {
		JavaFileObject fo = new JavaSourceCode(class_name, source_code);

		List<JavaFileObject> compilationUnits = Arrays.asList(fo);
		List<String> options = Arrays.asList(
					"-classpath", System.getProperty("java.class.path")
				);
		JavaFileManager manager = new ClassFileManager(compiler, listener);
		CompilationTask task = compiler.getTask(
						null,
						manager,	//出力ファイルを扱うマネージャー
						listener,	//エラー時の処理を行うリスナー（nullでもよい）
						options,	//コンパイルオプション
						null,
						compilationUnits	//コンパイル対象ファイル群
					);

		//コンパイル実行
		boolean successCompile = task.call();
		if (!successCompile) {
			throw new RuntimeException("コンパイル失敗：" + class_name);
		}

		ClassLoader cl = manager.getClassLoader(null);
		try {
			@SuppressWarnings("unchecked")
			Class<T> c = (Class<T>)cl.loadClass(class_name);
			return c;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
