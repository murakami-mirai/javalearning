package javalearning.core.ui.panel;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControlPanel extends AbstractBasePanel {

	private final JButton compileButton;
	
	public ControlPanel() {
		compileButton = new JButton("実行");
	}

	@Override
	public void create() {
		add(compileButton);
	}

	public void setCompileProcess(ActionListener listner) {
		compileButton.addActionListener(listner);
	}

	@Override
	protected GridSetting getGridSetting() {
		return new GridSetting(0, 0, 1, 1);
	}
}
