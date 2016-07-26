package com.insurance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.SAXException;

public class XMLUtil {

	static Log logger = LogFactory.getLog(XMLUtil.class);

	public static Document loadXMLFromFile(String xmlFilePath) {
		File file = new File(xmlFilePath);

		return loadXMLFromFile(file);
	}

	public static Document loadXMLFromFile(File xmlFile) {
		Document document = null;
		if (xmlFile.exists() && xmlFile.isFile()) {
			try {
				SAXReader reader = new SAXReader();
				reader.setEncoding("utf-8");
				document = reader.read(xmlFile);

			} catch (DocumentException e) {
				logger.error("XML文档解析错误。");
			}
		}

		return document;
	}

	/**
	 * 通过文件加载XML<编码格式>
	 * 
	 * @param xmlFile
	 * @param codeType
	 *            编码格式
	 * @return
	 */
	public static Document loadXMLFromFile(File xmlFile, String codeType) {
		Document document = null;
		if (xmlFile.exists() && xmlFile.isFile()) {
			try {
				SAXReader reader = new SAXReader();
				reader.setEncoding(codeType);
				document = reader.read(xmlFile);

			} catch (DocumentException e) {
				logger.error("XML文档解析错误。");
			}
		}

		return document;
	}

	/**
	 * 从指定XML文件中获取内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param encoding
	 *            编码类型
	 * @return 内容字符串
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static String getXMLFromFile(String filePath, String encoding) throws IOException {
		InputStream is = new FileInputStream(filePath);
		if (is == null)
			return null;

		InputStreamReader isr;
		if (encoding == null || encoding.trim().equals(""))
			isr = new InputStreamReader(is);
		else
			isr = new InputStreamReader(is, encoding);

		BufferedReader reader = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append(System.getProperty("line.separator"));
		}

		reader.close();
		isr.close();
		is.close();

		return sb.toString();
	}

	/**
	 * 获取指定元素下所有CDATA类型结点的列表集合
	 * 
	 * @param element
	 *            指定结点元素
	 * @return CDATA结点类型列表
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List<CDATA> getElementCDATAList(Element element) {
		if (element == null)
			return null;

		List<CDATA> dataList = new ArrayList<CDATA>();
		for (Iterator it = element.nodeIterator(); it.hasNext();) {
			Node dataNode = (Node) it.next();
			if (dataNode.getNodeType() == CDATA.CDATA_SECTION_NODE) {
				dataList.add((CDATA) dataNode);
			}
		}
		return dataList;
	}

	public static Document LoadXMLFromString(String xmlstr) throws DocumentException {
		if (xmlstr == null || xmlstr == "")
			return null;

		Document document = DocumentHelper.parseText(xmlstr);

		return document;
	}

	public static boolean saveXMLToFile(Document doc, String xmlFile, String encoding) {
		XMLWriter writer;

		try {
			// 格式化输出
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 指定XML编码
			format.setEncoding(encoding);

			writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlFile), encoding), format);
			writer.write(doc);
			// System.out.println(doc.asXML());
			writer.close();
		} catch (Exception ex) {
			logger.error("XML文件保存失败！");
			return false;
		}

		return true;
	}

	/**
	 * 根据Schema验证XML文件的架构是否正确
	 * 
	 * @param xmlFile
	 * @param xsdFile
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static boolean validateSchema(String xmlFile, String xsdFile) throws SAXException, IOException,
			DocumentException {
		boolean flag = true;

		SAXReader reader = new SAXReader();
		reader.setValidation(true);

		reader.setFeature("http://xml.org/sax/features/validation", true);
		reader.setFeature("http://apache.org/xml/features/validation/schema", true);
		reader.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", xsdFile);

		XMLErrorHandler errorHandler = new XMLErrorHandler();
		reader.setErrorHandler(errorHandler);

		reader.read(xmlFile);
		XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());

		if (errorHandler.getErrors().hasContent()) {
			flag = false;
			writer.write(errorHandler.getErrors());
		} else {
			logger.info("Xml is OK!.");
		}

		return flag;

	}

	/**
	 * 修改Element的文本。（当Element存在，直接修改;否则创建新的Element再修改值）
	 * 
	 * @param parentElement
	 *            上级Element
	 * @param elementName
	 *            要修改的Element名称
	 * @param text
	 *            文本值
	 */
	public static void editElementText(Element parentElement, String elementName, String text) {
		if (parentElement == null || elementName == null)
			return;

		Element element = parentElement.element(elementName);
		if (element == null)
			element = parentElement.addElement(elementName);

		element.setText(text);
	}

