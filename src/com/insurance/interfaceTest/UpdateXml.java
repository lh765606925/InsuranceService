package com.insurance.interfaceTest;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class UpdateXml {
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/** 将document中的内容写入文件中 */
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			// transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public static Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	/**
	 * 演示修改文件的具体某个节点的值
	 * @throws XPathExpressionException 
	 */
	public static void xmlUpdateDemo() throws XPathExpressionException {
		Document document = load("c://Message.xml");
		// 创建XPath对象
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node node = (Node) xpath.evaluate("/*", document, XPathConstants.NODE);
		System.out.println(node.getNodeName() + "--------" + node.getNodeValue());
		
		NodeList nodeList = (NodeList) xpath.evaluate("/rss/channel/*", document, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		
		Node root = document.getDocumentElement();
		/** 如果root有子元素 */
		if (root.hasChildNodes()) {
			/** ftpnodes */
			NodeList ftpnodes = root.getChildNodes();
			/** 循环取得ftp所有节点 */
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();
				for (int k = 0; k < ftplist.getLength(); k++) {
					Node subnode = ftplist.item(k);
					/** 删除ftp-chn节点 */
					if (subnode.getNodeType() == Node.ELEMENT_NODE && subnode.getNodeName() == "ftp-chn") {
						ftpnodes.item(i).removeChild(subnode);
					}
					/** 修改ftp-host的值为 192.168.0.1 */
					if (subnode.getNodeType() == Node.ELEMENT_NODE && subnode.getNodeName() == "status") {
						subnode.getFirstChild().setNodeValue("2");
					}
				}

			}
		}

		doc2XmlFile(document, "c://Message.xml");
	}
	public static void main(String args[]) throws Exception {
		UpdateXml.xmlUpdateDemo();
	}
}