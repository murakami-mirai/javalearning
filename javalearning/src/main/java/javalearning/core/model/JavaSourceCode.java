package javalearning.core.model;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class JavaSourceCode extends SimpleJavaFileObject {

	private final String code;
	
	public JavaSourceCode(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}
	
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
	
	protected String getCode() {
		return code;
	}
}
