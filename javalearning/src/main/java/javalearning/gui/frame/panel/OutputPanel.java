package javalearning.gui.frame.panel;

import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javalearning.gui.grid.GridManager;
import javalearning.gui.grid.GridSetting;

public class OutputPanel extends AbstractBasePanel {

	private final RTextScrollPane subPanel;
	private final RTextArea textArea;
	
	public OutputPanel(GridManager gridManager) {
		super(gridManager);
		textArea = new RTextArea(10, 105);
		textArea.setEditable(false);
		textArea.setHighlightCurrentLine(false);
		subPanel = new RTextScrollPane(textArea, false);
	}

	@Override
	protected void createPanel() {
		addNoSetLayout(subPanel, new GridSetting());
		getGridManager().addLine();
	}

	@Override
	public GridSetting getPanelSetting() {
		return new GridSetting();
	}

}
