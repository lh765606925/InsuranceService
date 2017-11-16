package com.insurance.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;

import com.foresealife.damsfront.webservice.AsyncOrderData;
import com.foresealife.damsfront.webservice.AsyncOrderDataResponse;
import com.foresealife.damsfront.webservice.PlantformserviceStub;
import com.insurance.util.MD5Utils;
import com.insurance.model.AccountBalanceLog;
import com.insurance.model.BrokerageLog;
import com.insurance.model.OrderCharge;
import com.insurance.model.PageBean;
import com.insurance.model.PolicyHolder;
import com.insurance.model.PolicyInfo;
import com.insurance.model.Policyinsured;
import com.insurance.model.Product;
import com.insurance.model.Salesman;
import com.insurance.service.AccountBalanceLogService;
import com.insurance.service.BrokerageLogService;
import com.insurance.service.OrderchargeService;
import com.insurance.service.PolicyInfoService;
import com.insurance.service.PolicyholderService;
import com.insurance.service.PolicyinsuredService;
import com.insurance.service.ProductService;
import com.insurance.service.SalesmanRankService;
import com.insurance.service.SalesmanService;
import com.insurance.util.DateUtil;
import com.insurance.util.PageUtil;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;
import com.insurance.util.SundryTest;
import com.insurance.util.UUIDUtil;
import com.insurance.util.XMLUtil;
import com.insurance.interfaceTest.MsgRealTimeTransDemo;

public class PolicyInfoAction extends BaseAction {

	private static String SUCCESS = "success";

	private static final long serialVersionUID = 160521490690675937L;
	@Resource
	private PolicyInfoService policyInfoService;
	@Resource
	private PolicyinsuredService policyinsuredService;
	@Resource
	private PolicyholderService policyholderService;
	@Resource
	private SalesmanService salesmanService;
	@Resource
	private OrderchargeService orderchargeService;
	@Resource
	private ProductService productService;
	@Resource
	private SalesmanRankService salesmanRankService;
	@Resource
	private AccountBalanceLogService accountBalanceLogService;
	@Resource
	private BrokerageLogService brokerageLogService;

	private PolicyInfo policyinfo;// 保单信息
	private Policyinsured policyinsured;// 受益人信息
	private PolicyHolder policyHolder;// 投保人信息
	// private OrderCharge orderCharge;// 支付信息
	private String orderchargeString;// 支付信息
	private String baodanxx;// 保单信息
	private String shouyirenxx;// 受益人信息
	private String toubaorenxx;// 投保人信息
	private String xmlcode;// XML编号
	private String policyNo;// 保单号
	private String salesman_id;// 短险平台ID
	private String policyId;// 保单id

