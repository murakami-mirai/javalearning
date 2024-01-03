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
import javalearning.core.stream.LearningPrintStream;
import javalearning.core.ui.panel.EditorPanel;
import javalearning.core.ui.panel.IPanel;
import javalearning.core.ui.panel.OutputTabPanel;
import javalearning.questions.AbstractQuestion;

public class MainFrame extends AbstractBaseFrame {
	
	private final static Logger LOGGER = LogManager.getLogger(AbstractQuestion.class);
	private final static String QUESTION_XML_FILE_NAME = "question.xml";
	
	private final EditorPanel editorPanel;
	private final OutputTabPanel outputTabPanel;
	private final LearningPrintStream consolePrintStream;
	private final LearningPrintStream outputPrintStream;
	private final LearningPrintStream errorPrintStream;
	private final QuestionXMLReader reader;
	
	private AbstractQuestion question;
	
	
	public MainFrame() {
		editorPanel = new EditorPanel();
		outputTabPanel = new OutputTabPanel();
		
		LearnigOutputStream consoleOutStream = new LearnigOutputStream(outputTabPanel.getConsolePanel());
		LearnigOutputStream outputOutStream = new LearnigOutputStream(outputTabPanel.getOutputPanel());
		LearnigOutputStream errorOutStream = new LearnigOutputStream(outputTabPanel.getErrorPanel());
		consolePrintStream = new LearningPrintStream(consoleOutStream);
		outputPrintStream = new LearningPrintStream(outputOutStream);
		errorPrintStream = new LearningPrintStream(errorOutStream);
		reader = new QuestionXMLReader(QUESTION_XML_FILE_NAME, outputPrintStream, errorPrintStream, consolePrintStream);
	}
	
	@Override
	protected Collection<IPanel> getPanelCollection() {
		return Arrays.asList(editorPanel, outputTabPanel);	
	}

	@Override
	protected void execute() {
		createMenubar();
		if (question != null) {
			editorPanel.setInputText(question.getSourceCode());
		}
	
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
		boolean isFirst = true;
		try {
			for (AbstractQuestion q : reader.getQuestions()) {
				if (isFirst) {
					question = q;
					isFirst = false;
				}
				JMenuItem item = new JMenuItem(q.getClass().getSimpleName());
				item.addActionListener(event -> {
					editorPanel.setInputText(q.getSourceCode());
				});
				menu.add(item);
			}
		} catch (QuestionXMLReaderException e) {
			LOGGER.error(e);
		}
	}
}
