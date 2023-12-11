package javalearning.gui.frame.panel;

import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorPanel extends AbstractInputPanel {
	
	private static final String[] RESERVED_WORDS = {
			"abstract", "assert", "boolean", "break", "byte",
			"case", "catch", "char", "class", "const",
			"continue", "default", "do", "double", "else",
			"enum", "extends", "final", "finally", "float",
			"for", "goto", "if", "implements", "import",
			"instanceof", "int", "interface", "long", "native",
			"new", "package", "private", "protected", "public",
			"return", "short", "static", "strictfp", "super",
			"switch", "synchrnized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while"};

	private final RSyntaxTextArea textArea;
	private final RTextScrollPane subPanel;
	
	public EditorPanel() {
		textArea = new RSyntaxTextArea(20,100);
		LanguageSupportFactory factory = LanguageSupportFactory.get();
		JavaLanguageSupport support = (JavaLanguageSupport)factory.getSupportFor(SyntaxConstants.SYNTAX_STYLE_JAVA);
//		support.getJarManager().addClassFileSource();
		support.setShowDescWindow(true);
		factory.register(textArea);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		subPanel = new RTextScrollPane(textArea);
		
//		CompletionProvider provider = createCompletionProvider();
//		AutoCompletion ac = new AutoCompletion(provider);
//		ac.install(textArea);
		
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
	
   private CompletionProvider createCompletionProvider() {

	      // A DefaultCompletionProvider is the simplest concrete implementation
	      // of CompletionProvider. This provider has no understanding of
	      // language semantics. It simply checks the text entered up to the
	      // caret position for a match against known completions. This is all
	      // that is needed in the majority of cases.
	      DefaultCompletionProvider provider = new DefaultCompletionProvider();

	      // Add completions for all Java keywords. A BasicCompletion is just
	      // a straightforward word completion.
	      for (String keyword : RESERVED_WORDS) {
	    	  provider.addCompletion(new BasicCompletion(provider, keyword));
	      }
	      // Add a couple of "shorthand" completions. These completions don't
	      // require the input text to be the same thing as the replacement text.
	      provider.addCompletion(new ShorthandCompletion(provider, "sysout",
	            "System.out.println(", "System.out.println("));

	      return provider;

	   }
}
