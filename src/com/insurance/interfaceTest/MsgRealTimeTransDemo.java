package com.insurance.interfaceTest;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.insurance.model.PolicyHolder;
import com.insurance.model.PolicyInfo;
import com.insurance.model.Policyinsured;
import com.insurance.util.DateUtil;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.UUIDUtil;

/**
 * 合作伙伴向平安发起报文类交易报文示例 注意：本示例仅供参考演示，生产代码请合作伙伴另行编写
 * 
 * @author HXS 2012-12-19
 * @author huzhihong.com
 * 
 *         创建日期：2015年7月23日
 */
public class MsgRealTimeTransDemo {

	/**
	 * @author HXS
	 * @date 2012-12-19
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");

		String msgFileName = propertiesUtil.readValue("RESOUCEPATH") + "100083" + ".xml";// 交易主体报文文件
		String message = getMessageTest(msgFileName, null, null, null);// 获取发送交易报文
		// String message = getMessage(msgFileName, "101083");// 获取注销报文
		System.out.println("发送到平安的请求报文内容：");
		System.out.println(message);
		String responseStr = sendMsgToPingAn(message); // 发送报文到平安
		System.out.println("收到平安的回复报文内容：");
		System.out.println(responseStr);

	}

	/**
	 * 提交保单
	 * 
	 * @param policyinfo
	 *            保单信息
	 * @param policyHolder
	 *            投保人信息
	 * @param policyinsured
	 *            被保人信息
	 * @param xmlcode
	 *            报文编号
	 * @return 返回报文
	 * @throws Exception
	 */
	public static String sendmsgToPingAn(PolicyInfo policyinfo, PolicyHolder policyHolder, Policyinsured policyinsured,
			String xmlcode) throws Exception {
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String msgFileName = propertiesUtil.readValue("RESOUCEPATH") + xmlcode + ".xml";// 交易主体报文文件
		// 交易主体报文文件
		String message = getMessage(msgFileName, policyinfo, policyHolder, policyinsured);// 获取发送交易报文
		System.out.println("发送到平安的请求报文内容：");
		System.out.println(message);
		String responseStr = sendMsgToPingAn(message); // 发送报文到平安
		System.out.println("收到平安的回复报文内容：");
		System.out.println(responseStr);
		return responseStr;

	}

	/**
	 * 撤销保单
	 * 
	 * @param xmlcode
	 *            报文编号
	 * @param policyNo
	 *            保单号
	 * @return 返回报文
	 * @throws Exception
	 */
	public static String sendmsgToPingAn(String xmlcode, String policyNo) throws Exception {
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String msgFileName = propertiesUtil.readValue("RESOUCEPATH") + xmlcode + ".xml";// 交易主体报文文件
		// 交易主体报文文件
		String message = getMessage(msgFileName, policyNo);// 获取发送交易报文
		System.out.println("发送到平安的请求报文内容：");
		System.out.println(message);
		String responseStr = sendMsgToPingAn(message); // 发送报文到平安
		System.out.println("收到平安的回复报文内容：");
		System.out.println(responseStr);
		return responseStr;

	}

