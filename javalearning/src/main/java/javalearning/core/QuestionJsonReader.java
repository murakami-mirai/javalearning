package javalearning.core;

import java.io.File;

import javalearning.questions.AbstractQuestion;

public class QuestionJsonReader {

	private String fileName;
	
	public QuestionJsonReader(String fileName) {
		this.fileName = fileName;
	}
	
	public AbstractQuestion[] getQuestions() {
		File file = getQuestionJson();
		
		return null;
	}
	
	private File getQuestionJson() {
		return new File(fileName);
	}
}
