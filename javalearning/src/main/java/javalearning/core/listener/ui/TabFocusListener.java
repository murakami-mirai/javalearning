package javalearning.core.listener.ui;

import java.awt.Component;

import javax.swing.JTabbedPane;

public class TabFocusListener implements IFocusListener {

	private JTabbedPane panel;

	public TabFocusListener(JTabbedPane panel) {
		this.panel = panel;
	}
	
	@Override
	public void forcus(Component component) {
		panel.setSelectedComponent(component);
	}

}
