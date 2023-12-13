package javalearning.learning.core;

import javalearning.learning.core.steam.LearningPrintStream;

public class Question1 extends AbstractQuestion {

	public Question1(LearningPrintStream outStream, LearningPrintStream errStream) {
		super(outStream, errStream);
	}

	@Override
	protected String getBeginningCode() {
		return "System.out.println(\"実行\");";
	}

	@Override
	protected String getCorrectAnswer() {
		return String.valueOf(3);
	}

}
