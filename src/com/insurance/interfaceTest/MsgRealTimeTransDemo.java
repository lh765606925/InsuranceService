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
 * ���������ƽ���������ཻ�ױ���ʾ�� ע�⣺��ʾ�������ο���ʾ���������������������б�д
 * 
 * @author HXS 2012-12-19
 * @author huzhihong.com
 * 
 *         �������ڣ�2015��7��23��
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

		String msgFileName = propertiesUtil.readValue("RESOUCEPATH") + "100083" + ".xml";// �������屨���ļ�
		String message = getMessageTest(msgFileName, null, null, null);// ��ȡ���ͽ��ױ���
		// String message = getMessage(msgFileName, "101083");// ��ȡע������
		System.out.println("���͵�ƽ�������������ݣ�");
		System.out.println(message);
		String responseStr = sendMsgToPingAn(message); // ���ͱ��ĵ�ƽ��
		System.out.println("�յ�ƽ���Ļظ��������ݣ�");
		System.out.println(responseStr);

	}

	/**
	 * �ύ����
	 * 
	 * @param policyinfo
	 *            ������Ϣ
	 * @param policyHolder
	 *            Ͷ������Ϣ
	 * @param policyinsured
	 *            ��������Ϣ
	 * @param xmlcode
	 *            ���ı��
	 * @return ���ر���
	 * @throws Exception
	 */
	public static String sendmsgToPingAn(PolicyInfo policyinfo, PolicyHolder policyHolder, Policyinsured policyinsured,
			String xmlcode) throws Exception {
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String msgFileName = propertiesUtil.readValue("RESOUCEPATH") + xmlcode + ".xml";// �������屨���ļ�
		// �������屨���ļ�
		String message = getMessage(msgFileName, policyinfo, policyHolder, policyinsured);// ��ȡ���ͽ��ױ���
		System.out.println("���͵�ƽ�������������ݣ�");
		System.out.println(message);
		String responseStr = sendMsgToPingAn(message); // ���ͱ��ĵ�ƽ��
		System.out.println("�յ�ƽ���Ļظ��������ݣ�");
		System.out.println(responseStr);
		return responseStr;

	}

	/**
	 * ��������
	 * 
	 * @param xmlcode
	 *            ���ı��
	 * @param policyNo
	 *            ������
	 * @return ���ر���
	 * @throws Exception
	 */
	public static String sendmsgToPingAn(String xmlcode, String policyNo) throws Exception {
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String msgFileName = propertiesUtil.readValue("RESOUCEPATH") + xmlcode + ".xml";// �������屨���ļ�
		// �������屨���ļ�
		String message = getMessage(msgFileName, policyNo);// ��ȡ���ͽ��ױ���
		System.out.println("���͵�ƽ�������������ݣ�");
		System.out.println(message);
		String responseStr = sendMsgToPingAn(message); // ���ͱ��ĵ�ƽ��
		System.out.println("�յ�ƽ���Ļظ��������ݣ�");
		System.out.println(responseStr);
		return responseStr;

	}

	/**
	 * ��ȡע������
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
			BufferedReader br = new BufferedReader(new InputStreamReader(is));// ���ر����ļ�
			StringBuffer strBuf = new StringBuffer();
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				strBuf.append(tmp).append("\n");
			}

			SimpleDateFormat acctDateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat acctTimeFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String BK_ACCT_DATE = acctDateFormat.format(calendar.getTime());// ��������
			String BK_ACCT_TIME = acctTimeFormat.format(calendar.getTime());// ����ʱ��

			// �����ǶԱ�����ָ���˶β���ֵ
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
	 * ���������ĵ�ƽ��������ƽ���ķ��ؽ��
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param requestMsg
	 * @return ���ؽ��
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
			keystoreInstream = new FileInputStream(new File(KEYSTOREPATH)); // ������Կ���ļ�
			trustStoreInstream = new FileInputStream(new File(TRUSTSTOREPATH)); // �������ſ��ļ�
			String keystoreType = "";

			if (TRUSTSTOREPATH.toUpperCase().indexOf("PFX") >= 0)// �ж�֤���ļ��ĸ�ʽ
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
			// ��װHTTPCLIENT
			HttpClient httpclient = new DefaultHttpClient();
			httpclient = wrapClient(httpclient);
			Integer PINGANPORT = Integer.parseInt(propertiesUtil.readValue("PINGANPORT"));
			// ָ��Э�顢URL����Ϣ
			Scheme sch = new Scheme(propertiesUtil.readValue("HTTPSPROTOCOL"), PINGANPORT.intValue(), socketFactory);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);
			HttpPost httpPost = new HttpPost(propertiesUtil.readValue("PINGANURL"));
			// ָ������ͷ
			StringEntity entity = new StringEntity(requestMsg, "text/html", propertiesUtil.readValue("PINGANENCODING"));
			httpPost.setEntity(entity);
			System.out.println("��ʼ��������...");
			HttpResponse httpResponse = httpclient.execute(httpPost);// ��������
			System.out.println("���շ��ؽ��...");
			HttpEntity resEntity = httpResponse.getEntity();
			if (resEntity != null) {
				responseStr = EntityUtils.toString(resEntity);// ��ȡ���
			}
			httpclient.getConnectionManager().shutdown();// �ر�����

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
	 * ��ȡ��Ҫ���͵ı�������<���԰�>����������ṹ������msgFilePath�����е�ĳЩֵ��Ҫ��̬�仯
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param msgFilePath
	 *            ���������ļ�·��
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
			// �����ĵ��������
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// ͨ��DocumentBuilder����doc���ĵ�����
			Document doc = db.parse(new FileInputStream(new File(msgFilePath)));
			// ����XPath
			XPath xpath = XPathFactory.newInstance().newXPath();
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			tran.setOutputProperty(OutputKeys.ENCODING, "gbk");
			tran.setOutputProperty(OutputKeys.INDENT, "yes");

			/**
			 * �����օ���̎��
			 */

			// Header ���ֲ���
			NodeList headerList = (NodeList) xpath.evaluate("/eaiAhsXml/Header", doc, XPathConstants.NODESET);
			Element headerElement = (Element) headerList.item(0);
			SimpleDateFormat acctDateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat acctTimeFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String BK_ACCT_DATE = acctDateFormat.format(calendar.getTime());// ��������
			String BK_ACCT_TIME = acctTimeFormat.format(calendar.getTime());// ����ʱ��
			headerElement.getElementsByTagName("BK_ACCT_DATE").item(0).setTextContent(BK_ACCT_DATE);
			headerElement.getElementsByTagName("BK_ACCT_TIME").item(0).setTextContent(BK_ACCT_TIME);
			headerElement.getElementsByTagName("BK_SERIAL").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// policyBaseInfo ���ֲ���
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
			policyBaseInfoElement.getElementsByTagName("alterableSpecialPromise").item(0).setTextContent("���ر�Լ��");

			// policyExtendInfo ���ֲ���
			NodeList policyExtendInfoList = (NodeList) xpath.evaluate("/eaiAhsXml/Request/ahsPolicy/policyExtendInfo",
					doc, XPathConstants.NODESET);
			Element policyExtendInfoElement = (Element) policyExtendInfoList.item(0);
			policyExtendInfoElement.getElementsByTagName("partnerName").item(0).setTextContent("JABXDL");
			policyExtendInfoElement.getElementsByTagName("partnerSystemSeriesNo").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// insuranceApplicantInfo ���ֲ���
			NodeList insuranceApplicantInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/insuranceApplicantInfo/individualPersonnelInfo", doc,
					XPathConstants.NODESET);
			Element insuranceApplicantInfoElement = (Element) insuranceApplicantInfoList.item(0);
			insuranceApplicantInfoElement.getElementsByTagName("personnelName").item(0).setTextContent("��־��");
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
			insuranceApplicantInfoElement.getElementsByTagName("address").item(0).setTextContent("������ɽ");

			// subjectInfoList ���ֲ���
			NodeList subjectInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo", doc, XPathConstants.NODESET);
			Element subjectInfoElement = (Element) subjectInfoList.item(0);
			subjectInfoElement.getElementsByTagName("totalModalPremium").item(0).setTextContent("320.00");

			// productInfo ���ֲ���
			NodeList productInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/productInfo", doc, XPathConstants.NODESET);
			Element productInfoElement = (Element) productInfoList.item(0);
			productInfoElement.getElementsByTagName("productCode").item(0).setTextContent("00820");
			productInfoElement.getElementsByTagName("applyNum").item(0).setTextContent("1");
			productInfoElement.getElementsByTagName("totalModalPremium").item(0).setTextContent("320.00");

			// insurantInfo ���ֲ���
			NodeList insurantInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/insurantInfo/insurantInfo", doc,
					XPathConstants.NODESET);
			Element insurantInfoElement = (Element) insurantInfoList.item(0);
			insurantInfoElement.getElementsByTagName("personnelAttribute").item(0).setTextContent("100");
			insurantInfoElement.getElementsByTagName("virtualInsuredNum").item(0).setTextContent("");
			insurantInfoElement.getElementsByTagName("personnelName").item(0).setTextContent("��־��");
			insurantInfoElement.getElementsByTagName("sexCode").item(0).setTextContent("M");
			insurantInfoElement.getElementsByTagName("certificateType").item(0).setTextContent("01");
			insurantInfoElement.getElementsByTagName("certificateNo").item(0).setTextContent("43092219890205423X");
			insurantInfoElement.getElementsByTagName("birthday").item(0).setTextContent("1989-02-05");
			insurantInfoElement.getElementsByTagName("familyNameSpell").item(0).setTextContent("HU");
			insurantInfoElement.getElementsByTagName("firstNameSpell").item(0).setTextContent("ZHIHONG");
			insurantInfoElement.getElementsByTagName("personnelAge").item(0).setTextContent("30");
			insurantInfoElement.getElementsByTagName("mobileTelephone").item(0).setTextContent("13268527439");
			insurantInfoElement.getElementsByTagName("email").item(0).setTextContent("hh_laohu@163.com");
			insurantInfoElement.getElementsByTagName("address").item(0).setTextContent("������ɽ");

			// �����޸ĺ��XML�ĵ�
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
	 * ��ȡ��Ҫ���͵ı�������<��������>����������ṹ������msgFilePath�����е�ĳЩֵ��Ҫ��̬�仯
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param msgFilePath
	 *            ���������ļ�·��
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
			// �����ĵ��������
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// ͨ��DocumentBuilder����doc���ĵ�����
			Document doc = db.parse(new FileInputStream(new File(msgFilePath)));
			// ����XPath
			XPath xpath = XPathFactory.newInstance().newXPath();
			Transformer tran = TransformerFactory.newInstance().newTransformer();
			tran.setOutputProperty(OutputKeys.ENCODING, "gbk");
			tran.setOutputProperty(OutputKeys.INDENT, "yes");

			/**
			 * �����օ���̎��
			 */

			// Header ���ֲ���
			NodeList headerList = (NodeList) xpath.evaluate("/eaiAhsXml/Header", doc, XPathConstants.NODESET);
			Element headerElement = (Element) headerList.item(0);
			SimpleDateFormat acctDateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat acctTimeFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String BK_ACCT_DATE = acctDateFormat.format(calendar.getTime());// ��������
			String BK_ACCT_TIME = acctTimeFormat.format(calendar.getTime());// ����ʱ��
			headerElement.getElementsByTagName("BK_ACCT_DATE").item(0).setTextContent(BK_ACCT_DATE);
			headerElement.getElementsByTagName("BK_ACCT_TIME").item(0).setTextContent(BK_ACCT_TIME);
			headerElement.getElementsByTagName("BK_SERIAL").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// policyBaseInfo ���ֲ���
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
			policyBaseInfoElement.getElementsByTagName("alterableSpecialPromise").item(0).setTextContent("���ر�Լ��");

			// policyExtendInfo ���ֲ���
			NodeList policyExtendInfoList = (NodeList) xpath.evaluate("/eaiAhsXml/Request/ahsPolicy/policyExtendInfo",
					doc, XPathConstants.NODESET);
			Element policyExtendInfoElement = (Element) policyExtendInfoList.item(0);
			policyExtendInfoElement.getElementsByTagName("partnerName").item(0).setTextContent("JABXDL");
			policyExtendInfoElement.getElementsByTagName("partnerSystemSeriesNo").item(0)
					.setTextContent(DateUtil.getCurrentDateStr6() + "BK_SERIAL" + UUIDUtil.random(1000000, 9999999));

			// insuranceApplicantInfo ���ֲ���
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

			// subjectInfoList ���ֲ���
			NodeList subjectInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo", doc, XPathConstants.NODESET);
			Element subjectInfoElement = (Element) subjectInfoList.item(0);
			subjectInfoElement.getElementsByTagName("totalModalPremium").item(0)
					.setTextContent(policyinfo.getAmount().toString());

			// productInfo ���ֲ���
			NodeList productInfoList = (NodeList) xpath.evaluate(
					"/eaiAhsXml/Request/ahsPolicy/subjectInfo/subjectInfo/productInfo", doc, XPathConstants.NODESET);
			Element productInfoElement = (Element) productInfoList.item(0);
			productInfoElement.getElementsByTagName("productCode").item(0).setTextContent(policyinfo.getProductId());
			productInfoElement.getElementsByTagName("applyNum").item(0).setTextContent("1");
			productInfoElement.getElementsByTagName("totalModalPremium").item(0)
					.setTextContent(policyinfo.getAmount().toString());

			// insurantInfo ���ֲ���
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

			// �����޸ĺ��XML�ĵ�
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
	 * ��StringBuffer�в���ָ���ֶε�ֵ
	 * 
	 * @author HXS
	 * @date 2012-12-19
	 * @param message
	 *            ��Ҫ���뵽��Ŀ���ַ���
	 * @param keyName
	 *            ��Ҫ���ҵ��ֶ���
	 * @param keyValue
	 *            ��Ҫ������ֶ�ֵ
	 */
	private static void insertValue(StringBuffer message, String keyName, String keyValue) {
		int beginIndex = message.indexOf(keyName, 2) + keyName.length();
		message.insert(beginIndex, keyValue);
	}

}
