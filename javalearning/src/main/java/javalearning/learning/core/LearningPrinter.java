package javalearning.learning.core;

import javalearning.gui.frame.panel.OutputPanel;
import javalearning.learning.core.steam.LearnigOutputStream;
import javalearning.learning.core.steam.LearningPrintStream;

public class LearningPrinter {
	
	private static LearningPrinter printer;
	
	public final LearningPrintStream OUT;
	
	private LearningPrinter(OutputPanel outputPanel) {
		// 標準出力先を変更
		LearnigOutputStream outputStream = new LearnigOutputStream(outputPanel);
		LearningPrintStream printSteam = new LearningPrintStream(outputStream);
		OUT = printSteam;
	}
	
	public static void initialize(OutputPanel outputPanel) {
		if (printer == null) {
			printer = new LearningPrinter(outputPanel);
		}
	}
	
	public static LearningPrinter printer() {
		return printer;
	}
}
