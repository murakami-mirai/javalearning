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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javalearning.core.control.reader.QuestionXMLReader;
import javalearning.core.exception.QuestionXMLReaderException;
import javalearning.core.stream.LearnigOutputStream;
import javalearning.core.stream.LearningInputStream;
import javalearning.core.stream.LearningPrintStream;
import javalearning.core.ui.panel.EditorPanel;
import javalearning.core.ui.panel.IPanel;
import javalearning.core.ui.panel.OutputPanel;
import javalearning.core.ui.panel.OutputTabPanel;
import javalearning.questions.AbstractQuestion;
import javalearning.questions.TutorialQuestion;

public class MainFrame extends AbstractBaseFrame {
	
	private final static Logger LOGGER = LogManager.getLogger(AbstractQuestion.class);
	private final static String QUESTION_XML_FILE_NAME = "question.xml";
	
	private final EditorPanel editorPanel;
	private final OutputPanel inputPanel;
	private final OutputTabPanel outputTabPanel;
	private final LearningPrintStream consolePrintStream;
	private final LearningPrintStream outputPrintStream;
	private final LearningPrintStream errorPrintStream;
	private final QuestionXMLReader reader;
	
	private AbstractQuestion question;
	
	
	public MainFrame() {
		editorPanel = new EditorPanel();
		outputTabPanel = new OutputTabPanel();
		inputPanel = outputTabPanel.getInputPanel();
		
		LearnigOutputStream consoleOutStream = new LearnigOutputStream(outputTabPanel.getConsolePanel());
		LearnigOutputStream outputOutStream = new LearnigOutputStream(outputTabPanel.getOutputPanel());
		LearnigOutputStream errorOutStream = new LearnigOutputStream(outputTabPanel.getErrorPanel());
		consolePrintStream = new LearningPrintStream(consoleOutStream);
		outputPrintStream = new LearningPrintStream(outputOutStream);
		errorPrintStream = new LearningPrintStream(errorOutStream);
		reader = new QuestionXMLReader(QUESTION_XML_FILE_NAME, consolePrintStream, outputPrintStream, errorPrintStream);
	}
	
	@Override
	protected Collection<IPanel> getPanelCollection() {
		return Arrays.asList(editorPanel, outputTabPanel);	
	}

	@Override
	protected void execute() {
		createMenubar();
		LearningInputStream tutorialInputStream = new LearningInputStream("あああ\nべべ"); 
		question = new TutorialQuestion(consolePrintStream, outputPrintStream, errorPrintStream, tutorialInputStream);
		editorPanel.setInputText(question.getSourceCode());
		inputPanel.setText(question.getInputText());
	
	}
	
	private void createMenubar() {
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("ファイル");
		JMenu questionMenu = new JMenu("問題");
		JMenu editMenu = new JMenu("実行");
		setRunItemMenu(editMenu);
		setQuestionsItemMenu(questionMenu);
		
		menubar.add(fileMenu);
		menubar.add(questionMenu);
		menubar.add(editMenu);
		setJMenuBar(menubar);
	}
	
	private void setRunItemMenu(JMenu menu) {
		JMenuItem item = new JMenuItem("実行");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		item.addActionListener(event -> {
			outputTabPanel.getOutputPanel().resetText();
			outputTabPanel.getErrorPanel().resetText();
			question.setSourceCode(editorPanel.getInputText());
			
			SwingUtilities.invokeLater(question);
		});
		menu.add(item);
	}
	
	private void setQuestionsItemMenu(JMenu menu) {
		try {
			for (AbstractQuestion q : reader.getQuestions()) {
				JMenuItem item = new JMenuItem(q.getQuestionName());
				item.addActionListener(event -> {
					editorPanel.setInputText(q.getSourceCode());
					inputPanel.setText(q.getInputText());
					question = q;
					disableCurrentQuestion(menu, item);
				});
				menu.add(item);
			}
		} catch (QuestionXMLReaderException e) {
			LOGGER.error(e);
		}
	}
	
	private void disableCurrentQuestion(JMenu menu, JMenuItem item) {
		for (int i = 0; i < menu.getItemCount(); i++) {
			JMenuItem im = menu.getItem(i);
			if (im == null) {
				continue;
			}
			if(im.equals(item)) {
				im.setEnabled(false);
			} else {
				im.setEnabled(true);
			}
		}
	}
}
