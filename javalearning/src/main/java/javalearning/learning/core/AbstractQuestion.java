package javalearning.learning.core;

import javalearning.core.listener.CompileRunListner;

public abstract class AbstractQuestion implements Runnable {
//public abstract class AbstractQuestion {
	
	private static final String MAIN_CLASS = "Main";
	private static final String DEFAULT_PACKAGE = "";
	
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
		CompileRunListner listner = new CompileRunListner(getFQCN(), sourceCode, mainParams);
		listner.run();
	}
	
	protected abstract String getBeginningCode();
	
	protected abstract String getCorrectAnswer();
	
	protected String getMainClassName() {
		return MAIN_CLASS;
	}
	
	protected String getPackage() {
		return DEFAULT_PACKAGE;
	}
	
	private String getCode() {
		StringBuilder sb = new StringBuilder();
		if (isExistPackage()) {
			sb.append("package " + getPackage());
		}
		sb.append("public class ").append(MAIN_CLASS).append(" {\n");
		sb.append("\tpublic static void main(String[] args) {\n");
		sb.append("\t\t").append(getBeginningCode()).append("\n");
		sb.append("\t}\n");
		sb.append("}");
		return sb.toString();
	}
	
	private String getFQCN() {
		String pack = getPackage();
		if (!isExistPackage()) {
			return getMainClassName();
		}
		StringBuilder sb = new StringBuilder(pack);
		sb.append(".");
		sb.append(getMainClassName());
		return sb.toString();
	}
	
	public boolean isExistPackage() {
		String pack = getPackage();
		return !(pack == null || pack.isBlank());
	}
}
