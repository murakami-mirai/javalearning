package javalearning.questions;

import javalearning.core.stream.LearningPrintStream;

public class ReadableQuestion extends AbstractQuestion {

	private String questionName;
	private String beginningCode;
	private String correctAnswer;
	private String questionText;
	
	public ReadableQuestion(LearningPrintStream consoleStream, 
			LearningPrintStream outStream, 
			LearningPrintStream errStream) {
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
	
	@Override
	protected String getQuestionName() {
		return questionName;
	}

	public void setBeginningCode(String beginningCode) {
		this.beginningCode = beginningCode;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
}
