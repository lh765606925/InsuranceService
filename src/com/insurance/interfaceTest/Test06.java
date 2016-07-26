package com.insurance.interfaceTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test06 {
	public static void test06() {
		InputStream is = null;
		try {
			// is =
			// TestStax.class.getClassLoader().getResourceAsStream("books.xml");
			// 创建文档处理对象
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// 通过DocumentBuilder创建doc的文档对象
			Document doc = db.parse(new FileInputStream(new File(
					"E:/WorkSpace2015/InsuranceService/src/resouse/100083.xml")));
			// 创建XPath
			XPath xpath = XPathFactory.newInstance().newXPath();
			// 第一个参数就是xpath,第二参数就是文档
			NodeList list = (NodeList) xpath.evaluate("//book[@category='WEB']", doc, XPathConstants.NODESET);
			for (int i = 0; i < list.getLength(); i++) {
				// 遍历输出相应的结果
				Element e = (Element) list.item(i);
				System.out.println(e.getElementsByTagName("title").item(0).getTextContent());
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test07() {
		try {
			XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
			xsw.writeStartDocument("UTF-8", "1.0");
			xsw.writeEndDocument();
			String ns = "http://11:dd";
			xsw.writeStartElement("nsadfsadf", "person", ns);
			xsw.writeStartElement(ns, "id");
			xsw.writeCharacters("1");
			xsw.writeEndElement();
			xsw.writeEndElement();
			xsw.flush();
			xsw.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	public static void test08() {
		InputStream is = null;
		try {
			// is =
			// TestStax.class.getClassLoader().getResourceAsStream("books.xml");
			// 创建文档处理对象
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// 通过DocumentBuilder创建doc的文档对象

			Document doc = db.parse(new FileInputStream(new File(
					"E:/WorkSpace2015/InsuranceService/src/resouse/100083.xml")));
			// 创建XPath
			XPath xpath = XPathFactory.newInstance().newXPath();
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			tran.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tran.setOutputProperty(OutputKeys.INDENT, "yes");
			// 第一个参数就是xpath,第二参数就是文档
			NodeList list = (NodeList) xpath.evaluate("//book[title='Learning XML']", doc, XPathConstants.NODESET);
			// 获取price节点
			Element be = (Element) list.item(0);
			Element e = (Element) (be.getElementsByTagName("price").item(0));
			e.setTextContent("333.9");
			Result result = new StreamResult(System.out);
			// 通过tranformer修改节点
			tran.transform(new DOMSource(doc), result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
