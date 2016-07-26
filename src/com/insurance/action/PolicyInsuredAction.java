package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.model.PageBean;
import com.insurance.model.Policyinsured;
import com.insurance.service.PolicyinsuredService;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;

public class PolicyInsuredAction extends BaseAction {
	private static final long serialVersionUID = 3083745961478037858L;
	@Resource
	private PolicyinsuredService policyInsuredService;

	private Policyinsured policyinsured;
	// 受益人信息
	private String shouyirenxx;

	public String getShouyirenxx() {
		return shouyirenxx;
	}

	public void setShouyirenxx(String shouyirenxx) {
		this.shouyirenxx = shouyirenxx;
	}

	/**
	 * 受益人管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showList() throws Exception {
		request.setAttribute("leftPage", "common/policymanage_left.jsp");
		request.setAttribute("mainPage", "salestable/policyinsuredManage.jsp");
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
			long total = policyInsuredService.getPolicyinsuredCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<Policyinsured> slist = policyInsuredService.findPolicyinsured(new PageBean(liMit, offSet), new Object[]{});
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
	 * 添加受益人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		JSONObject result = new JSONObject();
		// shouyirenxx json对象 受益人信息
		System.out.println("添加受益人接收：" + shouyirenxx);
		if (shouyirenxx == null) {
			return "shouyirenxx is null";
		} else {
			try {
				policyinsured = Return_Code.getGson().fromJson(shouyirenxx, Policyinsured.class);
				// 插入数据
				String rs = policyInsuredService.insert(policyinsured);
				result.put("insert", rs);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				e.printStackTrace();
				return "shouyirenxx fromJson failure";
			}
		}
		return null;
	}
}
