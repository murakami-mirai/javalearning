package javalearning.gui.frame.panel;

import java.awt.GridBagConstraints;

public class GridSetting {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int fill;
	
	public GridSetting(int x, int y) {
		this(x, y, 0, 0);
	}
	
	public GridSetting(int x, int y, int width, int height) {
		this(x, y, width, height, GridBagConstraints.BOTH);
	}
	
	public GridSetting(int x, int y, int width, int height, int fill) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getFill() {
		return fill;
	}
}
