package javalearning.gui.frame.panel;

import javalearning.gui.grid.GridManager;

public abstract class AbstractInputPanel extends AbstractBasePanel {

	public AbstractInputPanel(GridManager gridManager) {
		super(gridManager);
	}

	public abstract String getInputText();
}
