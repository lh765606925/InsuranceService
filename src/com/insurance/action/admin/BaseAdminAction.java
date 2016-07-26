package com.insurance.action.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAdminAction extends ActionSupport implements ServletRequestAware {

	public static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected String offset;
	protected String limit;

	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

	protected String ids;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
