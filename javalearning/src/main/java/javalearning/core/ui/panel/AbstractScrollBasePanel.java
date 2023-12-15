package javalearning.core.ui.panel;

import java.awt.GridBagConstraints;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;



public abstract class AbstractScrollBasePanel extends JScrollPane implements IPanel {
	
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
	
	protected void add(JComponent component) {
		super.setViewportView(component);
	}
	
	protected void add(JTextField field,  int alignment) {
		field.setHorizontalAlignment(alignment);
		add(field);
	}
	
	protected void add(AbstractButton button, int alignment) {
		button.setHorizontalAlignment(alignment);
		JViewport view = super.getViewport();
		view.add(button);
	}
	
	protected abstract GridSetting getGridSetting();
	
}
