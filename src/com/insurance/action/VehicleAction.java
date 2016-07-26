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
 * @车辆Action
 * 
 * @author huzhihong.com
 * 
 * @创建日期：2015年6月15日
 */
public class VehicleAction extends BaseAction {

	private static final long serialVersionUID = -2591235987475699122L;
	@Resource
	private VehicleService vehicleService;

	private String vehicleString;// 传的vehicleString
	private String vehicleName;// 车型名称，适用模糊查询
	private String vehicleFrameNo;// 车架号，不适用模糊查询
	private String autoModelCode;// 车型编码
	private String usageAttributeCode;// 使用性质
	private String requestJSON;// 请求JSON串
	private String applicantString;// 投保人信息
	private String insuredString;// 投保人信息
	private String vehicleOwnerString;// 3.3.1.5. 行驶证车主
	private String driverListString;// 3.3.1.6. 驾驶员信息

	/**
	 * 添加车辆信息
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
	 * 删除车辆信息
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
	 * 移动端修改资料
	 * 
	 * @throws Exception
	 */
	public void updateByMobile() throws Exception {
		String result = vehicleService.updateByMobile(vehicleString);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
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
	 * @author huzhihong.com
	 * @name 模糊查询获取车型信息-V010
	 * @category 根据车辆品牌型号或车架号, 找到车型详细信息，用于投保查询时输入规范的车型信息。 车架号不支持模糊查询
	 * @case http://localhost:8080/InsuranceService/back/vehicle_searchVehicleModel.action?vehicleString={%22vehicleName%22:123}
	 * @备注 参数注意大小写
	 * @URIEncoding 直接在地址栏输入中文，会因为参数编码问题，导致参数变成乱码
	 * @return
	 * @throws Exception
	 */
	public String searchVehicleModel() throws Exception {
		if (vehicleString == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "参数 vehicleString 不能为空");
			return null;
		}
		// vehicleString = new String(vehicleString.getBytes("ISO-8859-1"),
		// "UTF-8");
		System.out.println("接收到的jsonString:" + vehicleString);
		Vehicle vehicle = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
		if (vehicle == null)
			return "参数格式不正确！";
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
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * @author huzhihong.com
	 * @name 查询车型信息-V020
	 * @category 查询车型信息-V020:根据车辆品牌型号或车架号, 找到车型详细信息，用于投保查询时输入规范的车型信息。 车架号不支持模糊查询
	 * @case  http://localhost:8080/InsuranceService/back/vehicle_searchVehicleModel.action?vehicleString={%22vehicleName%22:123}
	 * @备注 参数注意大小写
	 * @return
	 * @throws Exception
	 */
	public String V020() throws Exception {

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

		// 读取文档中元素
		Element bodyvehicleElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Body/Vehicle");

		if (requestJSON != null) {
			System.out.println("接收到的requestJSON:" + requestJSON);
			Vehicle vehicle = Return_Code.getGson().fromJson(requestJSON, Vehicle.class);
			if (vehicle == null)
				return "参数 requestJSON 格式不正确！";

			// 修改元素
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

		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * 
	 * @name 3.3. 投保查询
	 * @category 投保查询
	 * @return
	 * @throws Exception
	 */
	public String V030() throws Exception {

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
		// System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * @category 保费计算
	 * @return
	 * @throws Exception
	 */
	public String V040() throws Exception {
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
		// System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();

		String jsonString = getXMLMessage(buffer);
		// JSONObject jsonObject = new JSONObject();
		ResponseUtil.write(ServletActionContext.getResponse(), jsonString);
		return null;
	}

	/**
	 * @category 保费计算校验
	 * @return
	 * @throws Exception
	 */
	public String V050() throws Exception {
		// 读取配置文件
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		// 获取XML模版文件
		String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
		File file = getXmlTempleteFile(method);
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

	/**
	 * @name main
	 * @category 主方法测试用
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("*******************************************************************************");
		System.out.println("测试接口：Test_V010");
		System.out.println("*******************************************************************************");
		Test_V010();
		System.out.println("*******************************************************************************");
		System.out.println("测试接口：Test_V020");
		System.out.println("*******************************************************************************");
		Test_V020();
//		 System.out.println("*******************************************************************************");
//		 System.out.println("测试接口：Test_V030");
//		 System.out.println("*******************************************************************************");
//		Test_V030();
//		System.out.println("*******************************************************************************");
//		 System.out.println("测试接口：Test_V040");
//		System.out.println("*******************************************************************************");
//		Test_V040();
	}

	@SuppressWarnings("unused")
	private static String Test_V010() throws Exception {
		String vehicleString = "{\"vehicleName\":1234}";
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
		// System.out.println(docString);
		// 将文档转换成字节流
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
	 * @category 直接获取XML里面的基本数据
	 * @param flieName
	 *            XML文件名
	 * @return
	 * @throws Exception
	 */
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
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * V020车型查询
	 */

	/**
	 * 所有车辆信息分页查询
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
	 * 查询车辆信息
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
