package javalearning.learning.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LearningPrintStream extends PrintStream {

	private final ByteArrayOutputStream out;
	
	public LearningPrintStream(ByteArrayOutputStream out) {
		super(out);
		this.out = out;
	}
	
	public String getString() {
		return new String(out.toByteArray());
	}

}
