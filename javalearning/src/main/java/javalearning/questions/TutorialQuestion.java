package javalearning.questions;

import javalearning.core.stream.LearningInputStream;
import javalearning.core.stream.LearningPrintStream;

public class TutorialQuestion extends AbstractQuestion {

	public TutorialQuestion(LearningPrintStream consoleStream, LearningPrintStream outStream,
			LearningPrintStream errStream, LearningInputStream inputStream) {
		super(consoleStream, outStream, errStream, inputStream);
	}

	@Override
	protected String getBeginningCode() {
		return "System.out.println(\"hello javaLearningSystems\");";
	}

	@Override
	protected String getCorrectAnswer() {
		return "";
	}

	@Override
	protected String getQuestionText() {
		StringBuilder sb = new StringBuilder();
		sb.append("ようこそ javaLearningSystemへ！").append(LF);
		sb.append("このシステムはEclipseなどの開発環境がなくても手軽にJavaを学べます。").append(LF);
//		sb.append(LF);
		return sb.toString();
	}

	@Override
	public String getQuestionName() {
		return "使い方";
	}

}
