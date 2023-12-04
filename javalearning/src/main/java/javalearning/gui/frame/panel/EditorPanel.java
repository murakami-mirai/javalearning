package javalearning.gui.frame.panel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javalearning.gui.grid.GridManager;
import javalearning.gui.grid.GridSetting;

public class EditorPanel extends AbstractInputPanel {

	private final RSyntaxTextArea textArea;
	private final RTextScrollPane subPanel;
	
	public EditorPanel(GridManager gridManager) {
		super(gridManager);
		textArea = new RSyntaxTextArea(40,100);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		subPanel = new RTextScrollPane(textArea);
	}

	@Override
	protected void createPanel() {
		GridSetting setting = new GridSetting();
		addNoSetLayout(subPanel, setting);
		getGridManager().addLine();
	}

	@Override
	public GridSetting getPanelSetting() {
		return new GridSetting();
	}

	@Override
	public String getInputText() {
		return textArea.getText();
	}
}
