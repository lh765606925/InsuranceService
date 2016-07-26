package com.insurance.action;

import java.sql.Timestamp;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.insurance.model.Manager;
import com.insurance.model.PageBean;
import com.insurance.model.Role;
import com.insurance.service.ManagerService;
import com.insurance.util.GetIpUtil;
import com.insurance.util.MD5Utils2;
import com.insurance.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
/**
 * 管理员Action
 * @author fly
 * 2014-11-18
 */
public class ManagerAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	@Resource
	private ManagerService managerService;
	
	private List<Manager> managerList;
	
	private String ids;
	
	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Manager> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<Manager> mangerList) {
		this.managerList = mangerList;
	}

	private String error;
	private Manager manager;

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 管理员登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		HttpSession session = request.getSession();
		Manager currentManager = managerService
				.findManagerByNameAndPassword(manager);
		if (currentManager != null) {
			session.setAttribute("currentManager", currentManager);
			currentManager.setDate(new Timestamp(System.currentTimeMillis()));
			currentManager.setIp(GetIpUtil.getRemoteHost(request));
			managerService.updateManager(currentManager);
			return SUCCESS;
		} else {
			error = "用户名或密码错误";
			return ERROR;
		}
	}
	
	public String showManager() throws Exception{
		request.setAttribute("leftPage", "common/manager_left.jsp");
		request.setAttribute("mainPage", "managerManage.jsp");
		return SUCCESS;
	}
	
	private String offset;
	private String limit;

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
	
	public String list() throws Exception{
        if(offset==null){
        	offset="0";
        }
        if(limit==null){
        	limit="10";
        }
        int offSet=Integer.parseInt(offset);
        int liMit=Integer.parseInt(limit);
        long total = managerService.getManagerCount();
        if(offSet>=total&&total!=0){
        	offSet=offSet-liMit;
        }
		List<Manager> mlist=managerService.findManager(new PageBean(liMit, offSet), new Object[]{});
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        jsonConfig.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"roleId","roleName"},Role.class));
		JSONArray rows =JSONArray.fromObject(mlist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
        ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}
	
	public String add() throws Exception{
		JSONObject result = new JSONObject();
		try {
			managerService.saveMananger(manager);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String update() throws Exception{
		Manager m = managerService.findbyId(manager.getMid());
		if(m!=null){
			m.setPassWord(MD5Utils2.getMD5(this.manager.getPassWord().getBytes()));
			managerService.updateManager(m);
			return null;
		}else{
			return null;
		}
		
	}
	
	public String delete() throws Exception{
		JSONObject result=new JSONObject();
		String managerIds[]=ids.split(",");
		for(int i=0;i<managerIds.length;i++){
		 Manager man=managerService.findbyId(Integer.parseInt(managerIds[i]));
		 managerService.deleteManager(man);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
    
	public String logout(){
		request.getSession().invalidate();
		return "logout";
	}
	
}
