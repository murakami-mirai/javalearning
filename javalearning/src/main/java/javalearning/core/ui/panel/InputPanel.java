package javalearning.core.ui.panel;

import javax.swing.JScrollPane;

import org.fife.ui.rtextarea.RTextArea;

public class InputPanel extends AbstractScrollBasePanel {

	private final RTextArea textArea;
	private final JScrollPane subPanel;
	private final String[] inputParams;
	private int pos;
	
	public InputPanel(String... inputParams) {
		this.inputParams = inputParams;
		pos = 0;
		textArea = new RTextArea(10, 103);
		textArea.setHighlightCurrentLine(false);
		textArea.setEditable(false);
		subPanel = new JScrollPane(textArea);
	}
	
	@Override
	public void create() {
		this.add(subPanel);
		
	}
	
	public String[] getInputParams() {
		return inputParams != null? inputParams : new String[0];
	}
	
	public byte[] nextInputParam() {
		byte[] ret;
		if (inputParams == null 
				|| pos >= inputParams.length
				|| inputParams[pos] == null) {
			ret = new byte[0];
		} else {
			ret = inputParams[pos].getBytes();
		}
		pos++;
		return ret;
	}

	@Override
	protected GridSetting getGridSetting() {
		return new GridSetting(0, 0, 1, 2);
	}

}
