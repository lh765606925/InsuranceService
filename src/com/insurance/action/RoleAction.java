package com.insurance.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.insurance.model.Role;
import com.insurance.service.RoleService;
import com.insurance.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
@Controller
/**
 * 角色action
 * @author fly
 * 2014-11-26
 */
public class RoleAction extends ActionSupport implements ServletRequestAware{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private RoleService roleService;
	private HttpServletRequest request;
	
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	private List<Role> rlist;
	
	public List<Role> getRlist() {
		return rlist;
	}

	public void setRlist(List<Role> rlist) {
		this.rlist = rlist;
	}
	
	private Role role;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String showRole() throws Exception{
		request.setAttribute("leftPage", "common/role_left.jsp");
		request.setAttribute("mainPage", "roleManage.jsp");
		return SUCCESS;
	}

	public String list() throws Exception{
		List<Role> rlist=roleService.finRoleList();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"managerList"});
		JSONArray rows = JSONArray.fromObject(rlist,jsonConfig);
		ResponseUtil.write(ServletActionContext.getResponse(), rows);		
		return null;
	}
	
	public String delete() throws Exception{
		if(ids!=null){
			JSONObject result = new JSONObject();
			if(!ids.contains(",")){
				Role r = roleService.findroleById(Integer.parseInt(ids));
				roleService.deleterole(r);
				result.put("success", true);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			}
		}
		return null;
	}
	
	public String add() throws Exception{
		JSONObject result = new JSONObject();
		if(role!=null){
			roleService.addrole(role);
			result.put("success", true);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
