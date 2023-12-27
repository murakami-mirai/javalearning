package javalearning.core.stream;

import java.io.IOException;
import java.io.InputStream;

public class LearningInputStream extends InputStream {

	public static final byte[] END = null;
	
	private final String[] inputPrams;
	private byte[] currentParams;
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
	
	private boolean isNext() {
		if (currentBytePos < currentParams.length) {
			return true;
		}
		if (currentParamPos < inputPrams.length) {
			currentParams = inputPrams[currentParamPos].getBytes();
			currentParamPos++;
			return true;
		}
		return false;
	}
	
	private void init() {
		if (currentParamPos < inputPrams.length
				|| inputPrams[currentParamPos] != null) {
			currentParams = inputPrams[currentParamPos].getBytes();
		}
	}
}
