package javalearning.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javalearning.gui.frame.AbstractBaseFrame;
import javalearning.gui.frame.panel.AbstractBasePanel;
import javalearning.gui.frame.panel.EditorPanel;

public class MainFrame extends AbstractBaseFrame {

	private final EditorPanel editorPanel;
	
	public MainFrame() {
		editorPanel = new EditorPanel(getGridManager());
	}
	
	@Override
	protected Collection<AbstractBasePanel> getPanelCollection() {
		List<AbstractBasePanel> panelList = new ArrayList<>();
		panelList.add(editorPanel);
		return panelList;
	}

	@Override
	protected void execute() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
