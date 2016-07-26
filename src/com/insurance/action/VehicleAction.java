package com.insurance.action;

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
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;

import com.insurance.model.PageBean;
import com.insurance.model.Vehicle;
import com.insurance.service.VehicleService;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;
import com.insurance.util.XMLUtil;
import com.insurance.util.XmlConverUtil;
import com.insurance.util.XmlJSON;

/**
 * @����Action
 * 
 * @author huzhihong.com
 * 
 * @�������ڣ�2015��6��15��
 */
public class VehicleAction extends BaseAction {

	private static final long serialVersionUID = -2591235987475699122L;
	@Resource
	private VehicleService vehicleService;

	private String vehicleString;// ����vehicleString
	private String vehicleName;// �������ƣ�����ģ����ѯ
	private String vehicleFrameNo;// ���ܺţ�������ģ����ѯ
	private String autoModelCode;// ���ͱ���
	private String usageAttributeCode;// ʹ������
	private String requestJSON;// ����JSON��
	private String applicantString;// Ͷ������Ϣ
	private String insuredString;// Ͷ������Ϣ
	private String vehicleOwnerString;// 3.3.1.5. ��ʻ֤����
	private String driverListString;// 3.3.1.6. ��ʻԱ��Ϣ

	/**
	 * ��ӳ�����Ϣ
	 * 
	 * @param vehicleString
	 * @return result
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String result = vehicleService.addVehicle(vehicleString);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return result;
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		JSONObject result = new JSONObject();
		if (ids.contains(",")) {
			vehicleService.deletes(ids);
		} else {
			Vehicle v = vehicleService.findById(Integer.parseInt(ids));
			vehicleService.delete(v);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}

	/**
	 * �ƶ����޸�����
	 * 
	 * @throws Exception
	 */
	public void updateByMobile() throws Exception {
		String result = vehicleService.updateByMobile(vehicleString);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
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
	 * @author huzhihong.com
	 * @name ģ����ѯ��ȡ������Ϣ-V010
	 * @category ���ݳ���Ʒ���ͺŻ򳵼ܺ�, �ҵ�������ϸ��Ϣ������Ͷ����ѯʱ����淶�ĳ�����Ϣ�� ���ܺŲ�֧��ģ����ѯ
	 * @case http://localhost:8080/InsuranceService/back/vehicle_searchVehicleModel.action?vehicleString={%22vehicleName%22:123}
	 * @��ע ����ע���Сд
	 * @URIEncoding ֱ���ڵ�ַ���������ģ�����Ϊ�����������⣬���²����������
	 * @return
	 * @throws Exception
	 */
	public String searchVehicleModel() throws Exception {
		if (vehicleString == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "���� vehicleString ����Ϊ��");
			return null;
		}
		// vehicleString = new String(vehicleString.getBytes("ISO-8859-1"),
		// "UTF-8");
		System.out.println("���յ���jsonString:" + vehicleString);
		Vehicle vehicle = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
		if (vehicle == null)
			return "������ʽ����ȷ��";
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
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * @author huzhihong.com
	 * @name ��ѯ������Ϣ-V020
	 * @category ��ѯ������Ϣ-V020:���ݳ���Ʒ���ͺŻ򳵼ܺ�, �ҵ�������ϸ��Ϣ������Ͷ����ѯʱ����淶�ĳ�����Ϣ�� ���ܺŲ�֧��ģ����ѯ
	 * @case  http://localhost:8080/InsuranceService/back/vehicle_searchVehicleModel.action?vehicleString={%22vehicleName%22:123}
	 * @��ע ����ע���Сд
	 * @return
	 * @throws Exception
	 */
	public String V020() throws Exception {

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

		// ��ȡ�ĵ���Ԫ��
		Element bodyvehicleElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Body/Vehicle");

		if (requestJSON != null) {
			System.out.println("���յ���requestJSON:" + requestJSON);
			Vehicle vehicle = Return_Code.getGson().fromJson(requestJSON, Vehicle.class);
			if (vehicle == null)
				return "���� requestJSON ��ʽ����ȷ��";

			// �޸�Ԫ��
			if (vehicle.getVehicleLicenceCode() != null)
				XMLUtil.editElementText(bodyvehicleElement, "VehicleLicenceCode", vehicle.getVehicleLicenceCode());
			if (vehicle.getLicenseTypeCode() != null)
				XMLUtil.editElementText(bodyvehicleElement, "LicenseTypeCode", vehicle.getLicenseTypeCode());
			if (vehicle.getVehicleBrand() != null)
				XMLUtil.editElementText(bodyvehicleElement, "VehicleBrand", vehicle.getVehicleBrand());
			if (vehicle.getFirstRegisterDate() != null)
				XMLUtil.editElementText(bodyvehicleElement, "FirstRegisterDate", vehicle.getFirstRegisterDate());
			if (vehicle.getVehicleFrameNo() != null)
				XMLUtil.editElementText(bodyvehicleElement, "VehicleFrameNo", vehicle.getVehicleFrameNo());
			if (vehicle.getEngineNo() != null)
				XMLUtil.editElementText(bodyvehicleElement, "EngineNo", vehicle.getEngineNo());
			if (vehicle.getAutoModelChnName() != null)
				XMLUtil.editElementText(bodyvehicleElement, "AutoModelChnName", vehicle.getAutoModelChnName());
			if (vehicle.getAutoModelCode() != null)
				XMLUtil.editElementText(bodyvehicleElement, "AutoModelCode", vehicle.getAutoModelCode());
		}

		// �ĵ�ת����String
		String docString = XmlConverUtil.doc2String(doc);
		// ��ӡ�ĵ�
		System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * 
	 * @name 3.3. Ͷ����ѯ
	 * @category Ͷ����ѯ
	 * @return
	 * @throws Exception
	 */
	public String V030() throws Exception {

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
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * @category ���Ѽ���
	 * @return
	 * @throws Exception
	 */
	public String V040() throws Exception {
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
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * @category ���Ѽ���У��
	 * @return
	 * @throws Exception
	 */
	public String V050() throws Exception {
		// ��ȡ�����ļ�
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// ��ȡXMLģ���ļ�
		String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
		File file = getXmlTempleteFile(method);
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
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	public String V060() {
		return null;
	}

	public String V070() {
		return null;
	}

	public String V080() {
		return null;
	}

	public String V090() {
		return null;
	}

	public String V110() {
		return null;
	}

	public String V120() {
		return null;
	}

	public String V130() {
		return null;
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

	/**
	 * @name main
	 * @category ������������
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("*******************************************************************************");
		System.out.println("���Խӿڣ�Test_V010");
		System.out.println("*******************************************************************************");
		Test_V010();
		System.out.println("*******************************************************************************");
		System.out.println("���Խӿڣ�Test_V020");
		System.out.println("*******************************************************************************");
		Test_V020();
//		 System.out.println("*******************************************************************************");
//		 System.out.println("���Խӿڣ�Test_V030");
//		 System.out.println("*******************************************************************************");
//		Test_V030();
//		System.out.println("*******************************************************************************");
//		 System.out.println("���Խӿڣ�Test_V040");
//		System.out.println("*******************************************************************************");
//		Test_V040();
	}

	@SuppressWarnings("unused")
	private static String Test_V010() throws Exception {
		String vehicleString = "{\"vehicleName\":1234}";
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
		// System.out.println(docString);
		// ���ĵ�ת�����ֽ���
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return jsonString;
	}

	private static void Test_V040() throws Exception {
		getBaseMsg("V040");
	}

	/**
	 * @category ֱ�ӻ�ȡXML����Ļ�������
	 * @param flieName
	 *            XML�ļ���
	 * @return
	 * @throws Exception
	 */
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

	@SuppressWarnings("resource")
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File("E:\\WorkSpace2015\\InsuranceService\\src\\resouse\\tpaic_xml\\" + filePath + ".xml");
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

	/**
	 * V020���Ͳ�ѯ
	 */

	/**
	 * ���г�����Ϣ��ҳ��ѯ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		long total = vehicleService.getVehicleCount();
		if (offSet >= total && total != 0) {
			offSet = offSet - liMit;
		}
		List<Vehicle> slist = vehicleService.find(new PageBean(liMit, offSet), new Object[] {});
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @return
	 */
	public String selectVehicle() {

		return null;
	}

	/**
	 * 
	 * @return
	 */

	public String searchVehicle() {

		System.out.println("this is searchVehicle!");
		return null;
	}

	public String getVehicleString() {
		return vehicleString;
	}

	public void setVehicleString(String vehicleString) {
		this.vehicleString = vehicleString;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleFrameNo() {
		return vehicleFrameNo;
	}

	public void setVehicleFrameNo(String vehicleFrameNo) {
		this.vehicleFrameNo = vehicleFrameNo;
	}

	public String getAutoModelCode() {
		return autoModelCode;
	}

	public void setAutoModelCode(String autoModelCode) {
		this.autoModelCode = autoModelCode;
	}

	public String getUsageAttributeCode() {
		return usageAttributeCode;
	}

	public void setUsageAttributeCode(String usageAttributeCode) {
		this.usageAttributeCode = usageAttributeCode;
	}

	public String getRequestJSON() {
		return requestJSON;
	}

	public void setRequestJSON(String requestJSON) {
		this.requestJSON = requestJSON;
	}
}
