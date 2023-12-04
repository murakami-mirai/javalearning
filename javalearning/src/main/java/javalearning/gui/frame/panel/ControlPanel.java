package javalearning.gui.frame.panel;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import javalearning.gui.grid.GridManager;
import javalearning.gui.grid.GridSetting;

public class ControlPanel extends AbstractBasePanel {

	private final JButton compileButton;
	
	public ControlPanel(GridManager gridManager) {
		super(gridManager);
		compileButton = new JButton("実行");
	}

	@Override
	protected void createPanel() {
		GridSetting setting = new GridSetting();
		add(compileButton,setting);
		getGridManager().addLine();
	}

	@Override
	public GridSetting getPanelSetting() {
		return new GridSetting();
	}
	public void setCompileProcess(ActionListener listner) {
		compileButton.addActionListener(listner);
	}
}
