package javalearning.gui.frame.panel;

import java.awt.Font;

import javalearning.gui.grid.GridManager;
import javalearning.gui.grid.GridSetting;

public interface IPanel {
	void create();
	Font getFont();
	GridManager getGridManager();
	void setGridManager(GridManager gridManger);
	abstract GridSetting getPanelSetting();
}
