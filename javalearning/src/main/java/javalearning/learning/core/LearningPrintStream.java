package javalearning.learning.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class LearningPrintStream extends PrintStream {
	
	public LearningPrintStream(ByteArrayOutputStream out) {
		super(out);
	}
	
	public ByteArrayOutputStream getOutputStream() throws IOException {
		if (!(out instanceof ByteArrayOutputStream)) {
			throw new IOException();
		}
		return (ByteArrayOutputStream)out;
	}
}
