package com.insurance.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
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

import com.insurance.model.Vehicle;
import com.insurance.util.DateUtil;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.Return_Code;
import com.insurance.util.XMLUtil;
import com.insurance.util.XmlConverUtil;
import com.insurance.util.XmlJSON;

public class VehicleTest {

	/**
	 * @name main
	 * @category ������������
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println("*******************************************************************************");
//		System.out.println("���Խӿڣ�Test_V010");
//		System.out.println("*******************************************************************************");
//		Test_V010();
//		Test_V011();
//		System.out.println("*******************************************************************************");
//		System.out.println("���Խӿڣ�Test_V020");
//		System.out.println("*******************************************************************************");
//		Test_V020();
//		System.out.println("*******************************************************************************");
//		System.out.println("���Խӿڣ�Test_V030");
//		System.out.println("*******************************************************************************");
//		Test_V030();
//		System.out.println("*******************************************************************************");
//		System.out.println("���Խӿڣ�Test_V040");
//		System.out.println("*******************************************************************************");
//		Test_V040();
//		System.out.println("*******************************************************************************");
//		System.out.println("���Խӿڣ�Test_V050");
//		System.out.println("*******************************************************************************");
//		Test_V050();
	}

	@SuppressWarnings("unused")
	private static String Test_V010() throws Exception {
		String vehicleString = "{\"vehicleName\":\"1234\",\"VehicleFrameNo\":\"LV3LD830473894821\"}";
		Vehicle vehicle = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile("V010");
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// ��ȡ�ĵ���Ԫ��
		Element bodyvehicleElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Body/Vehicle");
		// �޸�Ԫ��
		if (vehicle.getVehicleName() != null)
			XMLUtil.editElementText(bodyvehicleElement, "VehicleName", vehicle.getVehicleName());
		if (vehicle.getVehicleFrameNo() != null)
			XMLUtil.editElementText(bodyvehicleElement, "VehicleFrameNo", vehicle.getVehicleFrameNo());
		if (vehicle.getAutoModelCode() != null)
			XMLUtil.editElementText(bodyvehicleElement, "AutoModelCode", vehicle.getAutoModelCode());
		if (vehicle.getUsageAttributeCode() != null)
			XMLUtil.editElementText(bodyvehicleElement, "UsageAttributeCode", vehicle.getUsageAttributeCode());
		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V020() throws Exception {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile("V020");
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V030() throws Exception {
		String vehicleString = "{\"vehicleName\":123}";
		Vehicle vehicle = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile("V030");
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	private static String Test_V040() throws Exception {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile("V040");
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
//				ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V050() throws Exception {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile("V050");
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
//		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V011() throws Exception {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile("V011");
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
//		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	/**
	 * @category ֱ�ӻ�ȡXML����Ļ�������
	 * @param flieName
	 *            XML�ļ���
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String getBaseMsg(String flieName) throws Exception {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		File file = getXmlTempleteFile(flieName);
		// ����doc���ĵ�����,����XML<GBK����>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// ��ȡ�ĵ���Ԫ��
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// �޸�Ԫ��
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	/**
	 * @������ ��ȡ��ƽϵͳ���ر���
	 * @param data
	 *            �ֽ���
	 * @return
	 */
	public static String getXMLMessage(byte[] data) {

		String msg = "";
		// �ֽ���ת����string ��ӡ
		// String content = new String(data);
		// System.out.println("��ʼ���÷�����:");
		// System.out.println(content);

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
			while ((temp = reader.readLine()) != null) {
				msg = msg + temp;
			}
			msg = msg.replace("  ", "");
			msg = msg.replace("<Packet type=\"RESPONSE\" version=\"1.0\">", "<Packet>");
			System.out.println("��ӡ����XML��");
			System.out.println(msg);
			// XMLתJSON
			msg = XmlJSON.xml2JSON(msg);
			// ����XML
			System.out.println("��ӡ����JSON��");
			System.out.println(msg);
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
		return msg;
	}

	/**
	 * ��ȡXMLģ��
	 * 
	 * @param xmlName
	 * @return
	 * @throws IOException
	 */
	private static File getXmlTempleteFile(String xmlName) throws IOException {
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
		// �ļ�
		return file;
	}

}
