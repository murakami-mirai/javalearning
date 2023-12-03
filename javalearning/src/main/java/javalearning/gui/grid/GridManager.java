package javalearning.gui.grid;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.JComponent;

import javalearning.gui.frame.panel.AbstractBasePanel;

/**
 * GUIグリッドを管理するクラス<br>
 * パネルにあるパーツを格子状に配置する
 */
public final class GridManager {
	
	/** パーツを配置するベースパネル */
	private PanelManager basePanel;
	private Map<AbstractBasePanel ,PanelManager> subPanelMap;
	
	public GridManager() {
		basePanel = new PanelManager();
		subPanelMap = new HashMap<>();
	}
	
	public void addSubPanel(AbstractBasePanel subPanel) {
		if (isExistPanelName(subPanel)) {
			throw new KeyAlreadyExistsException("panelName is exists");
		}
		subPanelMap.put(subPanel, new PanelManager());
		basePanel.setComponent(subPanel, subPanel.getPanelSetting());
	}
	
	public void addLine() {
		basePanel.addLine();
	}
	
	public GridBagLayout getLayout() {
		return basePanel.layout;
	}
	
	public GridBagLayout getSubPanelLayout(AbstractBasePanel subPanel) {
		if (!isExistPanelName(subPanel)) {
			throw new NullPointerException("panel is unput");
		}
		return getSubPanel(subPanel).getLayout();
	}
	
	public GridBagConstraints getSubPanelConstraints(AbstractBasePanel subPanel) {
		if (!isExistPanelName(subPanel)) {
			throw new NullPointerException("panel is unput");
		}
		return getSubPanel(subPanel).getConstraints();
	}
	
	public void addSubPanelNewLine(AbstractBasePanel subPanel) {
		getSubPanel(subPanel).addLine();
	}
	
	public void setSubPanelComponent(AbstractBasePanel subPanel, JComponent component, GridSetting setting) {
		getSubPanel(subPanel).setComponent(component, setting);
	}
	
	private boolean isExistPanelName(AbstractBasePanel subPanel) {
		if (subPanel == null) {
			throw new NullPointerException("subPanel Is NULL");
			
		}
		return subPanelMap.containsKey(subPanel);
	}
	
	private PanelManager getSubPanel(AbstractBasePanel subPanel) {
		if (!isExistPanelName(subPanel)) {
			throw new NullPointerException("panel is unput");
		}
		return subPanelMap.get(subPanel);
	}
	
	private class PanelManager {
		private final GridBagLayout layout;
		private final GridBagConstraints constraints;
		private int y = 0;
		private int x = 0;
		
		public PanelManager() {
			layout = new GridBagLayout();
			constraints = new GridBagConstraints();
		}
		
		public GridBagLayout getLayout() {
			return layout;
		}

		public GridBagConstraints getConstraints() {
			return constraints;
		}

		public void addLine() {
			x = 0;
			y++;
		}
		
		public void setComponent(JComponent component, GridSetting setting) {
			constraints.gridx = x;
			constraints.gridy = y;
			constraints.gridwidth = setting.getGridheight();
			constraints.gridheight = setting.getGridheight();
			constraints.weightx = setting.getWeightx();
			constraints.weighty = setting.getWeighty();
			constraints.fill = GridBagConstraints.CENTER;
			layout.setConstraints(component, constraints);
			x++;
		}
	}
}