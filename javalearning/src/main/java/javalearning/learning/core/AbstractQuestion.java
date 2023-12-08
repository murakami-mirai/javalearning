package javalearning.learning.core;

import javalearning.core.listener.CompileRunListner;

public abstract class AbstractQuestion implements Runnable {
//public abstract class AbstractQuestion {
	
	private static final String MAIN_CLASS = "Main";
	
	private String[] mainParams;
	private String sourceCode;

	public AbstractQuestion() {
		sourceCode = getCode();
		mainParams = new String[0];
	}
	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	@Override
	public void run() {
		CompileRunListner listner = new CompileRunListner(getMainClassName(), sourceCode, mainParams);
		listner.run();
	}
	
	protected abstract String getBeginningCode();
	
	protected abstract String getCorrectAnswer();
	
	protected String getMainClassName() {
		return MAIN_CLASS;
	}
	
	private String getCode() {
		String code = "public class Main {\n"
					+ "\tpublic static void main(String[] args) {\n";
			   code += ("\t\t" + getBeginningCode() + "\n");
			   code += "\t}\n";
			   code += "}";
		return code;
	}
}
