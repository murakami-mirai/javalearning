package javalearning;

import java.io.ByteArrayOutputStream;

import javalearning.gui.MainFrame;
import javalearning.learning.core.LearningPrintStream;

public class Main {

	public static void main(String[] args) {
		// 標準出力先を変更
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		LearningPrintStream printSteam = new LearningPrintStream(outputStream);
		System.setOut(printSteam);
		new MainFrame().doProcess();
	}

}
