package javalearning.gui;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.SwingUtilities;

import javalearning.gui.frame.AbstractBaseFrame;
import javalearning.gui.frame.panel.ControlPanel;
import javalearning.gui.frame.panel.EditorPanel;
import javalearning.gui.frame.panel.IPanel;
import javalearning.gui.frame.panel.OutputPanel;
import javalearning.learning.core.AbstractQuestion;
import javalearning.learning.core.Question1;
import javalearning.learning.core.steam.LearnigOutputStream;
import javalearning.learning.core.steam.LearningPrintStream;

public class MainFrame extends AbstractBaseFrame {

	private final EditorPanel editorPanel;
	private final ControlPanel controlPanel;
	private final OutputPanel outputPanel;
	
	public MainFrame() {
		editorPanel = new EditorPanel();
		controlPanel = new ControlPanel();
		outputPanel = new OutputPanel();
		
		LearnigOutputStream outputStream = new LearnigOutputStream(outputPanel);
		LearningPrintStream printSteam = new LearningPrintStream(outputStream);
		System.setOut(printSteam);
		System.setErr(printSteam);
	}
	
	@Override
	protected Collection<IPanel> getPanelCollection() {
		return Arrays.asList(controlPanel, editorPanel, outputPanel);
	}

	@Override
	protected void execute() {
		AbstractQuestion question = new Question1();
		editorPanel.setInputText(question.getSourceCode());
		controlPanel.setCompileProcess(event -> {
			outputPanel.resetText();
			question.setSourceCode(editorPanel.getInputText());
			SwingUtilities.invokeLater(question);
//			question.run();
		});
	}

}
