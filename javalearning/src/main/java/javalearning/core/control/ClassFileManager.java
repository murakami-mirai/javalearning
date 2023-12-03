package javalearning.core.control;

import java.io.IOException;
import java.security.SecureClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

import javalearning.core.model.JavaClassObject;

public class ClassFileManager extends ForwardingJavaFileManager<JavaFileManager> {

	private final Map<String, JavaClassObject> map = new HashMap<String, JavaClassObject>();
	private ClassLoader loader = null;
	
	public ClassFileManager(JavaCompiler compiler, DiagnosticListener<? super JavaFileObject> listener) {
		super(compiler.getStandardFileManager(listener, null, null));
	}
	
	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) throws IOException {
		JavaClassObject co = new JavaClassObject(className, kind);
		map.put(className, co); // クラス名をキーにしてファイルオブジェクトを保持しておく
		return co;
	}
	
	@Override
	public ClassLoader getClassLoader(Location location) {
		if (loader == null) {
			loader = new Loader();
		}
		return loader;
	}
	
	private class Loader extends SecureClassLoader {

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			JavaClassObject classObject = map.get(name);
			if (classObject == null) {
				return super.findClass(name);
			}

			Class<?> clazz = classObject.getDefinedClass();
			if (clazz == null) {
				byte[] bytes = classObject.getBytes();
				clazz = super.defineClass(name, bytes, 0, bytes.length);
				classObject.setDefinedClass(clazz);
			}
			return clazz;
		}
	}
}
