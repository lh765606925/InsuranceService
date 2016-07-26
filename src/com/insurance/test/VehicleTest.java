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
	 * @category 主方法测试用
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println("*******************************************************************************");
//		System.out.println("测试接口：Test_V010");
//		System.out.println("*******************************************************************************");
//		Test_V010();
//		Test_V011();
//		System.out.println("*******************************************************************************");
//		System.out.println("测试接口：Test_V020");
//		System.out.println("*******************************************************************************");
//		Test_V020();
//		System.out.println("*******************************************************************************");
//		System.out.println("测试接口：Test_V030");
//		System.out.println("*******************************************************************************");
//		Test_V030();
//		System.out.println("*******************************************************************************");
//		System.out.println("测试接口：Test_V040");
//		System.out.println("*******************************************************************************");
//		Test_V040();
//		System.out.println("*******************************************************************************");
//		System.out.println("测试接口：Test_V050");
//		System.out.println("*******************************************************************************");
//		Test_V050();
	}

	@SuppressWarnings("unused")
	private static String Test_V010() throws Exception {
		String vehicleString = "{\"vehicleName\":\"1234\",\"VehicleFrameNo\":\"LV3LD830473894821\"}";
		Vehicle vehicle = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile("V010");
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 读取文档中元素
		Element bodyvehicleElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Body/Vehicle");
		// 修改元素
		if (vehicle.getVehicleName() != null)
			XMLUtil.editElementText(bodyvehicleElement, "VehicleName", vehicle.getVehicleName());
		if (vehicle.getVehicleFrameNo() != null)
			XMLUtil.editElementText(bodyvehicleElement, "VehicleFrameNo", vehicle.getVehicleFrameNo());
		if (vehicle.getAutoModelCode() != null)
			XMLUtil.editElementText(bodyvehicleElement, "AutoModelCode", vehicle.getAutoModelCode());
		if (vehicle.getUsageAttributeCode() != null)
			XMLUtil.editElementText(bodyvehicleElement, "UsageAttributeCode", vehicle.getUsageAttributeCode());
		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		// System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V020() throws Exception {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile("V020");
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		// System.out.println(docString);
		// 将文档转换成字节流
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
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile("V030");
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	private static String Test_V040() throws Exception {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile("V040");
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
//				ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V050() throws Exception {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile("V050");
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
//		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	@SuppressWarnings("unused")
	private static String Test_V011() throws Exception {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile("V011");
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
//		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	/**
	 * @category 直接获取XML里面的基本数据
	 * @param flieName
	 *            XML文件名
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String getBaseMsg(String flieName) throws Exception {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		File file = getXmlTempleteFile(flieName);
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", propertiesUtil.readValue("TPAIC_User"));
		XMLUtil.editElementText(headerElement, "Password", propertiesUtil.readValue("TPAIC_Password"));

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		// System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	/**
	 * @方法： 获取天平系统返回报文
	 * @param data
	 *            字节流
	 * @return
	 */
	public static String getXMLMessage(byte[] data) {

		String msg = "";
		// 字节流转换成string 打印
		// String content = new String(data);
		// System.out.println("开始调用服务器:");
		// System.out.println(content);

		try {
			// 读取配置文件
			PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
			// 读取配置文件中TPAIC_smsUrl的值
			String smsUrl = propertiesUtil.readValue("TPAIC_smsUrl");
			URL url = new URL(smsUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			System.out.println("请求文件：" + data.toString());
			// 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
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
			// 返回写入到此连接的输出流
			outStream.write(data);
			outStream.close();
			// 关闭流
			out.flush();
			out.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// BufferedReader转XMLString
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				msg = msg + temp;
			}
			msg = msg.replace("  ", "");
			msg = msg.replace("<Packet type=\"RESPONSE\" version=\"1.0\">", "<Packet>");
			System.out.println("打印返回XML：");
			System.out.println(msg);
			// XML转JSON
			msg = XmlJSON.xml2JSON(msg);
			// 返回XML
			System.out.println("打印返回JSON：");
			System.out.println(msg);
			reader.close();
			conn.disconnect();

			// 保存调用http服务后的响应信息// 如果请求响应码是200，则表示成功
			// if (conn.getResponseCode() == 200) {
			// // HTTP服务端返回的编码是UTF-8,故必须设置为UTF-8,保持编码统一,否则会出现中文乱码
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader((InputStream) conn.getInputStream(), "GBK"));
			// msg = in.readLine();
			// in.close();
			//
			// }
			// conn.disconnect();
			// 断开连接
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
	 * 读取XML模版
	 * 
	 * @param xmlName
	 * @return
	 * @throws IOException
	 */
	private static File getXmlTempleteFile(String xmlName) throws IOException {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 读取配置localpath数据
		String localpath = propertiesUtil.readValue("localpath");
		// 拼接模版文件路径
		String filePath = localpath + "/tpaic_xml/" + xmlName + ".xml";
		// 打印模版文件路径
		System.out.println("打印模版文件路径:" + filePath);
		// 读取模版文件
		File file = new File(filePath);
		// 文件
		return file;
	}

}
