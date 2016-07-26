package com.insurance.action;

import java.util.List;







import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.insurance.model.Function;
import com.insurance.model.Power;
import com.insurance.service.PowerService;
import com.insurance.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
@Controller
/**
 * 权限action
 * @author fly
 * 2014-11-26
 */
public class PowerAction extends ActionSupport implements ServletRequestAware{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PowerService powerService;
	private HttpServletRequest request;
	private List<Function> functionList;
	private Power power;

	private Function function;
	private String ids;
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public String showPower() throws Exception{
		request.setAttribute("leftPage", "common/power_left.jsp");
		request.setAttribute("mainPage", "powerManage.jsp");
		return SUCCESS;
	}
	
	public String treeGrid() throws Exception{
		JSONArray jsonArray= powerService.getListByParentId(-1);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}

	public String addPower() throws Exception{
		powerService.addPower(power);
		JSONObject result = new JSONObject();
			 result.put("success", true);
		 ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String delPower() throws Exception{
		Power p = powerService.findPowerById(power.getPid());
		if(p!=null){
			powerService.deletePower(p);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String updatePower() throws Exception{
		powerService.updatePower(power);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