	/**
	 * 保单管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showList() throws Exception {
		request.setAttribute("leftPage", "common/policymanage_left.jsp");
		request.setAttribute("mainPage", "salestable/policyinfoManage.jsp");
		return SUCCESS;
	}

	/**
	 * 提交保单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submitPolicyinfo() throws Exception {
		return null;
	}

	/**
	 * 获取当前用户的保单列表
	 * 
	 * @return
	 */
	public String getMyPolicyList() {
		if (salesman_id == null) {
			return "param  salesman_id is null";
		}
		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		try {
			long total = policyInfoService.getPolicyInfoCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<PolicyInfo> slist = policyInfoService.findPolicyInfo(new PageBean(liMit, offSet),
					new Object[] { salesman_id });
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取数据list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		if (offset == null) {
			offset = "0"
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		try {
			long total = policyInfoService.getPolicyInfoCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<PolicyInfo> slist = policyInfoService.findPolicyInfo(new PageBean(liMit, offSet));
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @name APP-查询我的订单
	 * @param salesman_id,pagesize, pageindex
	 * @case http://192.168.1.150:8080/InsuranceService/back/policyinfo_app_show_order_list.action?salesman_id=124
	 * @return
	 * @throws Exception
	 */
	public String app_show_order_list() throws Exception {
		try {
			long total = policyInfoService.getPolicyInfoCount(salesman_id);
			List<PolicyInfo> slist = policyInfoService.findPolicyInfo(
					PageUtil.getApp_PageBean(pagesize, pageindex, total), salesman_id);
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @name APP-查询订单详情
	 * @param policyId
	 * @case http://192.168.1.150:8080/InsuranceService/back/policyinfo_app_detailinfo.action?policyId=1522D0A0293F41B3839645EEFBB3C5B4
	 * @return
	 */
	public String app_detailinfo() throws Exception {
		PolicyInfo policyInfo = policyInfoService.findPolicyInfoById(policyId);
		// 获取受益人信息
		PolicyHolder policyHolder = policyholderService.findPolicyHolderById(policyInfo.getPolicyHolder_id());
		policyInfo.setPolicyHolder(policyHolder);
		// 获取投保人信息
		policyinsured = policyinsuredService.findPolicyinsuredById(policyInfo.getPolicyInsured_id());
		policyInfo.setPolicyinsured(policyinsured);

		// 获取销售人员信息
		Salesman salesman = salesmanService.findById(Integer.parseInt(policyInfo.getSalesman_id()));
		policyInfo.setSalesman(salesman);
		// 获取产品信息
		Product product = productService.findProductById(policyInfo.getProductId());
		if (product != null)
			policyInfo.setProduct_name(product.getName());

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("policyInfo", policyInfo);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;

	}

	/**
	 * @name 过滤JSON属性-临时方法
	 * @param jsonObject
	 * @return
	 */
	@SuppressWarnings("unused")
	private JSONObject filterProperties(JSONObject jsonObject) {
		PolicyInfo target = new PolicyInfo();
		target.setPolicyId(policyId);
		PolicyHolder b = new PolicyHolder();
		b.setPolicyId("b.a");
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				// 过滤条件：返回true时，过滤生效
				return source instanceof PolicyInfo && name.equals("a");
			}
		});

		JSON json = JSONSerializer.toJSON(target, config);

		return jsonObject;
	}

	public String showMyList() throws Exception {
		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		try {
			long total = policyInfoService.getPolicyInfoCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<PolicyInfo> slist = policyInfoService.findPolicyInfo(new PageBean(liMit, offSet));
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @name 添加投保人、被保人、受益人、保单信息、支付信息到建安后台
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addDataToJaia() throws Exception {
		// 投保人信息
		if (toubaorenxx == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "toubaorenxx is null");
			return "toubaorenxx is null";
		}
		// 受益人信息
		if (shouyirenxx == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "shouyirenxx is null");
			return "shouyirenxx is null";
		}
		// 保单信息
		if (baodanxx == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "baodanxx is null");
			return "baodanxx is null";
		} else {
			System.out.println("toubaorenxx =" + toubaorenxx);
			System.out.println("shouyirenxx =" + shouyirenxx);
			System.out.println("baodanxx =" + baodanxx);
			// 提交被保人信息
			insert_Policyinsured();

			// 提交投保人信息
			insert_PolicyHolder();

			// 提交保单信息
			insert_PolicyInfo();

			// 添加支付信息
			insert_Ordercharge();
			// 添加订单信息

			// 扣款操作
			update_Salesman_Money();
		}
		return null;

	}

	/**
	 * 添加保单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 投保人信息
		if (toubaorenxx == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "toubaorenxx is null");
			return "toubaorenxx is null";
		}
		// 受益人信息
		if (shouyirenxx == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "shouyirenxx is null");
			return "shouyirenxx is null";
		}
		// 保单信息
		if (baodanxx == null) {
			ResponseUtil.write(ServletActionContext.getResponse(), "baodanxx is null");
			return "baodanxx is null";
		} else {
			System.out.println("toubaorenxx =" + toubaorenxx);
			System.out.println("shouyirenxx =" + shouyirenxx);
			System.out.println("baodanxx =" + baodanxx);
			// 提交被保人信息
			insert_Policyinsured();

			// 提交投保人信息
			insert_PolicyHolder();

			// 提交保单信息
			insert_PolicyInfo();

			// 添加支付信息
			insert_Ordercharge();
			// 添加订单信息

			// 扣款操作
			update_Salesman_Money();

			// 提交保单
			// 待获取的字段，先写死
			// 订单号，获取一个16位序列号，时间格式
			// 获取当前16位时间流水号
			String applyCode = DateUtil.GetDateRandom16();
			PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
			String channelId = propertiesUtil.readValue("channelId");
			String residentCity = propertiesUtil.readValue("residentCity");
			String residentProvince = propertiesUtil.readValue("residentProvince");
			String supplierBranch = propertiesUtil.readValue("supplierBranch");
			policyHolder.setCity("");
			;
			policyHolder.setProvince("");
			policyHolder.setDetailAddress("");
			policyinsured.setCity("");
			;
			policyinsured.setProvince("");
			policyinsured.setDetailAddress("");

			// 保单总额
			String premium = "";
			// 获取jsonString
			String jsonString = null;
			// jsonString += "{'policyInfo': {";
			// jsonString += "'amount': '" + policyinfo.getAmount() + "',";
			// jsonString += "'applyCode': '" + applyCode + "',";

			jsonString = "{'policyInfo': {" + "'amount': '" + policyinfo.getAmount() + "'," + "'applyCode': '"
					+ applyCode + "'," + "'channelId': '" + channelId + "'," + "'endDate': '" + policyinfo.getEndDate()
					+ "'," + "'salesCode': '300000030'," + "'flowCode': '04'," + "'insuredAndBeneficiaryList': [{"
					+ "'insuredRule': {'benefits': {},'choiceDuty': {},'outPut': {}}," + "'policyInsured': {"
					+ "'birthdate': '" + policyinsured.getBirthdate() + "'," + "'certEndDate': '',"
					+ "'certStartDate': ''," + "'certValidFlag': '2'," + "'certificateNo': '"
					+ policyinsured.getCertificateNo() + "'," + "'certificateType': '201',"
					+ "'chiefInsuredRelation': '" + policyinsured.getChiefInsuredRelation() + "'," + "'city': '"
					+ policyinsured.getCity() + "'," + "'count': 1," + "'county': '" + policyinsured.getCounty() + "',"
					+ "'detailAddress': '" + policyinsured.getDetailAddress() + "'," + "'email': '"
					+ policyinsured.getEmail() + "'," + "'holderRelation': '301'," + "'id': '" + UUIDUtil.uuid() + "',"
					+ "'isChiefInsured': '" + policyinsured.getIsChiefInsured() + "'," + "'isLegal': '"
					+ policyinsured.getIsLegal() + "'," + "'moblie': '" + policyinsured.getMoblie() + "',"
					+ "'name': '" + policyinsured.getName() + "'," + "'nationality': '"
					+ policyinsured.getNationality() + "'," + "'policyId': '" + policyinsured.getPolicyId() + "',"
					+ "'premium': '" + premium + "'," + "'profession': '" + policyinsured.getProfession() + "',"
					+ "'province': '" + policyinsured.getProvince() + "'," + "'residentCity': '" + residentCity + "',"
					+ "'residentProvince': '" + residentProvince + "'," + "'sex': '" + policyinsured.getSex() + "',"
					+ "'sort': '" + policyinsured.getSort() + "'," + "'supplierBranch': '"
					+ policyinsured.getSupplierBranch() + "'," + "'telephone': '" + policyinsured.getTelephone() + "',"
					+ "'zipNo': '" + policyinsured.getZipNo() + "'}" + "}]," + "'insuredSelect': '"
					+ policyinfo.getInsuredSelect() + "'," + "'isLegal': '" + policyinfo.getIsLegal() + "',"
					+ "'orderCharge': {" + "'buyerAccountUsername': ''," + "'payAmount': '',"
					+ "'payBusinessCode': ''," + "'payCode': ''," + "'payMethod': ''," + "'payer': '',"
					+ "'sellerAccountUsername': ''" + "}," + "'orderCode': '9702004173'," + "'orderId': '"
					+ UUIDUtil.uuid() + "'," + "'policyHolder': {" + "'birthdate': '" + policyHolder.getBirthdate()
					+ "'," + "'certEndDate': ''," + "'certStartDate': ''," + "'certValidFlag': '2',"
					+ "'certificateNo': '" + policyHolder.getCertificateNo() + "'," + "'certificateType': '"
					+ policyHolder.getCertificateType() + "'," + "'city': '" + policyHolder.getCity() + "',"
					+ "'county': '" + policyHolder.getCounty() + "'," + "'detailAddress': '"
					+ policyHolder.getDetailAddress() + "'," + "'email': '" + policyHolder.getEmail() + "',"
					+ "'id': '" + policyHolder.getId() + "'," + "'moblie': '" + policyHolder.getMoblie() + "',"
					+ "'name': '" + policyHolder.getName() + "'," + "'nationality': '" + policyHolder.getNationality()
					+ "'," + "'policyId': '" + policyHolder.getPolicyId() + "'," + "'profession': '"
					+ policyHolder.getProfession() + "'," + "'province': '" + policyHolder.getProvince() + "',"
					+ "'residentCity': '" + policyHolder.getResidentCity() + "'," + "'residentProvince': '"
					+ policyHolder.getResidentProvince() + "'," + "'sex': '" + policyHolder.getSex() + "',"
					+ "'supplierBranch': '" + supplierBranch + "'," + "'telephone': '" + policyHolder.getTelephone()
					+ "'," + "'zipNo': '" + policyHolder.getZipNo() + "'}," + "'policyId': '"
					+ policyinfo.getPolicyId() + "'," + "'productId': '" + policyinfo.getProductId() + "',"
					+ "'startDate': '" + policyinfo.getStartDate() + "'}" + "}";

			try {
				jsonString = new String(jsonString.getBytes("utf-8"), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			try {
				// 加密前
				System.out.println("加密前：" + jsonString + "qinghaimd5");
				String md5str = MD5Utils.createMD5_utf8(jsonString + "qinghaimd5");
				System.out.println("" + md5str);

				PlantformserviceStub plantformservicestub = new PlantformserviceStub();
				AsyncOrderData asyncorderdata = new AsyncOrderData();
				jsonString = "JSON||04||" + jsonString;
				asyncorderdata.setJsonstr(jsonString);
				asyncorderdata.setMd5Salt(md5str);

				System.out.println(jsonString);
				System.out.println(md5str);

				AsyncOrderDataResponse asyncorderdataresponse;
				asyncorderdataresponse = plantformservicestub.asyncOrderData(asyncorderdata);
				System.out.println("第三方客户端测试:+++++" + asyncorderdataresponse.get_return());
				ResponseUtil.write(ServletActionContext.getResponse(), asyncorderdataresponse.get_return());
				if (asyncorderdataresponse.get_return().contains("SUCCESS")) {
					return "SUCCESS";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 获取保单截止日期
	public String getendDate() {
		Product product = productService.getProduct(policyinfo.getProductId());
		int i = 0;
		String startdateString = product.getStartdate();
		if (startdateString.equals("网上投保申请第三日零时")) {
			i = 3;
		} else if (startdateString.equals("网上投保次日零时")) {
			i = 1;
		} else if (startdateString.equals("网上投保申请第二日零时")) {
			i = 2;
		}
		if (product.getDatelimit().equals("三年")) {
			return DateUtil.getNextDate(365 * 3 + i);
		}
		String result = DateUtil.getNextDate(365 + i);
		return result;
	}

	// 获取保单生效日期
	public String getstartDate() {
		int i = 0;
		Product product = productService.getProduct(policyinfo.getProductId());
		if (product != null) {
			String startdateString = product.getStartdate();
			if (startdateString.equals("网上投保申请第三日零时")) {
				i = 3;
			} else if (startdateString.equals("网上投保次日零时")) {
				i = 1;
			} else if (startdateString.equals("网上投保申请第二日零时")) {
				i = 2;
			}
		}
		return DateUtil.getNextDate(i);
	}

	/**
	 * 提交到平安系统
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendToPingan() throws Exception {
		String returnMsg = null;
		JSONObject jsonObject = new JSONObject();
		// 添加到建安后台
		if (xmlcode == null) {
			xmlcode = "100083";
		}
		// 提交保单
		if (xmlcode.equals("100083")) {
			addDataToJaia();
			returnMsg = MsgRealTimeTransDemo.sendmsgToPingAn(policyinfo, policyHolder, policyinsured, xmlcode);
		}
		// 撤销保单
		if (xmlcode.equals("101083")) {
			returnMsg = MsgRealTimeTransDemo.sendmsgToPingAn(xmlcode, policyNo);
			// 通过JSON返回信心给前端
			JSONObject msgJsonObject = new JSONObject();
			msgJsonObject.put("msg", "success");
			jsonObject.put("ret", returnMsg);
		}

		// 查询保单
		if (xmlcode.equals("100084")) {
			returnMsg = MsgRealTimeTransDemo.sendmsgToPingAn(xmlcode, policyNo);
			// 通过JSON返回信心给前端
			JSONObject msgJsonObject = new JSONObject();
			msgJsonObject.put("msg", "success");
			jsonObject.put("ret", returnMsg);
		}

		if (returnMsg.contains("生成保单成功")) {
			// 通过返回string创建doc的文档对象,解析XML
			Document doc = XMLUtil.LoadXMLFromString(returnMsg);
			Element headerElement = XMLUtil.getSingleElementByPathExp(doc, "/eaiAhsXml/Header");
			String TRAN_CODE = XMLUtil.getElementText(headerElement, "TRAN_CODE");
			String BANK_CODE = XMLUtil.getElementText(headerElement, "BANK_CODE");
			String BK_SERIAL = XMLUtil.getElementText(headerElement, "BK_SERIAL");
			String PA_ACCT_DATE = XMLUtil.getElementText(headerElement, "PA_ACCT_DATE");
			String PA_ACCT_TIME = XMLUtil.getElementText(headerElement, "PA_ACCT_TIME");
			String FT_SERIAL = XMLUtil.getElementText(headerElement, "FT_SERIAL");
			String REGION_CODE = XMLUtil.getElementText(headerElement, "REGION_CODE");
			String PA_RSLT_CODE = XMLUtil.getElementText(headerElement, "PA_RSLT_CODE");
			String PA_RSLT_MESG = XMLUtil.getElementText(headerElement, "PA_RSLT_MESG");
			Element policyInfoElement = XMLUtil.getSingleElementByPathExp(doc, "/eaiAhsXml/Response/policyInfo");
			String applyPolicyNo = XMLUtil.getElementText(policyInfoElement, "applyPolicyNo");
			String policyNo = XMLUtil.getElementText(policyInfoElement, "policyNo");
			String noticeNo = XMLUtil.getElementText(policyInfoElement, "noticeNo");
			String validateCode = XMLUtil.getElementText(policyInfoElement, "validateCode");
			String totalPremium = XMLUtil.getElementText(policyInfoElement, "totalPremium");

			// 打印所有返回参数
			System.out.println("TRAN_CODE" + ":" + TRAN_CODE);
			System.out.println("BANK_CODE" + ":" + BANK_CODE);
			System.out.println("BK_SERIAL" + ":" + BK_SERIAL);
			System.out.println("PA_ACCT_DATE" + ":" + PA_ACCT_DATE);
			System.out.println("PA_ACCT_TIME" + ":" + PA_ACCT_TIME);
			System.out.println("FT_SERIAL" + ":" + FT_SERIAL);
			System.out.println("REGION_CODE" + ":" + REGION_CODE);
			System.out.println("PA_RSLT_CODE" + ":" + PA_RSLT_CODE);
			System.out.println("PA_RSLT_MESG" + ":" + PA_RSLT_MESG);
			System.out.println("applyPolicyNo" + ":" + applyPolicyNo);
			System.out.println("policyNo" + ":" + policyNo);
			System.out.println("totalPremium" + ":" + totalPremium);
			System.out.println("noticeNo" + ":" + noticeNo);
			System.out.println("validateCode" + ":" + validateCode);

			// 通过JSON返回信心给前端
			JSONObject msgJsonObject = new JSONObject();
			msgJsonObject.put("msg", "success");
			jsonObject.put("ret", msgJsonObject);
		}

		// 出单成功后发放佣金
		// 佣金发放-建议佣金发放功能单独放一个地方，放支付订单里面会影响效率

		// 获取产品佣金率
		Product product = productService.findById(policyinfo.getProductId());
		double pb1 = product.getFirstBrokerage();// 20%
		double pb2 = product.getSecondBrokerage();// 1%
		double pb3 = product.getThridBrokerage();// 1%
		double[] pb = { pb1, pb2, pb3 };

		// // 获取会员等级佣金率
		// Salesman sales = slist.get(0);
		// SalesmanRank salesmanRank =
		// salesmanRankService.findById(sales.getSalesmanRankId());
		// double sb1 = salesmanRank.getFirstBrokerage();
		// double sb2 = salesmanRank.getSecondBrokerage();
		// double sb3 = salesmanRank.getThridBrokerage();
		// double[] sb = {sb1, sb2, sb3};

		/**
		 * 循环对每个用户添加对应的佣金发放日志，并发放佣金
		 */
		// 当前订单关系用户列表
		List<Salesman> slist = salesmanService.getInvateSalesmans(Integer.parseInt(policyinfo.getSalesman_id()));
		System.out.println(slist.size());
		for (int i = 0; i < slist.size(); i++) {
			Salesman sales = slist.get(i);
			BigDecimal amount = new BigDecimal(policyinfo.getAmount() * pb[i]);
			// 新增账户余额日志
			AccountBalanceLog accountBalanceLog = new AccountBalanceLog();
			accountBalanceLog.setId(SundryTest.uuid());
			accountBalanceLog.setInfo("佣金");
			accountBalanceLog.setSid(sales.getSid());
			accountBalanceLog.setAmount(amount);
			accountBalanceLog.setCreateDate(DateUtil.getCurrentDateStr1());
			accountBalanceLog.setType(AccountBalanceLog.AccountBalanceLogType.brokerage);
			accountBalanceLogService.save(accountBalanceLog);
			// 获取当前获得佣金用户，发放佣金
			sales.setMoney(sales.getMoney() + (policyinfo.getAmount() * pb1));
			salesmanService.saveorUpdate(sales);
			// 新增佣金发放日志
			BrokerageLog log = new BrokerageLog();
			log.setId(SundryTest.uuid());
			log.setCreateDate(DateUtil.getCurrentDateStr1());
			log.setSid(sales.getSid());
			log.setOrderId(policyinfo.getId());
			log.setOperator("SYSTEM");
			log.setBrokerageLogType(BrokerageLog.BrokerageLogType.orderbrokerage);
			log.setInfo("投保单自动分配佣金！");
			String salesmamNameString = sales.getRealName();
			log.setOrder_sname(salesmamNameString);
			log.setOrderTotalPrice(new BigDecimal(policyinfo.getAmount()));
			log.setAmount(amount);
			brokerageLogService.save(log);
		}

		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * 提交到前海人寿的接口
	 * 
	 * @return 返回内容
	 * @throws Exception
	 */
	public String sendToQianHai() throws Exception {
		// 添加到建安后台
		addDataToJaia();

		// 提交保单
		// 待获取的字段，先写死
		// 订单号，获取一个16位序列号，时间格式
		// 获取当前16位时间流水号
		String applyCode = DateUtil.GetDateRandom16();
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String channelId = propertiesUtil.readValue("channelId");
		String residentCity = propertiesUtil.readValue("residentCity");
		String residentProvince = propertiesUtil.readValue("residentProvince");
		String supplierBranch = propertiesUtil.readValue("supplierBranch");
		policyHolder.setCity("");
		;
		policyHolder.setProvince("");
		policyHolder.setDetailAddress("");
		policyinsured.setCity("");
		;
		policyinsured.setProvince("");
		policyinsured.setDetailAddress("");

		//SORT为NULL为报错
		policyinsured.setSort("");
		// 保单总额
		String premium = policyinfo.getAmount().toString();
		// 获取jsonString
		String jsonString = null;
		// jsonString += "{'policyInfo': {";
		// jsonString += "'amount': '" + policyinfo.getAmount() + "',";
		// jsonString += "'applyCode': '" + applyCode + "',";

		jsonString = "{'policyInfo': {" + "'amount': '" + policyinfo.getAmount() + "'," + "'applyCode': '" + applyCode
				+ "'," + "'channelId': '" + channelId + "'," + "'endDate': '" + policyinfo.getEndDate() + "',"
				+ "'salesCode': '300000030'," + "'flowCode': '04'," + "'insuredAndBeneficiaryList': [{"
				+ "'insuredRule': {'benefits': {},'choiceDuty': {},'outPut': {}}," + "'policyInsured': {"
				+ "'birthdate': '" + policyinsured.getBirthdate() + "'," + "'certEndDate': '',"
				+ "'certStartDate': ''," + "'certValidFlag': '2'," + "'certificateNo': '"
				+ policyinsured.getCertificateNo() + "'," + "'certificateType': '201'," + "'chiefInsuredRelation': '"
				+ policyinsured.getChiefInsuredRelation() + "'," + "'city': '" + policyinsured.getCity() + "',"
				+ "'count': 1," + "'county': '" + policyinsured.getCounty() + "'," + "'detailAddress': '"
				+ policyinsured.getDetailAddress() + "'," + "'email': '" + policyinsured.getEmail() + "',"
				+ "'holderRelation': '301'," + "'id': '" + UUIDUtil.uuid() + "'," + "'isChiefInsured': '"
				+ policyinsured.getIsChiefInsured() + "'," + "'isLegal': '" + policyinsured.getIsLegal() + "',"
				+ "'moblie': '" + policyinsured.getMoblie() + "'," + "'name': '" + policyinsured.getName() + "',"
				+ "'nationality': '" + policyinsured.getNationality() + "'," + "'policyId': '"
				+ policyinsured.getPolicyId() + "'," + "'premium': '" + premium + "'," + "'profession': '"
				+ policyinsured.getProfession() + "'," + "'province': '" + policyinsured.getProvince() + "',"
				+ "'residentCity': '" + residentCity + "'," + "'residentProvince': '" + residentProvince + "',"
				+ "'sex': '" + policyinsured.getSex() + "'," + "'sort': '" + policyinsured.getSort() + "',"
				+ "'supplierBranch': '" + policyinsured.getSupplierBranch() + "'," + "'telephone': '"
				+ policyinsured.getTelephone() + "'," + "'zipNo': '" + policyinsured.getZipNo() + "'}" + "}],"
				+ "'insuredSelect': '" + policyinfo.getInsuredSelect() + "'," + "'isLegal': '"
				+ policyinfo.getIsLegal() + "'," + "'orderCharge': {" + "'buyerAccountUsername': '',"
				+ "'payAmount': ''," + "'payBusinessCode': ''," + "'payCode': ''," + "'payMethod': '',"
				+ "'payer': ''," + "'sellerAccountUsername': ''" + "}," + "'orderCode': '9702004173'," + "'orderId': '"
				+ UUIDUtil.uuid() + "'," + "'policyHolder': {" + "'birthdate': '" + policyHolder.getBirthdate() + "',"
				+ "'certEndDate': ''," + "'certStartDate': ''," + "'certValidFlag': '2'," + "'certificateNo': '"
				+ policyHolder.getCertificateNo() + "'," + "'certificateType': '" + policyHolder.getCertificateType()
				+ "'," + "'city': '" + policyHolder.getCity() + "'," + "'county': '" + policyHolder.getCounty() + "',"
				+ "'detailAddress': '" + policyHolder.getDetailAddress() + "'," + "'email': '"
				+ policyHolder.getEmail() + "'," + "'id': '" + policyHolder.getId() + "'," + "'moblie': '"
				+ policyHolder.getMoblie() + "'," + "'name': '" + policyHolder.getName() + "'," + "'nationality': '"
				+ policyHolder.getNationality() + "'," + "'policyId': '" + policyHolder.getPolicyId() + "',"
				+ "'profession': '" + policyHolder.getProfession() + "'," + "'province': '"
				+ policyHolder.getProvince() + "'," + "'residentCity': '" + policyHolder.getResidentCity() + "',"
				+ "'residentProvince': '" + policyHolder.getResidentProvince() + "'," + "'sex': '"
				+ policyHolder.getSex() + "'," + "'supplierBranch': '" + supplierBranch + "'," + "'telephone': '"
				+ policyHolder.getTelephone() + "'," + "'zipNo': '" + policyHolder.getZipNo() + "'}," + "'policyId': '"
				+ policyinfo.getPolicyId() + "'," + "'productId': '" + policyinfo.getProductId() + "',"
				+ "'startDate': '" + policyinfo.getStartDate() + "'}" + "}";

		try {
			jsonString = new String(jsonString.getBytes("utf-8"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			// 加密前
			System.out.println("加密前：" + jsonString + "qinghaimd5");
			String md5str = MD5Utils.createMD5_utf8(jsonString + "qinghaimd5");
			System.out.println("" + md5str);

			PlantformserviceStub plantformservicestub = new PlantformserviceStub();
			AsyncOrderData asyncorderdata = new AsyncOrderData();
			jsonString = "JSON||04||" + jsonString;
			asyncorderdata.setJsonstr(jsonString);
			asyncorderdata.setMd5Salt(md5str);

			System.out.println(jsonString);
			System.out.println(md5str);

			AsyncOrderDataResponse asyncorderdataresponse;
			asyncorderdataresponse = plantformservicestub.asyncOrderData(asyncorderdata);
			System.out.println("第三方客户端测试:+++++" + asyncorderdataresponse.get_return());
			ResponseUtil.write(ServletActionContext.getResponse(), asyncorderdataresponse.get_return());
			if (asyncorderdataresponse.get_return().contains("SUCCESS")) {

				// 出单成功后发放佣金
				// 佣金发放-建议佣金发放功能单独放一个地方，放支付订单里面会影响效率

				// 获取产品佣金率
				Product product = productService.findById(policyinfo.getProductId());
				double pb1 = product.getFirstBrokerage();// 20%
				double pb2 = product.getSecondBrokerage();// 1%
				double pb3 = product.getThridBrokerage();// 1%
				double[] pb = { pb1, pb2, pb3 };

				// // 获取会员等级佣金率
				// Salesman sales = slist.get(0);
				// SalesmanRank salesmanRank =
				// salesmanRankService.findById(sales.getSalesmanRankId());
				// double sb1 = salesmanRank.getFirstBrokerage();
				// double sb2 = salesmanRank.getSecondBrokerage();
				// double sb3 = salesmanRank.getThridBrokerage();
				// double[] sb = {sb1, sb2, sb3};

				/**
				 * 循环对每个用户添加对应的佣金发放日志，并发放佣金
				 */
				// 当前订单关系用户列表
				List<Salesman> slist = salesmanService
						.getInvateSalesmans(Integer.parseInt(policyinfo.getSalesman_id()));
				System.out.println(slist.size());
				for (int i = 0; i < slist.size(); i++) {
					Salesman sales = slist.get(i);
					BigDecimal amount = new BigDecimal(policyinfo.getAmount() * pb[i]);
					// 新增账户余额日志
					AccountBalanceLog accountBalanceLog = new AccountBalanceLog();
					accountBalanceLog.setId(SundryTest.uuid());
					accountBalanceLog.setInfo("佣金");
					accountBalanceLog.setSid(sales.getSid());
					accountBalanceLog.setAmount(amount);
					accountBalanceLog.setCreateDate(DateUtil.getCurrentDateStr1());
					accountBalanceLog.setType(AccountBalanceLog.AccountBalanceLogType.brokerage);
					accountBalanceLogService.save(accountBalanceLog);

					// 新增佣金发放日志
					BrokerageLog log = new BrokerageLog();
					log.setId(SundryTest.uuid());
					log.setCreateDate(DateUtil.getCurrentDateStr1());
					log.setSid(sales.getSid());
					log.setOrderId(policyinfo.getId());
					log.setOperator("SYSTEM");
					log.setBrokerageLogType(BrokerageLog.BrokerageLogType.orderbrokerage);
					log.setInfo("投保单自动分配佣金！");
					String salesmamNameString = sales.getRealName();
					log.setOrder_sname(salesmamNameString);
					log.setOrderTotalPrice(new BigDecimal(policyinfo.getAmount()));
					log.setAmount(amount);
					brokerageLogService.save(log);
					// 获取当前获得佣金用户，发放佣金
					sales.setMoney(sales.getMoney() + (policyinfo.getAmount() * pb[i]));
					salesmanService.saveorUpdate(sales);
				}
				return "SUCCESS";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 提交受益人信息
	private String insert_Policyinsured() {
		try {
			policyinsured = Return_Code.getGson().fromJson(shouyirenxx, Policyinsured.class);
			policyinsured.setCreateDate(DateUtil.getCurrentDateStr1());
			policyinsured.setCertEndDate(null);
			policyinsured.setCertStartDate(null);
			policyinsured.setPolicyId(UUIDUtil.uuid());
			policyinsured.setId(UUIDUtil.uuid());
			// 插入受益人信息
			policyinsuredService.insert(policyinsured);
		} catch (Exception e) {
			e.printStackTrace();
			return "受益人信息插入失败！";
		}
		return SUCCESS;
	}

	// 提交投保人信息
	private String insert_PolicyHolder() {
		try {
			policyHolder = Return_Code.getGson().fromJson(toubaorenxx, PolicyHolder.class);
			policyHolder.setCreateDate(DateUtil.getCurrentDateStr1());
			policyHolder.setCertEndDate(null);
			policyHolder.setCertStartDate(null);
			policyHolder.setPolicyId(UUIDUtil.uuid());
			policyHolder.setId(UUIDUtil.uuid());
			policyHolder.setDetailAddress(null);
			// 插入投保人信息
			policyholderService.insert(policyHolder);
		} catch (Exception e) {
			e.printStackTrace();
			return "投保人信息插入失败！";
		}
		return SUCCESS;
	}

	// 提交保单信息
	private String insert_PolicyInfo() {
		try {
			policyinfo = Return_Code.getGson().fromJson(baodanxx, PolicyInfo.class);
			policyinfo.setPolicyId(UUIDUtil.uuid());
			// 获取当前16位时间流水号
			policyinfo.setApplyCode(DateUtil.GetDateRandom16());
			policyinfo.setPolicyHolder_id(policyHolder.getId());
			policyinfo.setCreateDate(DateUtil.getCurrentDateStr1());
			policyinfo.setId(UUIDUtil.uuid());
			policyinfo.setPolicyHolder_name(policyHolder.getName());
			// 根据sid获取会员相关信息
			Salesman salesman = salesmanService.findById(Integer.parseInt(policyinfo.getSalesman_id()));
			policyinfo.setPolicyHolder_name(salesman.getRealName());
			// 根据productid获取产品相关信息
			System.out.println(policyinfo.getProductId());
			Product product = productService.getProductById(policyinfo.getProductId());
			policyinfo.setProduct_name(product.getName());

			if (policyinfo.getProductId().equals("00820") || policyinfo.getProductId().equals("00821")
					|| policyinfo.getProductId().equals("00822")) {
				System.out.println(policyinfo.getProductId());
			} else {
				// 获取保单截止日期
				String endDate = this.getendDate();
				String startDate = this.getstartDate();
				policyinfo.setStartDate(startDate);
				policyinfo.setEndDate(endDate);
			}

			// 插入保单信息
			policyInfoService.insert(policyinfo);
			System.out.println(policyinfo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("保单信息插入失败！");
			return "保单信息插入失败！";
		}
		return SUCCESS;
	}

	// 添加支付信息
	private String insert_Ordercharge() {
		try {
			OrderCharge orderCharge = new OrderCharge();
			// 设置支付流水号为，当前时间16位随机树
			orderCharge.setPayCode(DateUtil.GetDateRandom16());
			orderCharge.setId(UUIDUtil.uuid());
			orderCharge.setPayAmount(policyinfo.getAmount().toString());
			orderCharge.setBuyerAccountUsername("");
			Salesman salesman = salesmanService.findById(Integer.parseInt(policyinfo.getSalesman_id()));
			orderCharge.setPayer(salesman.getRealName());
			orderCharge.setPayMethod("1");// 默认支付宝
			orderCharge.setSellerAccountUsername(salesman.getPhone());
			// 插入支付信息
			orderchargeService.insert(orderCharge);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("支付信息插入失败！");
			return "支付信息插入失败！";
		}
		return SUCCESS;

	}

	// 扣款操作
	private String update_Salesman_Money() {
		try {
			Salesman salesman = salesmanService.findById(Integer.parseInt(policyinfo.getSalesman_id()));
			if (salesman.getMoney() - policyinfo.getAmount() >= 0) {
				salesman.setMoney(salesman.getMoney() - policyinfo.getAmount());
			} else {
				return "账户余额不足，请充值！";
			}
			salesmanService.update(salesman);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getOrderchargeString() {
		return orderchargeString;
	}

	public void setOrderchargeString(String orderchargeString) {
		this.orderchargeString = orderchargeString;
	}

	public String getBaodanxx() {
		return baodanxx;
	}

	public void setBaodanxx(String baodanxx) {
		this.baodanxx = baodanxx;
	}

	public String getShouyirenxx() {
		return shouyirenxx;
	}

	public void setShouyirenxx(String shouyirenxx) {
		this.shouyirenxx = shouyirenxx;
	}

	public String getToubaorenxx() {
		return toubaorenxx;
	}

	public void setToubaorenxx(String toubaorenxx) {
		this.toubaorenxx = toubaorenxx;
	}

	public String getXmlcode() {
		return xmlcode;
	}

	public void setXmlcode(String xmlcode) {

		this.xmlcode = xmlcode;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

}
