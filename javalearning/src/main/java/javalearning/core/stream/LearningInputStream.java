package javalearning.core.stream;

import java.io.IOException;
import java.io.InputStream;

public class LearningInputStream extends InputStream {

	/** 文字列 */
	private byte[] characters;
	private int charPos;

	public LearningInputStream(String input) {
		characters = input.getBytes();
		reset();
	}
	
	@Override
	public int read() throws IOException {

		// 文字列に次の文字が存在する場合、その文字列を返却
		if (isNext()) {
			return characters[charPos];
		}
		return -1;
		
	}
	
	@Override
	public boolean markSupported() {
		return false;
	}
	
	@Override
	public void reset() {
		charPos = -1;
	}
	
	@Override
	public void close() {
		reset();
	}
	
	public String getCharacters() {
		return new String(characters);
	}
	
	private boolean isNext() {
		charPos++;
		// inputが見ていない場合
		if (charPos < characters.length) {
			return true;
		}
		return false;
	}
}
