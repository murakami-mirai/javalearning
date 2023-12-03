package javalearning.gui.grid;

public final class GridSetting {
	private int gridheight = 1;
	private int gridgridwidth = 1;
	private double weightx = 1.0d;
	private double weighty = 1.0d;
	
	public GridSetting() {}
	
	public GridSetting(int gridheight, int gridgridwidth, double weightx, double weighty) {
		this.gridheight = gridheight;
		this.gridgridwidth = gridgridwidth;
		this.weightx = weightx;
		this.weighty = weighty;
	}
	
	public GridSetting(int gridheight, int gridgridwidth) {
		this.gridheight = gridheight;
		this.gridgridwidth = gridgridwidth;
	}
	
	public GridSetting(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
	}

	public int getGridheight() {
		return gridheight;
	}

	public int getGridgridwidth() {
		return gridgridwidth;
	}

	public double getWeightx() {
		return weightx;
	}

	public double getWeighty() {
		return weighty;
	}
}
