package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.insurance.model.AccountBalanceLog;
import com.insurance.service.AccountBalanceLogService;
import com.insurance.service.BaseService;
import com.insurance.util.PageUtil;
import com.insurance.util.ResponseUtil;

@Controller
public class AccountBalanceLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int sid;// 会员ID

	@Resource
	private AccountBalanceLogService accountBalanceLogService;
	@Resource
	private BaseService<AccountBalanceLog, String> baseService;

	public String manage() {
		request.setAttribute("leftPage", "common/salesman_left.jsp");
		request.setAttribute("mainPage", "member/account_balance_log.jsp");
		return SUCCESS;
	}

	/**
	 * @name 查询所有日志列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		long total = accountBalanceLogService.getTotalCount();
		List<AccountBalanceLog> slist = accountBalanceLogService.find(PageUtil.getPageBean(offset, limit, total));
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
	 * 查询指定SID用户日志列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listBySid() throws Exception {
		if (sid == 0) {
			return "参数SID不存在！";
		}
		long total = accountBalanceLogService.getTotalCount(sid);
		List<AccountBalanceLog> slist = accountBalanceLogService.find(PageUtil.getPageBean(offset, limit, total),
				new Object[] { sid });
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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
}
