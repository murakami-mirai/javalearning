package javalearning.gui.frame.panel;

import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

public class OutputPanel extends AbstractScrollBasePanel {

	private final RTextScrollPane subPanel;
	private final RTextArea textArea;
	
	public OutputPanel() {
		textArea = new RTextArea(10, 105);
		textArea.setEditable(false);
		textArea.setHighlightCurrentLine(false);
		subPanel = new RTextScrollPane(textArea, false);
	}
	
	public void appendText(String text) {
		if (text == null) {
			text = "";
		}
		textArea.append(text);
	}
	
	public void resetText() {
		
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
