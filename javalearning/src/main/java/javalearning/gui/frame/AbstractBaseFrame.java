package javalearning.gui.frame;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javalearning.gui.frame.panel.AbstractBasePanel;
import javalearning.gui.grid.GridManager;


public abstract class AbstractBaseFrame extends JFrame {
	
	private static final int DEFAULT_HIGHT = 600;
	private static final int DEFAULT_WIDTH = 700;
	private final GridManager gridManager;
	private final JPanel basePanel;
	
	public AbstractBaseFrame() {
		basePanel = new JPanel();
		gridManager = new GridManager();
	}
	
	public final void doProcess() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		basePanel.setLayout(gridManager.getLayout());
		Collection<AbstractBasePanel> panelCollection = getPanelCollection();
		panelCollection.stream().forEach(panel -> {
			gridManager.addSubPanel(panel);
			panel.create();
			basePanel.add(panel);
		});
		setContentPane(basePanel);
		setBounds(80, 80, DEFAULT_WIDTH, DEFAULT_HIGHT);
		execute();
		pack();
		setVisible(true);
	}
	
	protected final GridManager getGridManager() {
		return gridManager;
	}
	
	protected abstract Collection<AbstractBasePanel> getPanelCollection();
	protected abstract void execute();
}
