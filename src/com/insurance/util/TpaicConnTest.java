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

		// 模版名称-V010
		String tempName = "V010";
		System.out.println("执行接口模版：" + tempName);
		// 根据名称读取XML模版
		byte[] data = getXmlTemplete(tempName);

		// 字节流转换成string 打印
		String content = new String(data);
		System.out.println("开始调用服务器:");
		System.out.println(content);

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
			String s = "";
			while ((temp = reader.readLine()) != null) {
				s = s + temp;
			}
			s = s.replace(">  <", "><");
			System.out.println("打印返回XML：");
			System.out.println(s);
			// XML转JSON
			s = XmlConverUtil.xmltoJson(s);
			//返回XML
			System.out.println("打印返回JSON：");
			System.out.println(s);
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
	}

	/**
	 * 读取XML模版
	 * 
	 * @param xmlName
	 * @return
	 * @throws IOException
	 */
	private static byte[] getXmlTemplete(String xmlName) throws IOException {
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
		// 创建doc的文档对象,解析XML<GBK编码>
		Document doc = XMLUtil.loadXMLFromFile(file, "GBK");
		// 读取文档中元素
		Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/Packet/Head");
		// 修改元素
		XMLUtil.editElementText(headerElement, "User", "admin");
		// 文档转换成String
		String docString = XmlConverUtil.doc2String(doc);
		// 打印文档
		// System.out.println(docString);
		// 将文档转换成字节流
		byte[] buffer = docString.getBytes();
		// 返回字节流
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
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}
}
