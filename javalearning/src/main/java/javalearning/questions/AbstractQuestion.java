package javalearning.questions;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javalearning.core.control.CompileRunManger;
import javalearning.core.stream.LearningInputStream;
import javalearning.core.stream.LearningPrintStream;

public abstract class AbstractQuestion implements Runnable {
	
	/** 改行文字 */
	protected static final String LF = "\n";
	/** インデント */
	protected static final String INDENT = "\t";
	protected static final String HORIZON = "--------";
	
	/** 標準出力を保存 */
	private static final PrintStream SYSTEM_OUT = System.out;
	/** 標準エラーを保存 */
	private static final PrintStream SYSTEM_ERR = System.err;
	/** 標準入力を保存 */
	private static final InputStream SYSTEM_IN = System.in;
	/** デフォルトメインクラス */
	private static final String MAIN_CLASS = "Main";
	/** デフォルトパッケージ */
	private static final String DEFAULT_PACKAGE = "";
	
	private static final Logger LOGGER = LogManager.getLogger(AbstractQuestion.class);

	/** ラーニングシステムのコンソール出力 */
	private final LearningPrintStream consoleStream;
	/** ラーニングシステムの標準出力 */
	private final LearningPrintStream outStream;
	/** ラーニングシステムの標準エラー */
	private final LearningPrintStream errStream;
	/** ラーニングシステムの標準入力 */
	private final LearningInputStream inputStream;
	/** メインメソッドのパラメータ */
	private String[] mainParams;
	/** コンパイル対象のソースコード */
	private String sourceCode;

	/**
	 * コンストラクタ
	 * @param consoleStream コンソールストリーム
	 * @param outStream 出力ストリーム
	 * @param errStream エラー出力ストリーム
	 * @param inputStream 入力ストリーム
	 */
	public AbstractQuestion(LearningPrintStream consoleStream, 
			LearningPrintStream outStream, 
			LearningPrintStream errStream, 
			LearningInputStream inputStream) {
		mainParams = new String[0];
		this.consoleStream = consoleStream;
		this.outStream = outStream;
		this.errStream = errStream;
		this.inputStream = inputStream;
	}
	
	/**
	 * コンストラクタ
	 * @param consoleStream コンソールストリーム
	 * @param outStream 出力ストリーム
	 * @param errStream エラー出力ストリーム
	 * @param inputStream 入力ストリーム
	 * @param args メインパラメータ
	 */
	public AbstractQuestion(LearningPrintStream consoleStream, 
			LearningPrintStream outStream, 
			LearningPrintStream errStream, 
			LearningInputStream inputStream, 
			String... args) {
		mainParams = args;
		this.consoleStream = consoleStream;
		this.outStream = outStream;
		this.errStream = errStream;
		this.inputStream = inputStream;
	}
	
	public String getSourceCode() {
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

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public String getInputText() {
		return inputStream.getCharacters();
	}
	
	@Override
	public void run() {
		CompileRunManger listner = new CompileRunManger(getFQCN(), sourceCode, mainParams);
		
		setStdInOut(outStream, errStream, inputStream);
		listner.run();
		if (isSuccess()) {
			consoleStream.println(getQuestionName() + " 正解!!");
			LOGGER.info(getQuestionName() + "正解");
		} else {
			consoleStream.println(getQuestionName() + " 残念。。。");
			LOGGER.info(getQuestionName() + "不正解");
		}
		inputStream.reset();
		setStdInOut(SYSTEM_OUT, SYSTEM_ERR, SYSTEM_IN);
	}
	
	protected abstract String getBeginningCode();
	
	protected abstract String getCorrectAnswer();
	
	protected abstract String getQuestionText();
	
	public abstract String getQuestionName();
	
	protected String getMainClassName() {
		return MAIN_CLASS;
	}
	
	protected String getPackage() {
		return DEFAULT_PACKAGE;
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
	
	private void setStdInOut(PrintStream out, PrintStream err, InputStream in) {
		System.setOut(out);
		System.setErr(err);
		System.setIn(in);
	}
	
	private boolean isSuccess() {
		String correct = getCorrectAnswer();
		if (correct == null || correct.isBlank()) {
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
	 * 
	 * 実行結果
	 * ---------------------
	 * }</pre>
	 * @return 作成したJavaDocコメント
	 */
	private String createJavaDocComment() {
		
		final String startComment = "/**";
		final String endComment = "*/";
		final String bol = "* ";
		
		StringBuilder sb = new StringBuilder(startComment).append(LF);
		sb.append(bol).append(getQuestionName()).append(LF);
		
		// 問題文がある場合、問題文を表示
		String content = getQuestionText();
		if (content != null && !content.isBlank()) {
			content.lines().forEach(t -> {
				sb.append(bol).append(t).append(LF);
			});
		}
		
		// 実行結果がある場合、実行結果を表示
		String answer = trimIndentionOfLineEnd(getCorrectAnswer());
		if (answer != null && !answer.isBlank()) {
			sb.append(bol).append(LF);
			sb.append(bol).append(HORIZON).append("実行結果").append(HORIZON).append(LF);
			sb.append(bol).append(answer).append(LF);
			sb.append(bol).append(HORIZON).append(HORIZON).append(HORIZON).append(LF);
		}

		sb.append(endComment).append(LF);
		return sb.toString();
	}
}
