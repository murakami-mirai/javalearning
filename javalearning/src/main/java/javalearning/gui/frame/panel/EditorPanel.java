package javalearning.gui.frame.panel;

import java.io.IOException;

import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javalearning.core.model.LearningJarLibraryInfo;

public class EditorPanel extends AbstractInputPanel {

	private final RSyntaxTextArea textArea;
	private final RTextScrollPane subPanel;

	public EditorPanel() {
		textArea = new RSyntaxTextArea(20, 100);
		LanguageSupportFactory factory = LanguageSupportFactory.get();
		JavaLanguageSupport support = (JavaLanguageSupport) factory.getSupportFor(SyntaxConstants.SYNTAX_STYLE_JAVA);
		try {

			support.getJarManager().addClassFileSource(new LearningJarLibraryInfo());
			support.setShowDescWindow(true);
			factory.register(textArea);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String javaInfo = System.getenv("JAVA_HOME");
		System.out.println(javaInfo);

		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		subPanel = new RTextScrollPane(textArea);
	}

	@Override
	public void create() {
		add(subPanel);
	}

	@Override
	public String getInputText() {
		return textArea.getText();
	}

	public void setInputText(String text) {
		textArea.setText(text);
	}

	@Override
	protected GridSetting getGridSetting() {
		return new GridSetting(0, 1, 1, 2);
	}
}
