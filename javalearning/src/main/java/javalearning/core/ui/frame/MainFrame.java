package javalearning.core.ui.frame;

import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import javalearning.core.QuestionXMLReader;
import javalearning.core.stream.LearnigOutputStream;
import javalearning.core.stream.LearningPrintStream;
import javalearning.core.ui.panel.EditorPanel;
import javalearning.core.ui.panel.IPanel;
import javalearning.core.ui.panel.OutputTabPanel;
import javalearning.questions.AbstractQuestion;
import javalearning.questions.Question1;

public class MainFrame extends AbstractBaseFrame {
	
	private final EditorPanel editorPanel;
	private final OutputTabPanel outputTabPanel;
	private final LearningPrintStream consolePrintStream;
	private final LearningPrintStream outputPrintStream;
	private final LearningPrintStream errorPrintStream;

	public MainFrame() {
		editorPanel = new EditorPanel();
		outputTabPanel = new OutputTabPanel();
		
		LearnigOutputStream consoleOutStream = new LearnigOutputStream(outputTabPanel.getConsolePanel());
		LearnigOutputStream outputOutStream = new LearnigOutputStream(outputTabPanel.getOutputPanel());
		LearnigOutputStream errorOutStream = new LearnigOutputStream(outputTabPanel.getErrorPanel());
		consolePrintStream = new LearningPrintStream(consoleOutStream);
		outputPrintStream = new LearningPrintStream(outputOutStream);
		errorPrintStream = new LearningPrintStream(errorOutStream);
	}
	
	@Override
	protected Collection<IPanel> getPanelCollection() {
		return Arrays.asList(editorPanel, outputTabPanel);	
	}

	@Override
	protected void execute() {
		createMenubar();
		
		outputTabPanel.getOutputPanel().addListener(new InputMethodListener() {
			
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {
				System.out.println("入力 チェンジ");
				
			}
			
			@Override
			public void caretPositionChanged(InputMethodEvent event) {
				System.out.println("入力 ポジション");
				
			}
		});
	}
	
	private void createMenubar() {
		new QuestionXMLReader("question.xml").getQuestions();
		AbstractQuestion question = new Question1(consolePrintStream, outputPrintStream, errorPrintStream);
		editorPanel.setInputText(question.getSourceCode());
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("ファイル");
		JMenu editMenu = new JMenu("実行");
		JMenuItem runMenuItem = new JMenuItem("実行");
		runMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		runMenuItem.addActionListener(event -> {
			outputTabPanel.getOutputPanel().resetText();
			outputTabPanel.getErrorPanel().resetText();
			question.setSourceCode(editorPanel.getInputText());
			
			SwingUtilities.invokeLater(question);
		});
		editMenu.add(runMenuItem);
		
		menubar.add(fileMenu);
		menubar.add(editMenu);
		setJMenuBar(menubar);
	}
}
