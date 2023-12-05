package javalearning.learning.core.steam;

import java.io.ByteArrayOutputStream;

import javalearning.gui.frame.panel.OutputPanel;

public class LearnigOutputStream extends ByteArrayOutputStream {

	private final OutputPanel panel;
	
	public LearnigOutputStream(OutputPanel panel) {
		super();
		this.panel = panel;
	}
	
	public OutputPanel getPanel() {
		return panel;
	}
}
