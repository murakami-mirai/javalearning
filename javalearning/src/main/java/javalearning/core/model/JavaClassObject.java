package javalearning.core.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class JavaClassObject extends SimpleJavaFileObject {

	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private Class<?> clazz = null;
	
	public JavaClassObject(String name, Kind kind) {
		super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
	}
	
	@Override
	public OutputStream openOutputStream() throws IOException {
		return bos;
	}
	
	public byte[] getBytes() {
		return bos.toByteArray();
	}
	
	public void setDefinedClass(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public Class<?> getDefinedClass() {
		return clazz;
	}
}
