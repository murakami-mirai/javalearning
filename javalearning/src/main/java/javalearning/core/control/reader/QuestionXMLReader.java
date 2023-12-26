package javalearning.core.control.reader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javalearning.core.exception.QuestionXMLReaderException;
import javalearning.questions.AbstractQuestion;

public class QuestionXMLReader {

	private static final Logger LOGGER = LogManager.getLogger(QuestionXMLReader.class);
	private static final String ROOT_TAG_NAME = "Questions";
	private static final String QUESTION_TAG_NAME = "Question";
	
	
	private File xmlFile;
	
	public QuestionXMLReader(String fileName) {
		this.xmlFile = new File(fileName);
	}
	
	public AbstractQuestion[] getQuestions() throws QuestionXMLReaderException {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
											  .newDefaultInstance()
											  .newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			Element root = document.getDocumentElement();
			
			if (root == null || !ROOT_TAG_NAME.equals(root.getTagName())) {
				throw new QuestionXMLReaderException("ルートタグは「Questions」ではなければなりません。");
			}
			
			NodeList questions = root.getElementsByTagName(QUESTION_TAG_NAME);
			
			System.out.println(root.getTagName());
			for (int i = 0; i < questions.getLength(); i++) {
				Node node = questions.item(i);
				System.out.println(node.getNodeName());
			}
			
		} catch (ParserConfigurationException e) {
			LOGGER.error(e);
			throw new QuestionXMLReaderException(e);
		} catch (SAXException e) {
			LOGGER.error(e);
			throw new QuestionXMLReaderException(e);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new QuestionXMLReaderException(e);
		}
		
		return null;
	}
}
