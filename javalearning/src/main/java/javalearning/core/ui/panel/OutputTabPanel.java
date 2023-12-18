package javalearning.core.ui.panel;

import java.awt.GridBagConstraints;

import javax.swing.JTabbedPane;

import javalearning.core.listener.ui.TabFocusListener;

public class OutputTabPanel extends JTabbedPane implements IPanel {

	private OutputPanel consolePanel;
	private OutputPanel outputPanel;
	private OutputPanel errorPanel;
	
	public OutputTabPanel() {
		consolePanel = new OutputPanel(new TabFocusListener(this));
		outputPanel = new OutputPanel(new TabFocusListener(this));
		errorPanel = new OutputPanel(new TabFocusListener(this));
	}
	
	@Override
	public void create() {
		consolePanel.create();
		outputPanel.create();
		errorPanel.create();
		
		this.addTab("コンソール", consolePanel);
		this.addTab("標準出力", outputPanel);
		this.addTab("エラー出力", errorPanel);
		
	}

	@Override
	public GridBagConstraints getConstraints() {
		GridBagConstraints constraints = new GridBagConstraints();
		GridSetting setting = getGridSetting();
		constraints.gridx = setting.getX();
		constraints.gridy = setting.getY();
		constraints.gridwidth = setting.getWidth();
		constraints.gridheight = setting.getHeight();
		constraints.fill = setting.getFill();
		constraints.anchor = GridBagConstraints.BASELINE;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
		return constraints;
	}
	
	private GridSetting getGridSetting() {
		return new GridSetting(0, 3, 1, 2);
	}

	public OutputPanel getConsolePanel() {
		return consolePanel;
	}

	public OutputPanel getOutputPanel() {
		return outputPanel;
	}

	public OutputPanel getErrorPanel() {
		return errorPanel;
	}

}
