package javalearning.gui;

import java.util.Arrays;
import java.util.Collection;

import javalearning.core.control.CompileManager;
import javalearning.gui.frame.AbstractBaseFrame;
import javalearning.gui.frame.panel.AbstractBasePanel;
import javalearning.gui.frame.panel.ControlPanel;
import javalearning.gui.frame.panel.EditorPanel;
import javalearning.gui.frame.panel.OutputPanel;

public class MainFrame extends AbstractBaseFrame {

	private final EditorPanel editorPanel;
	private final ControlPanel controlPanel;
	private final OutputPanel outputPanel;
	
	public MainFrame() {
		editorPanel = new EditorPanel(getGridManager());
		controlPanel = new ControlPanel(getGridManager());
		outputPanel = new OutputPanel(getGridManager());
	}
	
	@Override
	protected Collection<AbstractBasePanel> getPanelCollection() {
		return Arrays.asList(editorPanel, outputPanel, controlPanel);
	}

	@Override
	protected void execute() {
		String sourceCode = editorPanel.getInputText();
		controlPanel.setCompileProcess(event -> {
			CompileManager compiler = new CompileManager();
			compiler.run(sourceCode);
		});
	}

}
