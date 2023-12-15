package javalearning.questions;

import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javalearning.core.listener.CompileRunListner;
import javalearning.core.stream.LearningPrintStream;

public abstract class AbstractQuestion implements Runnable {
	
	/** 改行文字 */
	protected static final String LF = "\n";
	/** インデント */
	protected static final String INDENT = "\t";
	
	/** 標準出力を保存 */
	private static final PrintStream SYSTEM_OUT = System.out;
	/** 標準エラーを保存 */
	private static final PrintStream SYSTEM_ERR = System.err;
	/** デフォルトメインクラス */
	private static final String MAIN_CLASS = "Main";
	/** デフォルトパッケージ */
	private static final String DEFAULT_PACKAGE = "";
	
	private static final Logger LOGGER = LogManager.getLogger(AbstractQuestion.class);

	/** ラーニングシステムの標準出力 */
	private final LearningPrintStream outStream;
	/** ラーニングシステムの標準エラー */
	private final LearningPrintStream errStream;
	/** メインメソッドのパラメータ */
	private String[] mainParams;
	/** コンパイル対象のソースコード */
	private String sourceCode;

	/**
	 * コンストラクタ
	 * @param outStream 出力ストリーム
	 * @param errStream エラー出力ストリーム
	 */
	public AbstractQuestion(LearningPrintStream outStream, LearningPrintStream errStream) {
		sourceCode = getCode();
		mainParams = new String[0];
		this.outStream = outStream;
		this.errStream = errStream;
	}
	
	/**
	 * コンストラクタ
	 * @param outStream 出力ストリーム
	 * @param errStream エラー出力ストリーム
	 * @param args メインパラメータ
	 */
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
			LOGGER.info("正解");
		} else {
			System.out.println("残念。。。");
			LOGGER.info("不正解");
		}
		setStdOut(SYSTEM_OUT, SYSTEM_ERR);
	}
	
	protected abstract String getBeginningCode();
	
	protected abstract String getCorrectAnswer();
	
	protected abstract String getQuestionText();
	
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
		sb.append(createJavaDocComment());
		sb.append("public class ").append(MAIN_CLASS).append(" {").append(LF);
		sb.append(INDENT).append("public static void main(String[] args) {").append(LF);
		sb.append(INDENT).append(INDENT).append(getBeginningCode()).append(LF);
		sb.append(INDENT).append("}").append(LF);
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
		if (text.length() > 1 && text.endsWith(LF)) {
			return text.substring(0, text.length() - 1); 
		}
		return text;
	}
	
	/**
	 * 以下のようにJavaDocコメントを作成する
	 * <pre>{@code
	 * ---------------------
	 * 問題名(クラス名)
	 * 問題文
	 * ---------------------
	 * }</pre>
	 * @return 作成したJavaDocコメント
	 */
	private String createJavaDocComment() {
		
		final String startComment = "/**";
		final String endComment = "*/";
		final String bol = "* ";
		
		String content = getQuestionText();
		StringBuilder sb = new StringBuilder(startComment).append(LF);
		
		sb.append(bol).append(getClass().getSimpleName()).append(LF);
		
		// 問題文がある場合、問題文を表示
		if (content != null && !content.isBlank()) {
			content.lines().forEach(t -> {
				sb.append(bol).append(t).append(LF);
			});
		}

		sb.append(endComment).append(LF);
		return sb.toString();
	}
}
