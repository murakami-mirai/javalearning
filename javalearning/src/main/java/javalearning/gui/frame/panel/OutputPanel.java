package javalearning.gui.frame.panel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputPanel extends AbstractScrollBasePanel {

	private final JScrollPane subPanel;
	private final JTextArea textArea;
	
	public OutputPanel() {
		textArea = new JTextArea(10, 105);
		textArea.setEditable(false);
		subPanel = new JScrollPane(textArea);
	}
	
	public void appendText(String text) {
		if (text == null) {
			text = "";
		}
		textArea.append(text);
	}
	
	public void resetText() {
		int length = textArea.getText().length();
		textArea.replaceRange("", 0, length);;
	}
	
	public String getText() {
		String text = textArea.getText();
		if (text == null) {
			text = "";
		}
		return text;
	}

	@Override
	public void create() {
		add(subPanel);
	}
	
	@Override
	protected GridSetting getGridSetting() {
		return new GridSetting(0, 3, 1, 2);
	}
}
