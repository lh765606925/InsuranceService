package com.insurance.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.insurance.model.Version;
import com.insurance.service.VersionService;
import com.insurance.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class VersionAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Resource
	private VersionService versionService;

	/**
	 * 移动端查询版本信息
	 * 
	 * @throws Exception
	 */
	public void findNewVersionByMobile() throws Exception {
		try {
			Version version = versionService.findNewVersion();
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(version, jsonConfig);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示历史版本列表
	 * 
	 * @throws Exception
	 */
	public String admin_manage() throws Exception {
		request.setAttribute("leftPage", "common/left.jsp");
		request.setAttribute("mainPage", "version/manage.jsp");
		return SUCCESS;
	}

	/**
	 * @category 后台查询所有版本信息列表
	 * @return
	 * @throws Exception
	 */
	public String admin_list() throws Exception {
		try {
			Version version = versionService.findNewVersion();
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(version, jsonConfig);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发布新版本
	 * @throws Exception
	 */
	public void admin_update_NewVersion() throws Exception {
		try {
			Version version = versionService.findNewVersion();
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(version, jsonConfig);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新版本信息
	 * 
	 * @throws Exception
	 */
	public void updateVersion() throws Exception {
		// Todo:
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
