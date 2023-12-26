package javalearning.core.stream;

import java.io.IOException;
import java.io.InputStream;

public class LearningInputStream extends InputStream {

	public static final byte[] END = null;
	
	private final String[] inputPrams;
	private byte[] currentParam;
	private int currentParamPos;
	private int currentBytePos;
	
	public LearningInputStream(String[] inputPrams) {
		this.inputPrams = inputPrams;
		currentParamPos = 0;
		currentBytePos = 0;
	}
	
	@Override
	public int read() throws IOException {
		
		return 0;
	}
	
	private boolean next() {
		if (currentBytePos)
	}
	
	private void init() {
		if (currentParamPos < inputPrams.length
				|| inputPrams[currentBytePos]) {
			currentParam = 
		}
	}
}
