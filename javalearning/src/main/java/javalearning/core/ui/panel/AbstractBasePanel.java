package javalearning.core.ui.panel;

import java.awt.GridBagConstraints;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractBasePanel extends JPanel implements IPanel {
	
	public GridBagConstraints getConstraints() {
		GridBagConstraints constraints = new GridBagConstraints();
		GridSetting setting = getGridSetting();
		constraints.gridx = setting.getX();
		constraints.gridy = setting.getY();
		constraints.gridwidth = setting.getWidth();
		constraints.gridheight = setting.getHeight();
		constraints.fill = setting.getFill();
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
		return constraints;
	}
	
	
	protected void add(JTextField field,  int alignment) {
		field.setHorizontalAlignment(alignment);
		add(field);
	}
	
	protected void add(AbstractButton button, int alignment) {
		button.setHorizontalAlignment(alignment);
	}
	
	protected abstract GridSetting getGridSetting();

}
