package com.insurance.util;

/**
 * APP-接口访问
 * @author huzhihong
 *
 * 创建日期：2015年11月9日
 */
public class WapConstant {

	public static String URLSTRING = "/InsuranceService/back/";
	public static String IP = "http://192.168.1.151:8080";
	public static String URLIMAGEString = "/InsuranceData";

	/**
	 * @category 读取版本号
	 * @case http://192.168.1.150:8080/InsuranceService/back/version_findNewVersionByMobile.action
	 * @return_OK {"rows":[{"id":"2276493b6f5642a0909f50be1dde2e22","versioninfo":"最新版本","versioncode":"1.2.1","releasedate":"2015-05-28 15:54:33"}]} 
	 */
	public static String READ_VERSION = "/InsuranceService/back/version_findNewVersionByMobile.action";
	/**
	 * @category  下载新版本
	 * @case http://192.168.1.150:8080/InsuranceData/apk/Jaia.apk
	 * @return_OK 弹出下载框
	 */
	public static String UPDATE_VERSION = "/InsuranceData/apk/Jaia.apk";
	/**
	 * @category  APP-注册
	 * @case1 http://192.168.1.150:8080/InsuranceService/back/salesman_registerByMobile.action?salesbean={"phone":"13268527402","email":"13268527402@qq.com","passWord":"123456","sex":"1","name":"huzhihong","realName":"huzi"}
	 * @case-浏览器端 http://192.168.1.150:8080/InsuranceService/back/salesman_registerByMobile.action?phone=13268527400&email=13268527400@qq.com&passWord=123456&sex=男&name='一路上有你'&realName='胡子'
	 * @return1 该手机号已被注册 * @return2 该邮箱已被绑定 * @return3 该昵称已存在 * @return4 注册成功
	 */
	public static String INSERTString = URLSTRING + "salesman_registerByMobile.action";

	/**
	 *@category  登陆
	 *@case 
	 */
	public static String LOGINString = "salesman_login.action";
	// 修改密码
	public static String UPDATEPASS = "salesman_modifyPass.action";
	// 添加银行卡
	public static String ADDBANKString = "salesman_updateByMobile.action";
	// 修改个人资料
	public static String UPDATEString = "salesman_updateByMobile.action";
	// 添加证件信息
	public static String iNSERTIDString = "salesman_updateZJ.action";
	// 添加头像
	public static String addHead = "salesman_uploadhead.action";
	// 修改头像
	public static String UPDATEHEADIMAGE = "salesman_uploadhead.action";

	// 读取余额的信息
	public static String TOTALMoneyString = "finance_list.action";
	// 充值
	public static String deposit_addDepositByMobile = "deposit_addDepositByMobile.action";
	// 查询用户充值记录
	public static String AccountRecord = "deposit_selectPagerDepositByMobile.action";

	// 投保
	public static String product_send = "/InsuranceService/back/product_sendToWebService.action";

	// //投保人信息
	// public static String product_tbr = "policyholder_insert.action";
	// //受益人信息
	// public static String product_syr = "policyinsured_insert.action";

	public static String product_insert = "policyinfo_insert.action";

	// 车险
	public static String product_cxjiekou = "product_mobile_showProductCX.action";
	// 人寿险
	public static String product_rsjiekou = "product_mobile_showProductRSX.action";
	// 综合险
	public static String product_zhjiekou = "product_mobile_showProductZHX.action";
	// 保险详细
	public static String product_xx = "productDetailinfo_list.action";

	// 平安保险价格
	public static String product_priceForPingAn = "productprice_getPrice.action";

	/**
	 * @category 热门文章
	 * @deprecated 不带参数默认查询前5条，带参数查询指定条数的热门文章；
	 * @Case http://192.168.1.150:8080/InsuranceService/back/article_App_show_hot_list.action?hot_total=6
	 * @param hot_total
	 * @paramType integer
	 */
	public static String Hot_article_list = "article_App_show_hot_list.action";

	/**
	 * @category 热门产品
	 * @deprecated 不带参数默认查询前4条，带参数查询指定条数的热门产品；
	 * @Case http://192.168.1.150:8080/InsuranceService/back/product_App_show_hot_list.action?hot_total=4
	 * @param hot_total
	 * @paramType integer
	 */
	public static String Hot_product_list = "product_App_show_hot_list.action";

	// policyinfo_submitPolicyinfo.action提交保单接口

	/**
	 * @category  App查询文章列表 BY 文章栏目，分页查询文章（标题和ID）
	 * @param 4028811650c5c0680150c5c4b82e0000
	 * @case http://192.168.1.150:8080/InsuranceService/back/article_app_show_list_by_articlecategory.action?articlecategory_id=4028811650c5c0680150c5c4b82e0000
	 */
	public static String article_app_show_list_by_articlecategory = "article_app_show_list_by_articlecategory.action";

	/**
	 * @category App端查询文章详情
	 * @param 文章ID=4028811650adb51a0150adb7968b0001
	 * @case http://192.168.1.150:8080/InsuranceService/back/article_app_show_detail.action?id=4028811650adb51a0150adb7968b0001
	 */
	public static String article_app_show_detail = "article_app_show_detail.action";

	/**
	 * @category APP-查询我的订单
	 * @param salesman_id,pagesize, pageindex
	 * @case http://192.168.1.150:8080/InsuranceService/back/policyinfo_app_show_order_list.action?salesman_id=124
	 * @return
	 * @throws Exception
	 */
	public static String policyinfo_app_show_order_list = "policyinfo_app_show_order_list.action";

	/**
	 * @category 密码加密，仅用于密码全部加密一次，属于一次性接口
	 * @case  http://localhost:8080/InsuranceService/back/salesman_updatepassworldtoMd5.action
	 */
	@SuppressWarnings("unused")
	private static String update_passworld_Md5 = "back/salesman_updatepassworldtoMd5.action";

	/**
	 * @category 统计会员
	 * @case http://gandong.3gso.com:8080/InsuranceService/back/salesman_Countmerber.action?phone=13268527439
	 * @param phone
	 * @return
	 */
	public static String Countmerber = "salesman_Countmerber.action";
	
	
	/**
	 * @category 查询一级会员
	 * @case http://gandong.3gso.com:8080/InsuranceService/back/salesman_CountFirstmerber.action?phone=13268527439
	 * @param phone
	 * @return
	 */
	public static String CountFirstmerber = "salesman_CountFirstmerber.action";
	
	/**
	 * @category 查询二级会员
	 * @case http://gandong.3gso.com:8080/InsuranceService/back/salesman_CountSecondmerber.action?phone=13268527439
	 * @param phone
	 * @return
	 */
	public static String CountSecondmerber = "salesman_CountSecondmerber.action";
	
	/**
	 * @category 查询三级会员
	 * @case http://gandong.3gso.com:8080/InsuranceService/back/salesman_CountThreemerber.action?phone=13268527439
	 * @param phone
	 * @return
	 */
	public static String CountThreemerber = "salesman_CountThreemerber.action";
	
	/**
	 * @category 查询四级会员
	 * @case http://gandong.3gso.com:8080/InsuranceService/back/salesman_CountFourmerber.action?phone=13268527439
	 * @param phone
	 * @return
	 */
	public static String CountFourmerber = "salesman_CountFourmerber.action";
	
	/**
	 * @category 查询五级会员
	 * @case http://gandong.3gso.com:8080/InsuranceService/back/salesman_CountFivemerber.action?phone=13268527439
	 * @param phone
	 * @return
	 */
	public static String CountFivemerber = "salesman_CountFivemerber.action";
	
}
