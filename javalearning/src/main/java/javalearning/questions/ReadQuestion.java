package javalearning.questions;

import javalearning.core.stream.LearningPrintStream;

public class ReadQuestion extends AbstractQuestion {

	private String beginningCode;
	private String correctAnswer;
	private String questionText;
	
	public ReadQuestion(LearningPrintStream consoleStream, LearningPrintStream outStream, LearningPrintStream errStream) {
		super(consoleStream, outStream, errStream);
	}

	@Override
	protected String getBeginningCode() {
		return beginningCode;
	}

	@Override
	protected String getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	protected String getQuestionText() {
		return questionText;
	}

}
