package javalearning.questions;

import javalearning.core.stream.LearningPrintStream;

public class Question1 extends AbstractQuestion {

	public Question1(LearningPrintStream outStream, LearningPrintStream errStream) {
		super(outStream, errStream);
	}

	@Override
	protected String getBeginningCode() {
		return "System.out.println();";
	}

	@Override
	protected String getCorrectAnswer() {
		return String.valueOf(3);
	}

	@Override
	protected String getQuestionText() {
		return "「3」を表示するプログラムを作成して下さい";
	}

}