	/**
	 * 获取注销报文
	 * 
	 * @param msgFileName
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	private static String getMessage(String msgFileName, String policyNo) throws Exception {
		String message = "";
		InputStream is = null;
		try {
			is = new FileInputStream(new File(msgFileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));// 加载报文文件
			StringBuffer strBuf = new StringBuffer();
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				strBuf.append(tmp).append("\n");
			}

			SimpleDateFormat acctDateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat acctTimeFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String BK_ACCT_DATE = acctDateFormat.format(calendar.getTime());// 交易日期
			String BK_ACCT_TIME = acctTimeFormat.format(calendar.getTime());// 交易时间

			// 以下是对报文中指定此段插入值
			insertValue(strBuf, "<BK_ACCT_DATE>", BK_ACCT_DATE);
			insertValue(strBuf, "<BK_ACCT_TIME>", BK_ACCT_TIME);
			insertValue(strBuf, "<BK_SERIAL>",
					DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));
			insertValue(strBuf, "<policyNo>", policyNo);
			message = strBuf.toString();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {

					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return message;
	}

	/**
	 * 发送请求报文到平安并接收平安的返回结果
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param requestMsg
	 * @return 返回结果
	 * @throws IOException
	 */
	private static String sendMsgToPingAn(String requestMsg) throws IOException {
		String responseStr = "";
		FileInputStream keystoreInstream = null;
		FileInputStream trustStoreInstream = null;
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		try {
			String KEYSTOREPATH = propertiesUtil.readValue("RESOUCEPATH") + propertiesUtil.readValue("KEYSTOREPATH");
			String TRUSTSTOREPATH = propertiesUtil.readValue("RESOUCEPATH")
					+ propertiesUtil.readValue("TRUSTSTOREPATH");
			keystoreInstream = new FileInputStream(new File(KEYSTOREPATH)); // 加载密钥库文件
			trustStoreInstream = new FileInputStream(new File(TRUSTSTOREPATH)); // 加载授信库文件
			String keystoreType = "";

			if (TRUSTSTOREPATH.toUpperCase().indexOf("PFX") >= 0)// 判断证书文件的格式
			{
				keystoreType = "PKCS12";
			} else {
				keystoreType = "JKS";
			}

			KeyStore ks = KeyStore.getInstance(keystoreType);
			String KEYSTOREPASSWORD = propertiesUtil.readValue("KEYSTOREPASSWORD");
			ks.load(keystoreInstream, KEYSTOREPASSWORD.toCharArray());
			KeyStore ts = KeyStore.getInstance(keystoreType);
			ts.load(trustStoreInstream, propertiesUtil.readValue("TRUSTSTOREPASSWORD").toCharArray());
			SSLSocketFactory socketFactory = new SSLSocketFactory(SSLSocketFactory.SSL, ks,
					propertiesUtil.readValue("TRUSTSTOREPASSWORD"), ts, null, new TrustStrategy() {
						public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
							return true;
						}
					}, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			// 包装HTTPCLIENT
			HttpClient httpclient = new DefaultHttpClient();
			httpclient = wrapClient(httpclient);
			Integer PINGANPORT = Integer.parseInt(propertiesUtil.readValue("PINGANPORT"));
			// 指定协议、URL等信息
			Scheme sch = new Scheme(propertiesUtil.readValue("HTTPSPROTOCOL"), PINGANPORT.intValue(), socketFactory);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);
			HttpPost httpPost = new HttpPost(propertiesUtil.readValue("PINGANURL"));
			// 指定请求头
			StringEntity entity = new StringEntity(requestMsg, "text/html", propertiesUtil.readValue("PINGANENCODING"));
			httpPost.setEntity(entity);
			System.out.println("开始发送数据...");
			HttpResponse httpResponse = httpclient.execute(httpPost);// 发送请求
			System.out.println("接收返回结果...");
			HttpEntity resEntity = httpResponse.getEntity();
			if (resEntity != null) {
				responseStr = EntityUtils.toString(resEntity);// 获取结果
			}
			httpclient.getConnectionManager().shutdown();// 关闭连接

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (keystoreInstream != null)
				try {
					keystoreInstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (trustStoreInstream != null)
				try {
					trustStoreInstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return responseStr;

	}

	public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {
		try {
			// SSLContext ctx = SSLContext.getInstance("TLS");
			SSLContext ctx = SSLContext.getInstance("SSL");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, ssf));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
			return new DefaultHttpClient(mgr, base.getParams());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取需要发送的报文内容<测试版>，报文主体结构放置在msgFilePath，其中的某些值需要动态变化
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param msgFilePath
	 *            报文主体文件路径
	 * @param policyinsured
	 * @param policyHolder
	 * @param policyinfo
	 * @return
	 * @throws Exception
	 */
	private static String getMessageTest(String msgFilePath, PolicyInfo policyinfo, PolicyHolder policyHolder,
			Policyinsured policyinsured) throws Exception {
		String message = "";
		InputStream is = null;
		try {
			// 创建文档处理对象
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// 通过DocumentBuilder创建doc的文档对象
			Document doc = db.parse(new FileInputStream(new File(msgFilePath)));
			// 创建XPath
			XPath xpath = XPathFactory.newInstance().newXPath();
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			tran.setOutputProperty(OutputKeys.ENCODING, "gbk");
			tran.setOutputProperty(OutputKeys.INDENT, "yes");

			/**
			 * 各部分參數處理
			 */

			// Header 部分参数
			NodeList headerList = (NodeList) xpath.evaluate("/eaiAhsXml/Header", doc, XPathConstants.NODESET);
			Element headerElement = (Element) headerList.item(0);
			SimpleDateFormat acctDateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat acctTimeFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String BK_ACCT_DATE = acctDateFormat.format(calendar.getTime());// 交易日期
			String BK_ACCT_TIME = acctTimeFormat.format(calendar.getTime());// 交易时间
			headerElement.getElementsByTagName("BK_ACCT_DATE").item(0).setTextContent(BK_ACCT_DATE);
			headerElement.getElementsByTagName("BK_ACCT_TIME").item(0).setTextContent(BK_ACCT_TIME);
			headerElement.getElementsByTagName("BK_SERIAL").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// policyBaseInfo 部分参数
			NodeList policyBaseInfoList = (NodeList) xpath.evaluate("/eaiAhsXml/Request/ahsPolicy/policyBaseInfo", doc,
					XPathConstants.NODESET);
			Element policyBaseInfoElement = (Element) policyBaseInfoList.item(0);
			policyBaseInfoElement.getElementsByTagName("applyPersonnelNum").item(0).setTextContent("1");
			policyBaseInfoElement.getElementsByTagName("relationshipWithInsured").item(0).setTextContent("9");
			policyBaseInfoElement.getElementsByTagName("totalModalPremium").item(0).setTextContent("320.00");
			policyBaseInfoElement.getElementsByTagName("insuranceBeginTime").item(0)
					.setTextContent("2015-08-01 00:00:00");
			policyBaseInfoElement.getElementsByTagName("insuranceEndTime").item(0)
					.setTextContent("2015-10-10 23:59:59");
			int days = DateUtil.daysBetween("2015-08-01 00:00:00", "2015-10-10 23:59:59") + 1;
			policyBaseInfoElement.getElementsByTagName("applyDay").item(0).setTextContent(String.valueOf(days));
			policyBaseInfoElement.getElementsByTagName("businessType").item(0).setTextContent("1");
			policyBaseInfoElement.getElementsByTagName("currecyCode").item(0).setTextContent("01");
			policyBaseInfoElement.getElementsByTagName("alterableSpecialPromise").item(0).setTextContent("无特别约定");

			// policyExtendInfo 部分参数
			NodeList policyExtendInfoList = (NodeList) xpath.evaluate("/eaiAhsXml/Request/ahsPolicy/policyExtendInfo",
					doc, XPathConstants.NODESET);
			Element policyExtendInfoElement = (Element) policyExtendInfoList.item(0);
			policyExtendInfoElement.getElementsByTagName("partnerName").item(0).setTextContent("JABXDL");
			policyExtendInfoElement.getElementsByTagName("partnerSystemSeriesNo").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// insuranceApplicantInfo 部分参数
			NodeList insuranceApplicantInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/insuranceApplicantInfo/individualPersonnelInfo", doc,
					XPathConstants.NODESET);
			Element insuranceApplicantInfoElement = (Element) insuranceApplicantInfoList.item(0);
			insuranceApplicantInfoElement.getElementsByTagName("personnelName").item(0).setTextContent("胡志宏");
			insuranceApplicantInfoElement.getElementsByTagName("sexCode").item(0).setTextContent("M");
			insuranceApplicantInfoElement.getElementsByTagName("certificateType").item(0).setTextContent("01");
			insuranceApplicantInfoElement.getElementsByTagName("certificateNo").item(0)
					.setTextContent("43092219890205423X");
			insuranceApplicantInfoElement.getElementsByTagName("birthday").item(0).setTextContent("1989-02-05");
			insuranceApplicantInfoElement.getElementsByTagName("familyNameSpell").item(0).setTextContent("HU");
			insuranceApplicantInfoElement.getElementsByTagName("firstNameSpell").item(0).setTextContent("ZHIHONG");
			insuranceApplicantInfoElement.getElementsByTagName("personnelAge").item(0).setTextContent("30");
			insuranceApplicantInfoElement.getElementsByTagName("mobileTelephone").item(0).setTextContent("13268527439");
			insuranceApplicantInfoElement.getElementsByTagName("email").item(0).setTextContent("hh_laohu@163.com");
			insuranceApplicantInfoElement.getElementsByTagName("address").item(0).setTextContent("深圳南山");

			// subjectInfoList 部分参数
			NodeList subjectInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo", doc, XPathConstants.NODESET);
			Element subjectInfoElement = (Element) subjectInfoList.item(0);
			subjectInfoElement.getElementsByTagName("totalModalPremium").item(0).setTextContent("320.00");

			// productInfo 部分参数
			NodeList productInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/productInfo", doc, XPathConstants.NODESET);
			Element productInfoElement = (Element) productInfoList.item(0);
			productInfoElement.getElementsByTagName("productCode").item(0).setTextContent("00820");
			productInfoElement.getElementsByTagName("applyNum").item(0).setTextContent("1");
			productInfoElement.getElementsByTagName("totalModalPremium").item(0).setTextContent("320.00");

			// insurantInfo 部分参数
			NodeList insurantInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/insurantInfo/insurantInfo", doc,
					XPathConstants.NODESET);
			Element insurantInfoElement = (Element) insurantInfoList.item(0);
			insurantInfoElement.getElementsByTagName("personnelAttribute").item(0).setTextContent("100");
			insurantInfoElement.getElementsByTagName("virtualInsuredNum").item(0).setTextContent("");
			insurantInfoElement.getElementsByTagName("personnelName").item(0).setTextContent("胡志宏");
			insurantInfoElement.getElementsByTagName("sexCode").item(0).setTextContent("M");
			insurantInfoElement.getElementsByTagName("certificateType").item(0).setTextContent("01");
			insurantInfoElement.getElementsByTagName("certificateNo").item(0).setTextContent("43092219890205423X");
			insurantInfoElement.getElementsByTagName("birthday").item(0).setTextContent("1989-02-05");
			insurantInfoElement.getElementsByTagName("familyNameSpell").item(0).setTextContent("HU");
			insurantInfoElement.getElementsByTagName("firstNameSpell").item(0).setTextContent("ZHIHONG");
			insurantInfoElement.getElementsByTagName("personnelAge").item(0).setTextContent("30");
			insurantInfoElement.getElementsByTagName("mobileTelephone").item(0).setTextContent("13268527439");
			insurantInfoElement.getElementsByTagName("email").item(0).setTextContent("hh_laohu@163.com");
			insurantInfoElement.getElementsByTagName("address").item(0).setTextContent("深圳南山");

			// 返回修改后的XML文档
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			tran.transform(new DOMSource(doc), new StreamResult(bos));
			// System.out.println( bos.toString());
			message = bos.toString();
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

		return message;
	}

	/**
	 * 获取需要发送的报文内容<生产环境>，报文主体结构放置在msgFilePath，其中的某些值需要动态变化
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param msgFilePath
	 *            报文主体文件路径
	 * @param policyinsured
	 * @param policyHolder
	 * @param policyinfo
	 * @return
	 * @throws Exception
	 */
	private static String getMessage(String msgFilePath, PolicyInfo policyinfo, PolicyHolder policyHolder,
			Policyinsured policyinsured) throws Exception {
		String message = "";
		InputStream is = null;
		try {
			// 创建文档处理对象
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// 通过DocumentBuilder创建doc的文档对象
			Document doc = db.parse(new FileInputStream(new File(msgFilePath)));
			// 创建XPath
			XPath xpath = XPathFactory.newInstance().newXPath();
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			tran.setOutputProperty(OutputKeys.ENCODING, "gbk");
			tran.setOutputProperty(OutputKeys.INDENT, "yes");

			/**
			 * 各部分參數處理
			 */

			// Header 部分参数
			NodeList headerList = (NodeList) xpath.evaluate("/eaiAhsXml/Header", doc, XPathConstants.NODESET);
			Element headerElement = (Element) headerList.item(0);
			SimpleDateFormat acctDateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat acctTimeFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String BK_ACCT_DATE = acctDateFormat.format(calendar.getTime());// 交易日期
			String BK_ACCT_TIME = acctTimeFormat.format(calendar.getTime());// 交易时间
			headerElement.getElementsByTagName("BK_ACCT_DATE").item(0).setTextContent(BK_ACCT_DATE);
			headerElement.getElementsByTagName("BK_ACCT_TIME").item(0).setTextContent(BK_ACCT_TIME);
			headerElement.getElementsByTagName("BK_SERIAL").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// policyBaseInfo 部分参数
			NodeList policyBaseInfoList = (NodeList) xpath.evaluate("/eaiAhsXml/Request/ahsPolicy/policyBaseInfo", doc,
					XPathConstants.NODESET);
			Element policyBaseInfoElement = (Element) policyBaseInfoList.item(0);
			policyBaseInfoElement.getElementsByTagName("applyPersonnelNum").item(0).setTextContent("1");
			policyBaseInfoElement.getElementsByTagName("relationshipWithInsured").item(0).setTextContent("9");
			policyBaseInfoElement.getElementsByTagName("totalModalPremium").item(0)
					.setTextContent(policyinfo.getAmount().toString());
			policyBaseInfoElement.getElementsByTagName("insuranceBeginTime").item(0)
					.setTextContent(policyinfo.getStartDate().substring(0, 10) + " 00:00:00");
			policyBaseInfoElement.getElementsByTagName("insuranceEndTime").item(0)
					.setTextContent(policyinfo.getEndDate().substring(0, 10) + " 23:59:59");
			int days = DateUtil.daysBetween(policyinfo.getStartDate(), policyinfo.getEndDate()) + 1;
			policyBaseInfoElement.getElementsByTagName("applyDay").item(0).setTextContent(String.valueOf(days));
			policyBaseInfoElement.getElementsByTagName("businessType").item(0).setTextContent("1");
			policyBaseInfoElement.getElementsByTagName("currecyCode").item(0).setTextContent("01");
			policyBaseInfoElement.getElementsByTagName("alterableSpecialPromise").item(0).setTextContent("无特别约定");

			// policyExtendInfo 部分参数
			NodeList policyExtendInfoList = (NodeList) xpath.evaluate("/eaiAhsXml/Request/ahsPolicy/policyExtendInfo",
					doc, XPathConstants.NODESET);
			Element policyExtendInfoElement = (Element) policyExtendInfoList.item(0);
			policyExtendInfoElement.getElementsByTagName("partnerName").item(0).setTextContent("JABXDL");
			policyExtendInfoElement.getElementsByTagName("partnerSystemSeriesNo").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// insuranceApplicantInfo 部分参数
			NodeList insuranceApplicantInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/insuranceApplicantInfo/individualPersonnelInfo", doc,
					XPathConstants.NODESET);
			Element insuranceApplicantInfoElement = (Element) insuranceApplicantInfoList.item(0);
			insuranceApplicantInfoElement.getElementsByTagName("personnelName").item(0)
					.setTextContent(policyHolder.getName());
			insuranceApplicantInfoElement.getElementsByTagName("sexCode").item(0)
					.setTextContent(policyHolder.getSex() == "1" ? "M" : "F");
			insuranceApplicantInfoElement.getElementsByTagName("certificateType").item(0).setTextContent("01");
			insuranceApplicantInfoElement.getElementsByTagName("certificateNo").item(0)
					.setTextContent(policyHolder.getCertificateNo());
			insuranceApplicantInfoElement.getElementsByTagName("birthday").item(0)
					.setTextContent(policyHolder.getBirthdate().substring(0, 10));
			insuranceApplicantInfoElement.getElementsByTagName("familyNameSpell").item(0).setTextContent("");
			insuranceApplicantInfoElement.getElementsByTagName("firstNameSpell").item(0).setTextContent("");
			insuranceApplicantInfoElement.getElementsByTagName("personnelAge").item(0)
					.setTextContent(DateUtil.getAgeByBirthDay(policyHolder.getBirthdate().substring(0, 9)).toString());
			insuranceApplicantInfoElement.getElementsByTagName("mobileTelephone").item(0)
					.setTextContent(policyHolder.getTelephone());
			insuranceApplicantInfoElement.getElementsByTagName("email").item(0).setTextContent(policyHolder.getEmail());
			insuranceApplicantInfoElement.getElementsByTagName("address").item(0)
					.setTextContent(policyHolder.getDetailAddress());

			// subjectInfoList 部分参数
			NodeList subjectInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo", doc, XPathConstants.NODESET);
			Element subjectInfoElement = (Element) subjectInfoList.item(0);
			subjectInfoElement.getElementsByTagName("totalModalPremium").item(0)
					.setTextContent(policyinfo.getAmount().toString());

			// productInfo 部分参数
			NodeList productInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/productInfo", doc, XPathConstants.NODESET);
			Element productInfoElement = (Element) productInfoList.item(0);
			productInfoElement.getElementsByTagName("productCode").item(0).setTextContent(policyinfo.getProductId());
			productInfoElement.getElementsByTagName("applyNum").item(0).setTextContent("1");
			productInfoElement.getElementsByTagName("totalModalPremium").item(0)
					.setTextContent(policyinfo.getAmount().toString());

			// insurantInfo 部分参数
			NodeList insurantInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/insurantInfo/insurantInfo", doc,
					XPathConstants.NODESET);
			Element insurantInfoElement = (Element) insurantInfoList.item(0);
			insurantInfoElement.getElementsByTagName("personnelAttribute").item(0).setTextContent("100");
			insurantInfoElement.getElementsByTagName("virtualInsuredNum").item(0).setTextContent("");
			insurantInfoElement.getElementsByTagName("personnelName").item(0).setTextContent(policyinsured.getName());
			insurantInfoElement.getElementsByTagName("sexCode").item(0)
					.setTextContent(policyinsured.getSex() == "1" ? "M" : "F");
			insurantInfoElement.getElementsByTagName("certificateType").item(0).setTextContent("01");
			insurantInfoElement.getElementsByTagName("certificateNo").item(0)
					.setTextContent(policyinsured.getCertificateNo());
			insurantInfoElement.getElementsByTagName("birthday").item(0)
					.setTextContent(policyinsured.getBirthdate().substring(0, 10));
			insurantInfoElement.getElementsByTagName("familyNameSpell").item(0).setTextContent("");
			insurantInfoElement.getElementsByTagName("firstNameSpell").item(0).setTextContent("");
			insurantInfoElement.getElementsByTagName("personnelAge").item(0)
					.setTextContent(DateUtil.getAgeByBirthDay(policyinsured.getBirthdate().substring(0, 9)).toString());
			insurantInfoElement.getElementsByTagName("mobileTelephone").item(0)
					.setTextContent(policyinsured.getTelephone());
			insurantInfoElement.getElementsByTagName("email").item(0).setTextContent(policyinsured.getEmail());
			insurantInfoElement.getElementsByTagName("address").item(0)
					.setTextContent(policyinsured.getDetailAddress());

			// 返回修改后的XML文档
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			tran.transform(new DOMSource(doc), new StreamResult(bos));
			// System.out.println( bos.toString());
			message = bos.toString();
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

		return message;
	}

	/**
	 * 向StringBuffer中插入指定字段的值
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param message
	 *            需要插入到的目的字符串
	 * @param keyName
	 *            需要查找的字段名
	 * @param keyValue
	 *            需要插入的字段值
	 */
	private static void insertValue(StringBuffer message, String keyName, String keyValue) {
		int beginIndex = message.indexOf(keyName, 2) + keyName.length();
		message.insert(beginIndex, keyValue);
	}

}
