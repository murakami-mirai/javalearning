package javalearning.gui.frame;

import java.awt.GridBagLayout;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import javalearning.gui.frame.panel.IPanel;


public abstract class AbstractBaseFrame extends JFrame {
	
	private static final int DEFAULT_HIGHT = 600;
	private static final int DEFAULT_WIDTH = 700;
	private final JPanel basePanel;
	
	public AbstractBaseFrame() {
		basePanel = new JPanel();
	}
	
	public final void doProcess() {
		basePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout basePanelLayout = new GridBagLayout();
//		basePanelLayout.columnWidths = new int[]{0, 100, 0};
//		basePanelLayout.rowHeights = new int[]{50, 0, 0};
//		basePanelLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
//		basePanelLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		basePanel.setLayout(basePanelLayout);
		
		
		// 親パネルを描画
		setContentPane(basePanel);
		// ×ボタンをアプリの終了に設定
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// ウィンドウサイズを設定
		setBounds(80, 80, DEFAULT_WIDTH, DEFAULT_HIGHT);
		
		
		// 子パネルを描画
		Collection<IPanel> panelCollection = getPanelCollection();
		panelCollection.stream().forEach(panel -> {
			panel.create();
			if (basePanel instanceof JComponent) {
				basePanel.add((JComponent)panel, panel.getConstraints());
			} else {
				throw new ClassCastException("AbstractBasePanelまたはAbstractScrollBasePanelの継承が必要です");
			}
		});
		
		// フレームで行う処理を実行
		execute();
		
		// 各パネルの最適化を行い描画
		pack();
		setVisible(true);
	}
	
	protected abstract Collection<IPanel> getPanelCollection();
	protected abstract void execute();
}