	public static void addElementText(Element parentElement, String elementName, String attributeName, String value) {
		if (parentElement == null || elementName == null)
			return;

		Element element = parentElement.addElement(elementName);
		element.addAttribute(attributeName, value);

	}

	/**
	 * 修改Element的属性值。（当Element存在，直接修改；否则创建新的Attribute再修改。若传null给已有属性，则删除现有属性）
	 * 
	 * @param element
	 *            当前Element
	 * @param attributeName
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public static void editAttributeValue(Element element, String attributeName, String value) {
		if (element == null || attributeName == null)
			return;

		Attribute attribute = element.attribute(attributeName);
		if (attribute == null) {
			if (value != null) // value非null则添加属性
				element.addAttribute(attributeName, value);
		} else {
			if (value == null) // value为null，删除attribute
				element.remove(attribute);
			else
				// 非null，修改值(value可为Empty)
				attribute.setValue(value);
		}
	}

	/**
	 * 获取指定名称元素结点的文本内容 首先通过父结点元素检查该名称元素是否存在，若存在则取该结点文本内容，否则返回null
	 * 
	 * @param parentElement
	 *            父级结点元素
	 * @param elementName
	 *            结点元素名
	 * @return 结点文本内容
	 */
	public static String getElementText(Element parentElement, String elementName) {
		if (parentElement == null || elementName == null)
			return null;

		String text = null;
		Element element = parentElement.element(elementName);

		if (element != null) {
			text = element.getTextTrim(); // 获取Trim后的文本，便于类型转换
		}
		return text;
	}

	/**
	 * 获取指定元素的属性值 如果结点无该属性，则返回null。
	 * 
	 * @param element
	 *            结点元素
	 * @param attributeName
	 *            属性名称
	 * @return 属性值
	 */
	public static String getAttributeValue(Element element, String attributeName) {
		if (element == null || attributeName == null)
			return null;

		String value = null;
		Attribute attribute = element.attribute(attributeName);
		if (attribute != null) {
			value = attribute.getValue();
		}
		return value;
	}

	/**
	 * 添加指定名称的单一结点元素
	 * 
	 * @param parentElement
	 *            父结点元素
	 * @param elementName
	 *            元素名称
	 */
	public static Element addSingleElement(Element parentElement, String elementName) {
		if (parentElement == null || elementName == null)
			return null;

		Element element = parentElement.element(elementName);

		// 检查是否存在，不存在则创建
		if (element == null)
			element = parentElement.addElement(elementName);

		return element;
	}

	/**
	 * 删除指定元素 首先根据父结点元素检查该名称元素是否存在，若存在则删除该元素
	 * 
	 * @param parentElement
	 *            父结点元素
	 * @param elementName
	 *            元素名称
	 */
	public static void removeElement(Element parentElement, String elementName) {
		if (parentElement == null || elementName == null)
			return;

		Element element = parentElement.element(elementName);

		if (element != null)
			parentElement.remove(element);
	}

	/**
	 * 删除指定元素的属性值 首先检查元素及属性是否存在，若存在则删除。
	 * 
	 * @param element
	 *            结点元素
	 * @param attributeName
	 *            属性名称
	 */
	public static void removeAttribute(Element element, String attributeName) {
		if (element == null || attributeName == null)
			return;

		Attribute attribute = element.attribute(attributeName);
		if (attribute != null)
			element.remove(attribute);
	}

	/**
	 * 通过XPath表达式从DOM文档中获取(单一)元素
	 * 
	 * @param doc
	 * @param nodePathExpression
	 * @return
	 */
	public static Element getSingleElementByPathExp(Document doc, String nodePathExpression) {
		Element element = null;
		Node node = null;

		try {
			node = doc.selectSingleNode(nodePathExpression);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (node != null)
			element = (Element) node;

		return element;
	}

}
