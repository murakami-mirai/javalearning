package javalearning.gui.frame.panel;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorPanel extends AbstractInputPanel {

	private final RSyntaxTextArea textArea;
	private final RTextScrollPane subPanel;
	
	public EditorPanel() {
		textArea = new RSyntaxTextArea(20,100);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		
		CompletionProvider provider = createCompletionProvider();
		AutoCompletion ac = new AutoCompletion(provider);
		ac.install(textArea);
		
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
	
   private CompletionProvider createCompletionProvider() {

	      // A DefaultCompletionProvider is the simplest concrete implementation
	      // of CompletionProvider. This provider has no understanding of
	      // language semantics. It simply checks the text entered up to the
	      // caret position for a match against known completions. This is all
	      // that is needed in the majority of cases.
	      DefaultCompletionProvider provider = new DefaultCompletionProvider();

	      // Add completions for all Java keywords. A BasicCompletion is just
	      // a straightforward word completion.
	      provider.addCompletion(new BasicCompletion(provider, "abstract"));
	      provider.addCompletion(new BasicCompletion(provider, "assert"));
	      provider.addCompletion(new BasicCompletion(provider, "break"));
	      provider.addCompletion(new BasicCompletion(provider, "case"));
	      // ... etc ...
	      provider.addCompletion(new BasicCompletion(provider, "transient"));
	      provider.addCompletion(new BasicCompletion(provider, "try"));
	      provider.addCompletion(new BasicCompletion(provider, "void"));
	      provider.addCompletion(new BasicCompletion(provider, "volatile"));
	      provider.addCompletion(new BasicCompletion(provider, "while"));

	      // Add a couple of "shorthand" completions. These completions don't
	      // require the input text to be the same thing as the replacement text.
	      provider.addCompletion(new ShorthandCompletion(provider, "sysout",
	            "System.out.println(", "System.out.println("));
	      provider.addCompletion(new ShorthandCompletion(provider, "syserr",
	            "System.err.println(", "System.err.println("));

	      return provider;

	   }
}
