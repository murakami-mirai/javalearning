package javalearning.core.control.reader;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

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
import javalearning.core.stream.LearningInputStream;
import javalearning.core.stream.LearningPrintStream;
import javalearning.questions.AbstractQuestion;
import javalearning.questions.ReadableQuestion;

public class QuestionXMLReader {

	/** ロガー */
	private static final Logger LOGGER = LogManager.getLogger(QuestionXMLReader.class);
	private static final String DELIMITER = "\n";
	
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
		Map<String, AbstractQuestion> questionMap = new TreeMap<>();
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
											  .newDefaultInstance()
											  .newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			Element root = document.getDocumentElement();

			// ルートタグが指定以外の場合、例外
			if (root == null || !Tag.ROOT_TAG.isEqualsByTagName(root.getTagName())) {
				StringBuilder sb = new StringBuilder("ルートタグは「");
				sb.append(Tag.ROOT_TAG.getTagName());
				sb.append("」ではなければなりません。");
				throw new QuestionXMLReaderException(sb.toString());
			}

			// Questionタグを取得する
			NodeList questions = root.getElementsByTagName(Tag.QUESTION_TAG.getTagName());
			if (questions == null) {
				return new AbstractQuestion[0];
			}

			// Questionタグの中にあるタグからAbstractQuestionを作成し、リストに格納する
			for (int i = 0; i < questions.getLength(); i++) {
				Node node = questions.item(i);
				LOGGER.info(node.getNodeName());

				if (node instanceof Element) {
					Element e = (Element)node;
					AbstractQuestion q = createQuestion(e);
					questionMap.put(q.getQuestionName(), q);
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

		return questionMap.values().toArray(new AbstractQuestion[questionMap.values().size()]);
	}

	private AbstractQuestion createQuestion(Element element) throws QuestionXMLReaderException {
		
		LearningInputStream inputStream = new LearningInputStream(getContentByTag(element, Tag.INPUT_TAG));
		
		ReadableQuestion question = new ReadableQuestion(consoleStream, outStream, errStream, inputStream);
		question.setBeginningCode(getContentByTag(element, Tag.CODE_TAG));
		question.setCorrectAnswer(getContentByTag(element, Tag.ANSWER_TAG));
		question.setQuestionText(getContentByTag(element, Tag.TEXT_TAG));
		question.setQuestionName(getContentByTag(element, Tag.NAME_TAG));

		return question;
	}

	private String getContentByTag(Element element, Tag tag) throws QuestionXMLReaderException {
		NodeList tags = element.getElementsByTagName(tag.getTagName());
		if ((tags == null || tags.getLength() < 1 )&& tag.isRequire()) {
			StringBuilder sb = new StringBuilder("「");
			sb.append(tag.tagName);
			sb.append("」");
			sb.append("は設定してください");
			throw new QuestionXMLReaderException(sb.toString());
		}

		if (!tag.isMutable() && tags.getLength() > 1) {
			StringBuilder sb = new StringBuilder("「");
			sb.append(tag.getTagName());
			sb.append("」");
			sb.append("は複数サポートされていません");
			throw new QuestionXMLReaderException(sb.toString());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tags.getLength(); i++) {
			Node node = tags.item(i);
			if (!(node instanceof Element)) {
				continue;
			}
			Element e = (Element)node;
			String content = e.getTextContent();
			if (content == null || content.isBlank()) {
				continue;
			}
			if (i != 0) {
				sb.append(DELIMITER);
			}
			sb.append(content.trim());
		}
		return sb.toString().trim();
	}

	private enum Tag {
		ROOT_TAG("Questions", false, true),
		QUESTION_TAG("Question", true, true),
		CODE_TAG("Code", false, false),
		TEXT_TAG("Text", false, true),
		ANSWER_TAG("Answer", false, true),
		NAME_TAG("Name", false, true),
		INPUT_TAG("Input", true, false);

		private String tagName;
		private boolean mutable;
		private boolean require;

		private Tag(String tagName, boolean mutable, boolean require) {
			this.tagName = tagName;
			this.mutable = mutable;
			this.require = require;
		}

		public String getTagName() {
			return tagName;
		}

		public boolean isMutable() {
			return mutable;
		}

		public boolean isRequire() {
			return require;
		}

		public boolean isEqualsByTagName(String tagName) {
			return tagName.equals(tagName);
		}
	}
}
