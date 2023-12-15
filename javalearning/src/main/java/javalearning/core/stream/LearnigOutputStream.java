package javalearning.core.stream;

import java.io.ByteArrayOutputStream;

import javalearning.core.ui.panel.OutputPanel;

public class LearnigOutputStream extends ByteArrayOutputStream {

//	private final OutputPanel panel;
	private final OutputPanel panel;
	
	public LearnigOutputStream(OutputPanel panel) {
		super();
		this.panel = panel;
	}
	
	public OutputPanel getPanel() {
		return panel;
	}	
	
//	public LearnigOutputStream(OutputPanel panel) {
//		super();
//		this.panel = panel;
//	}
//	
//	public OutputPanel getPanel() {
//		return panel;
//	}
}
