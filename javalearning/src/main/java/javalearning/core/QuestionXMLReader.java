package javalearning.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javalearning.questions.AbstractQuestion;

public class QuestionXMLReader {

	private static final Logger LOGGER = LogManager.getLogger(QuestionXMLReader.class);
	private File xmlFile;
	
	public QuestionXMLReader(String fileName) {
		this.xmlFile = new File(fileName);
	}
	
	public AbstractQuestion[] getQuestions() {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
											  .newDefaultInstance()
											  .newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			Element root = document.getDocumentElement();
			
			System.out.println(root.getTagName());
		} catch (ParserConfigurationException e) {
			LOGGER.error(e);
		} catch (SAXException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		
		return null;
	}
}
