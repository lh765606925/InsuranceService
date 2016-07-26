package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.model.PageBean;
import com.insurance.model.Partners;
import com.insurance.service.PartnersService;
import com.insurance.util.ResponseUtil;

/**
 * 合作伙伴Action
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月5日
 */
public class PartnersAction  extends BaseAction {

	private static final long serialVersionUID = -1868909653125759921L;
	@Resource
	private PartnersService partnersService;
	
	private Partners partners;
	
	
	/**
	 * 合作伙伴管理
	 * @return
	 * @throws Exception
	 */
	public String showList() throws Exception {
		request.setAttribute("leftPage", "product/left.jsp");
		request.setAttribute("mainPage", "partners/Manage.jsp");
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
			long total = partnersService.getPartnersCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<Partners> slist = partnersService.findPartners(new PageBean(liMit, offSet), new Object[]{});
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
	public void insert() {
		partnersService.insert(partners);
	}


	public void update() throws Exception {
		partnersService.update(partners);
	}
	
	public Partners getPartners() {
		return partners;
	}

	public void setPartners(Partners partners) {
		this.partners = partners;
	}

}
