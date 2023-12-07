package javalearning.gui.frame.panel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorPanel extends AbstractInputPanel {

	private final RSyntaxTextArea textArea;
	private final RTextScrollPane subPanel;
	
	public EditorPanel() {
		textArea = new RSyntaxTextArea(20,100);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		subPanel = new RTextScrollPane(textArea);
	}

	@Override
	public void create() {
		add(subPanel);
	}

	@Override
	public String getInputText() {
		return textArea.getText();
	}
	
	public void setInputText(String text) {
		textArea.setText(text);
	}

	@Override
	protected GridSetting getGridSetting() {
		return new GridSetting(0, 1, 1, 2);
	}
}
