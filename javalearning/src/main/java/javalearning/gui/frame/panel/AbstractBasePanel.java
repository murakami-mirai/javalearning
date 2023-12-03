package javalearning.gui.frame.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.PopupMenu;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javalearning.gui.grid.GridManager;
import javalearning.gui.grid.GridSetting;


public abstract class AbstractBasePanel extends JPanel implements IPanel {
	
	private GridManager gridManager;
	
	public AbstractBasePanel(GridManager gridManager) {
		setGridManager(gridManager);
	}
	
	public void create() {
		setLayout(gridManager.getSubPanelLayout(this));
		createPanel();
	}
	
	public Font getFont() {
		return new Font(Font.MONOSPACED, Font.PLAIN, 15);
	}
	
	protected void add(JComponent component, GridSetting setting) {
		
		gridManager.setSubPanelComponent(this, component, setting);
		component.setLayout(new BorderLayout(5, 10));
		component.setFont(getFont());
		super.add(component);
	}
	
	protected void addNoSetLayout(JComponent component, GridSetting setting) {
		
		gridManager.setSubPanelComponent(this, component, setting);
		super.add(component);
	}
	
	protected void add(JTextField field, GridSetting setting, int alignment) {
		field.setHorizontalAlignment(alignment);
		add(field, setting);
	}
	
	protected void add(AbstractButton button, GridSetting setting, int alignment) {
		button.setHorizontalAlignment(alignment);
		add(button, setting);
	}
	
	protected LayoutManager getLayoutManager() {
		return new FlowLayout(FlowLayout.LEFT);
	}
	
	public GridManager getGridManager() {
		return gridManager;
	}
	
	public void setGridManager(GridManager gridManger) {
		this.gridManager = gridManger;
	}
	
	protected abstract void createPanel();
	
	public abstract GridSetting getPanelSetting();
	
	/**
	 * @deprecated この抽象クラスを利用する場合は、追加で設定情報が必要である
	 * @see AbstractBasePanel#add(JTextField, GridSetting, int)
	 */
	@Deprecated
	@Override
	public Component add(Component comp) {throw new UnsupportedOperationException();}
	
	/**
	 * @deprecated この抽象クラスを利用する場合は、追加で設定情報が必要である
	 * @see AbstractBasePanel#add(JTextField, GridSetting, int)
	 */
	@Deprecated
	@Override
	public Component add(String name, Component comp) {throw new UnsupportedOperationException();}
	
	/**
	 * @deprecated この抽象クラスを利用する場合は、追加で設定情報が必要である
	 * @see AbstractBasePanel#add(JTextField, GridSetting, int)
	 */
	@Deprecated
	@Override
	public Component add(Component comp,int index) {throw new UnsupportedOperationException();}
	
	/**
	 * @return 
	 * @deprecated この抽象クラスを利用する場合は、追加で設定情報が必要である
	 * @see AbstractBasePanel#add(JComponent, GridSetting)
	 */
	@Deprecated
	@Override
	public  void add(PopupMenu menu) {throw new UnsupportedOperationException();}

	/**
	 * @return 
	 * @deprecated この抽象クラスを利用する場合は、追加で設定情報が必要である
	 * @see AbstractBasePanel#add(JComponent, GridSetting)
	 */
	@Deprecated
	@Override
	public void add(Component comp,Object constraints) {throw new UnsupportedOperationException();}

	/**
	 * @return 
	 * @deprecated この抽象クラスを利用する場合は、追加で設定情報が必要である
	 * @see AbstractBasePanel#add(JComponent, GridSetting)
	 */
	@Deprecated
	@Override
	public void add(Component comp, Object constraints, int index) {throw new UnsupportedOperationException();}
}
