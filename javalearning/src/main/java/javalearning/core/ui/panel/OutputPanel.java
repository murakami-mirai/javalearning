package javalearning.core.ui.panel;

import javax.swing.JScrollPane;

import org.fife.ui.rtextarea.RTextArea;

public class OutputPanel extends AbstractScrollBasePanel {
	private final RTextArea textArea;
	private final JScrollPane subPanel;
	
	public OutputPanel() {
		textArea = new RTextArea(10, 105);
		textArea.setHighlightCurrentLine(false);
		textArea.setEditable(false);
		subPanel = new JScrollPane(textArea);
	}
	
	public synchronized void appendText(String text) {
		if (text == null) {
			text = "";
		}
		textArea.append(text);
	}
	
	public synchronized void resetText() {
		int length = textArea.getText().length();
		textArea.replaceRange("", 0, length);
	}
	
	public String getText() {
		String text = textArea.getText();
		if (text == null) {
			text = "";
		}
		return text;
	}
	
	public void create() {
		this.add(subPanel);
	}

	@Override
	protected GridSetting getGridSetting() {
		return new GridSetting(0, 0, 1, 2);
	}
}
