package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.model.PageBean;
import com.insurance.model.PolicyHolder;
import com.insurance.service.PolicyholderService;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;

//@Controller
public class PolicyHolderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private PolicyholderService policyholderService;
	private PolicyHolder policyholder;
	// toubaorenxx json对象 投保人信息
	private String toubaorenxx;

	public String getToubaorenxx() {
		return toubaorenxx;
	}

	public void setToubaorenxx(String toubaorenxx) {
		this.toubaorenxx = toubaorenxx;
	}

	/**
	 * 投保人管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showList() throws Exception {
		request.setAttribute("leftPage", "common/policymanage_left.jsp");
		request.setAttribute("mainPage", "salestable/policyholderManage.jsp");
		return SUCCESS;
	}

	/**
	 * 获取数据list
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
		try {
			long total = policyholderService.getPolicyHolderCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<PolicyHolder> slist = policyholderService.findPolicyHolder(new PageBean(liMit, offSet), new Object[]{});
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
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
	 * 添加投保人
	 * 
	 * @return
	 * @throws Exception
	 */

	// 保存
//	@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "member.username", message = "用户名不允许为空!"), @RequiredStringValidator(fieldName = "member.password", message = "密码不允许为空!"), @RequiredStringValidator(fieldName = "member.email", message = "E-mail不允许为空!")}, requiredFields = {@RequiredFieldValidator(fieldName = "member.point", message = "积分不允许为空!"), @RequiredFieldValidator(fieldName = "member.deposit", message = "预存款不允许为空!"), @RequiredFieldValidator(fieldName = "member.memberRank.id", message = "会员等级不允许为空!"), @RequiredFieldValidator(fieldName = "member.isAccountEnabled", message = "是否启用不允许为空!")}, stringLengthFields = {
//			@StringLengthFieldValidator(fieldName = "member.username", minLength = "2", maxLength = "20", message = "用户名长度必须在${minLength}到${maxLength}之间!"), @StringLengthFieldValidator(fieldName = "member.password", minLength = "4", maxLength = "20", message = "密码长度必须在${minLength}到${maxLength}之间!")}, emails = {@EmailValidator(fieldName = "member.email", message = "E-mail格式错误!")}, intRangeFields = {@IntRangeFieldValidator(fieldName = "member.point", min = "0", message = "积分必须为零或正整数!")}, regexFields = {@RegexFieldValidator(fieldName = "product.name", regexExpression = "^[0-9a-z_A-Z\u4e00-\u9fa5]+$", message = "用户名只允许包含中文、英文、数字和下划线!")})
	public String insert() throws Exception {
		JSONObject result = new JSONObject();
		// toubaorenxx json对象 投保人信息
		System.out.println("添加投保人接收：" + toubaorenxx);
		if (toubaorenxx == null) {
			result.put("error", "policyholderbean is null");
			return "policyholderbean is null";
		} else {


			try {
				policyholder = Return_Code.getGson().fromJson(toubaorenxx, PolicyHolder.class);

				// 插入数据
				String rs = policyholderService.insert(policyholder);
				result.put("insert", rs);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("error", "policyholderbean fromJson failure");
				return "policyholderbean fromJson failure";
			}
		}

		return null;
	}

	/**
	 * 移动端修改投保人资料
	 * 
	 * @throws Exception
	 */
	public String updateByMobile() throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("修改投保人资料接收：" + toubaorenxx);
		if (toubaorenxx == null) {
			result.put("error", "policyholderbean is null");
			return "policyholderbean is null";
		} else {
			try {
				policyholder = Return_Code.getGson().fromJson(toubaorenxx, PolicyHolder.class);
				result.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("error", "policyholderbean fromJson failure");
				return "policyholderbean fromJson failure";
			}
			policyholderService.update(policyholder);
		}

		System.out.println("修改资料返回：" + result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

}
