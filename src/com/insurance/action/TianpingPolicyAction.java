package com.insurance.action;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 天平接口类
 * 
 * @author huzhihong
 * 
 *         创建日期：2015年6月23日
 */
public class TianpingPolicyAction extends BaseAction {
	private static final long serialVersionUID = -383965662034603304L;

	// 测试
	public static void main(String args[]) {
//		readXmlByDom();
		ServletActionContext.getServletContext().getRealPath("");
	}

	/**
	 * 车型模糊查询
	 * @return 查询是否成功
	 */
	public String vehicleModelFuzzyquery() {

		return null;
	}

	/**
	 * 读取XML
	 */
	public static void readXmlByDom() {
		long lasting = System.currentTimeMillis();

		try {
			File file = new File("config\\test.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuder = dbf.newDocumentBuilder();
			Document doc = docbuder.parse(file);
			NodeList nl = doc.getElementsByTagName("book");
			for (int i = 0; i < nl.getLength(); i++) {
				String bookName = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
				String bookAuthor = doc.getElementsByTagName("author").item(i).getFirstChild().getNodeValue();
				String bookPageCount = doc.getElementsByTagName("pageCount").item(i).getFirstChild().getNodeValue();
				String bookPrintDate = doc.getElementsByTagName("printDate").item(i).getFirstChild().getNodeValue();
				String bookPrice = doc.getElementsByTagName("price").item(i).getFirstChild().getNodeValue();
				String bookEmail = "";
				NamedNodeMap nnm = nl.item(i).getAttributes();
				for (int j = 0; j < nnm.getLength(); j++) {
					bookEmail = nnm.item(j).getNodeValue();
				}
				System.out.println("");
				System.out.println("书    名 ：《" + bookName + "》");
				System.out.println("作    者 ： " + bookAuthor + "");
				System.out.println("出版日期 ： " + bookPrintDate + "");
				System.out.println("价    格 ： " + bookPrice + " 元");
				System.out.println("页    数 ： " + bookPageCount + " 页");
				System.out.println("电子信箱 ： " + bookEmail + "");
				System.out.println("---------------------");
			}
			long ending = System.currentTimeMillis();
			System.out.println(ending - lasting + "毫秒");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
