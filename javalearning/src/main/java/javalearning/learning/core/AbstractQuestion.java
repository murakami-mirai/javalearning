package javalearning.learning.core;

import java.io.PrintStream;

import javalearning.core.listener.CompileRunListner;
import javalearning.learning.core.steam.LearningPrintStream;

public abstract class AbstractQuestion implements Runnable {
	
	/** 標準出力を保存 */
	private static final PrintStream SYSTEM_OUT = System.out;
	/** 標準エラーを保存 */
	private static final PrintStream SYSTEM_ERR = System.err;
	/** デフォルトメインクラス */
	private static final String MAIN_CLASS = "Main";
	/** デフォルトパッケージ */
	private static final String DEFAULT_PACKAGE = "";
	
	/** ラーニングシステムの標準出力 */
	private final LearningPrintStream outStream;
	/** ラーニングシステムの標準エラー */
	private final LearningPrintStream errStream;
	/** メインメソッドのパラメータ */
	private String[] mainParams;
	/** コンパイル対象のソースコード */
	private String sourceCode;

	public AbstractQuestion(LearningPrintStream outStream, LearningPrintStream errStream) {
		sourceCode = getCode();
		mainParams = new String[0];
		this.outStream = outStream;
		this.errStream = errStream;
	}
	
	public AbstractQuestion(LearningPrintStream outStream, LearningPrintStream errStream, String... args) {
		sourceCode = getCode();
		mainParams = args;
		this.outStream = outStream;
		this.errStream = errStream;
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
		
		setStdOut(outStream, errStream);
		listner.run();
		if (isSuccess()) {
			System.out.println("正解!!");
		} else {
			System.out.println("残念。。。");
		}
		setStdOut(SYSTEM_OUT, SYSTEM_ERR);
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
			sb.append("package ").append(getPackage());
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
	
	private boolean isExistPackage() {
		String pack = getPackage();
		return !(pack == null || pack.isBlank());
	}
	
	private void setStdOut(PrintStream out, PrintStream err) {
		System.setOut(out);
		System.setErr(err);
	}
	
	private boolean isSuccess() {
		String correct = getCorrectAnswer();
		if (correct == null || correct.trim().isEmpty()) {
			return true;
		}
		
		try {
			String answer = outStream.getOutputStream().getPanel().getText();
			
			answer = trimIndentionOfLineEnd(answer);
			correct = trimIndentionOfLineEnd(correct);
			
			return correct.equals(answer);
		}catch (Exception e) {
			return false;
		}
	}
	
	private String trimIndentionOfLineEnd(String text) {
		if (text.length() > 1 && text.endsWith("\n")) {
			return text.substring(0, text.length() - 1); 
		}
		return text;
	}
}
