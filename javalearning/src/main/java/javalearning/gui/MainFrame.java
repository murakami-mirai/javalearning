package javalearning.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import javalearning.gui.frame.AbstractBaseFrame;
import javalearning.gui.frame.panel.EditorPanel;
import javalearning.gui.frame.panel.IPanel;
import javalearning.gui.frame.panel.OutputPanel;
import javalearning.learning.core.AbstractQuestion;
import javalearning.learning.core.Question1;
import javalearning.learning.core.steam.LearnigOutputStream;
import javalearning.learning.core.steam.LearningPrintStream;

public class MainFrame extends AbstractBaseFrame {

	private final EditorPanel editorPanel;
//	private final ControlPanel controlPanel;
	private final OutputPanel outputPanel;
	
	public MainFrame() {
		editorPanel = new EditorPanel();
//		controlPanel = new ControlPanel();
		outputPanel = new OutputPanel();
		createMenubar();
		
		LearnigOutputStream outputStream = new LearnigOutputStream(outputPanel);
		LearningPrintStream printSteam = new LearningPrintStream(outputStream);
		System.setOut(printSteam);
		System.setErr(printSteam);
	}
	
	@Override
	protected Collection<IPanel> getPanelCollection() {
		return Arrays.asList(editorPanel, outputPanel);
	}

	@Override
	protected void execute() {
		AbstractQuestion question = new Question1();
		editorPanel.setInputText(question.getSourceCode());
//		controlPanel.setCompileProcess(event -> {
//			outputPanel.resetText();
//			question.setSourceCode(editorPanel.getInputText());
//			SwingUtilities.invokeLater(question);
////			question.run();
//		});
	}
	
	private void createMenubar() {
		AbstractQuestion question = new Question1();
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenuItem runMenuItem = new JMenuItem("Run");
		runMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		runMenuItem.addActionListener(event -> {
			outputPanel.resetText();
			question.setSourceCode(editorPanel.getInputText());
			SwingUtilities.invokeLater(question);			
		});
		editMenu.add(runMenuItem);
		
		menubar.add(fileMenu);
		menubar.add(editMenu);
		setJMenuBar(menubar);
	}

}
