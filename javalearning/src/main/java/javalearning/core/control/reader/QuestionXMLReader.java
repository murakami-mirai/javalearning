package javalearning.core.control.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javalearning.core.stream.LearningPrintStream;
import javalearning.questions.AbstractQuestion;

public class QuestionXMLReader {

	/** ロガー */
	private static final Logger LOGGER = LogManager.getLogger(QuestionXMLReader.class);
	
	// 各タグ名
	private static final String ROOT_TAG_NAME = "Questions";
	private static final String QUESTION_TAG_NAME = "Question";
	private static final String CODE_TAG_NAME = "Code";
	private static final String TEXT_TAG_NAME = "Text";
	private static final String ANSWER_TAG_NAME = "Answer";
	
	private final File xmlFile;
	private final LearningPrintStream consoleStream;
	private final LearningPrintStream outStream;
	private final LearningPrintStream errStream;
	
	public QuestionXMLReader(String fileName, LearningPrintStream consoleStream, 
			LearningPrintStream outStream, LearningPrintStream errStream) {
		this.xmlFile = new File(fileName);
		this.consoleStream = consoleStream;
		this.outStream = outStream;
		this.errStream = errStream;
	}
	
	/**
	 * XMLファイルを読み込んで、
	 * @return
	 * @throws QuestionXMLReaderException
	 */
	public AbstractQuestion[] getQuestions() throws QuestionXMLReaderException {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
											  .newDefaultInstance()
											  .newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			Element root = document.getDocumentElement();
			
			// ルートタグが指定以外の場合、例外
			if (root == null || !ROOT_TAG_NAME.equals(root.getTagName())) {
				StringBuilder sb = new StringBuilder("ルートタグは「");
				sb.append(ROOT_TAG_NAME);
				sb.append("」ではなければなりません。");
				throw new QuestionXMLReaderException(sb.toString());
			}
			
			
			NodeList questions = root.getElementsByTagName(QUESTION_TAG_NAME);
			if (questions == null) {
				return new AbstractQuestion[0];
			}
			List<AbstractQuestion> list = new ArrayList<>();
			
			System.out.println(root.getTagName());
			for (int i = 0; i < questions.getLength(); i++) {
				Node node = questions.item(i);
				System.out.println(node.getNodeName());
				
				if (node instanceof Element) {
					Element e = (Element)node;
					
				}
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
	
//	private AbstractQuestion createQuestion(Node node) throws QuestionXMLReaderException {
//		
//	}
//	
	private String getContentByTag(Element element, String tagName, boolean mutable, boolean require) throws QuestionXMLReaderException {
		NodeList tags = element.getElementsByTagName(tagName);
		if ((tags == null || tags.getLength() < 1 )&& require) {
			StringBuilder sb = new StringBuilder("「");
			sb.append(tagName);
			sb.append("」");
			sb.append("は設定してください");
			throw new QuestionXMLReaderException(sb.toString());			
		}
		
		if (!mutable && tags.getLength() > 1) {
			StringBuilder sb = new StringBuilder("「");
			sb.append(tagName);
			sb.append("」");
			sb.append("は複数サポートされていません");
			throw new QuestionXMLReaderException(sb.toString());
		}
	}
}
