package javalearning.core.ui.panel;

import java.awt.GridBagConstraints;

import javax.swing.JTabbedPane;

import javalearning.core.listener.ui.TabFocusListener;

public class OutputTabPanel extends JTabbedPane implements IPanel {

	private static final String CONSOLE = "コンソール";
	private static final String INPUT = "標準入力";
	private static final String OUTPUT = "標準出力";
	private static final String ERROR = "標準エラー";
	
	/** コンソールパネル */
	private final OutputPanel consolePanel;
	/** 標準入力パネル */
	private final OutputPanel inputPanel;
	/** 標準出力パネル */
	private final OutputPanel outputPanel;
	/** 標準エラーパネル */
	private final OutputPanel errorPanel;
	
	public OutputTabPanel() {
		consolePanel = new OutputPanel();
		inputPanel = new OutputPanel();
		outputPanel = new OutputPanel(new TabFocusListener(this));
		errorPanel = new OutputPanel(new TabFocusListener(this));
	}
	
	@Override
	public void create() {
		consolePanel.create();
		inputPanel.create();
		outputPanel.create();
		errorPanel.create();
		
		this.addTab(CONSOLE, consolePanel);
		this.addTab(INPUT, inputPanel);
		this.addTab(OUTPUT, outputPanel);
		this.addTab(ERROR, errorPanel);
		
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
	
	public OutputPanel getInputPanel() {
		return inputPanel;
	}
}
