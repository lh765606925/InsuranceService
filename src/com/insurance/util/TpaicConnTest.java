package com.insurance.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


import org.dom4j.Document;
import org.dom4j.Element;

public class TpaicConnTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws IOException {

		// ģ������-V010
		String tempName = "V010";
		System.out.println("ִ�нӿ�ģ�棺" + tempName);
		// �������ƶ�ȡXMLģ��
		byte[] data = getXmlTemplete(tempName);

		// �ֽ���ת����string ��ӡ
		String content = new String(data);
		System.out.println("��ʼ���÷�����:");
		System.out.println(content);

		try {
			// ��ȡ�����ļ�
			PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
			// ��ȡ�����ļ���TPAIC_smsUrl��ֵ
			String smsUrl = propertiesUtil.readValue("TPAIC_smsUrl");
			URL url = new URL(smsUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			System.out.println("�����ļ���" + data.toString());
			// ʹ�� URL ���ӽ���������� DoOutput��־����Ϊ true
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("Content-Type", "text/xml;charset=GBK");
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			OutputStream outStream = conn.getOutputStream();
			// ����д�뵽�����ӵ������
			outStream.write(data);
			outStream.close();
			// �ر���
			out.flush();
			out.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// BufferedReaderתXMLString
			String temp = "";
			String s = "";
			while ((temp = reader.readLine()) != null) {
				s = s + temp;
			}
			s = s.replace(">  <", "><");
			System.out.println("��ӡ����XML��");
			System.out.println(s);
			// XMLתJSON
			s = XmlConverUtil.xmltoJson(s);
			//����XML
			System.out.println("��ӡ����JSON��");
			System.out.println(s);
			reader.close();
			conn.disconnect();

			// �������http��������Ӧ��Ϣ// ���������Ӧ����200�����ʾ�ɹ�
			// if (conn.getResponseCode() == 200) {
			// // HTTP����˷��صı�����UTF-8,�ʱ�������ΪUTF-8,���ֱ���ͳһ,����������������
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader((InputStream) conn.getInputStream(), "GBK"));
			// msg = in.readLine();
			// in.close();
			//
			// }
			// conn.disconnect();
			// �Ͽ�����
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡXMLģ��
	 * 
	 * @param xmlName
	 * @return
	 * @throws IOException
	 */
	private static byte[] getXmlTemplete(String xmlName) throws IOException {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡ����localpath����
		String localpath = propertiesUtil.readValue("localpath");
		// ƴ��ģ���ļ�·��
		String filePath = localpath + "/tpaic_xml/" + xmlName + ".xml";
		// ��ӡģ���ļ�·��
		System.out.println("��ӡģ���ļ�·��:" + filePath);
		// ��ȡģ���ļ�
		File file = new File(filePath);
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", "admin");
		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();
		// �����ֽ���
		return buffer;
	}

	@SuppressWarnings("resource")
	public byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// ȷ���������ݾ�����ȡ
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}
}
