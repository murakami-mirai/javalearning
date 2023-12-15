package javalearning.core.ui.frame;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import javalearning.core.model.AbstractQuestion;
import javalearning.core.stream.LearnigOutputStream;
import javalearning.core.stream.LearningPrintStream;
import javalearning.core.ui.panel.EditorPanel;
import javalearning.core.ui.panel.IPanel;
import javalearning.core.ui.panel.OutputPanel;
import javalearning.questions.Question1;

public class MainFrame extends AbstractBaseFrame {
	
	private final EditorPanel editorPanel;
	private final OutputPanel outputPanel;
	private final LearningPrintStream printStream;
	
	
	public MainFrame() {
		editorPanel = new EditorPanel();
		outputPanel = new OutputPanel();
		
		LearnigOutputStream outputStream = new LearnigOutputStream(outputPanel);
		printStream = new LearningPrintStream(outputStream);
	}
	
	@Override
	protected Collection<IPanel> getPanelCollection() {
		return Arrays.asList(editorPanel, outputPanel);
	}

	@Override
	protected void execute() {
		createMenubar();
	}
	
	private void createMenubar() {
		AbstractQuestion question = new Question1(printStream, printStream);
		editorPanel.setInputText(question.getSourceCode());
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
