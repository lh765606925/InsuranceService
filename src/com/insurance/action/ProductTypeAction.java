package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.http.Header;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonArray;
import com.insurance.model.Function;
import com.insurance.model.Power;
import com.insurance.model.Product;
import com.insurance.model.ProductType;
import com.insurance.service.PowerService;
import com.insurance.service.ProductTypeService;
import com.insurance.util.ListToPageByJson;
import com.insurance.util.PageUtil;
import com.insurance.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台Action类 - 产品类型表 20150714
 * 
 */
@Controller
public class ProductTypeAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 8895838200173152426L;

	@Resource
	private ProductTypeService productTypeService;

	private ProductType productType;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<Function> functionList;
	private Power power;
	private Function function;
	private String idsString;
	private String url;
	private int typeid;

	private String pagesize;

	private String pageindex;

	private long total;

	private String limit;

	private String offset;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getPageindex() {
		return pageindex;
	}

	public void setPageindex(String pageindex) {
		this.pageindex = pageindex;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String showProductType() throws Exception {
		request.setAttribute("leftPage", "common/goods_left.jsp");
		request.setAttribute("mainPage", "product/product_type_manage.jsp");
		return SUCCESS;
	}

	public String treeGrid() throws Exception {
		JSONArray jsonArray = productTypeService.getListByParentId(-1);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}

	/**
	 * 获取一级类型列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTypelevel1() throws Exception {
		List<ProductType> list = productTypeService.showTypebylevel(1);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(list, jsonConfig);
		jsonObject.put("message", "");
		jsonObject.put("value", rows);
		jsonObject.put("code", 200);
		jsonObject.put("redirect", "");
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * @name 后台分页查询产品分类
	 * @throws Exception
	 */
	public void admin_show_list() throws Exception {

		long total = productTypeService.getTotalCount();
		List<ProductType> slist = productTypeService.find(PageUtil.getAdmin_PageBean(offset, limit, total));
		ListToPageByJson<ProductType> toPageByJson = new ListToPageByJson<ProductType>();
		toPageByJson.putToJson(slist);
	}

	/**
	 * 获取一级类型列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String test() throws Exception {
		List<ProductType> list = productTypeService.showTypebylevel(1);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(list, jsonConfig);
		jsonObject.put("message", "");
		jsonObject.put("value", rows);
		jsonObject.put("code", 200);
		jsonObject.put("redirect", "");
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	public JSON test2() throws Exception {
		List<ProductType> list = productTypeService.showTypebylevel(1);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(list, jsonConfig);
		jsonObject.put("message", "");
		jsonObject.put("value", rows);
		jsonObject.put("code", 200);
		jsonObject.put("redirect", "");
		return jsonObject;

	}

	/**
	 * 获取当前类型列表
	 * 
	 * @pram typeid 类型ID
	 * @return
	 * @throws Exception
	 */
	public String showTypebyTypeid() throws Exception {
		List<ProductType> list = productTypeService.showTypebyTypeid(typeid);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(list, jsonConfig);
		jsonObject.put("message", "");
		jsonObject.put("value", rows);
		jsonObject.put("code", 200);
		jsonObject.put("redirect", "");
		System.out.println(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	// 获取所有类型列表
	public String showList() throws Exception {
		List<ProductType> list = productTypeService.findAllList();
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(list, jsonConfig);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	public String addProductType() throws Exception {
		productTypeService.addProductType(productType);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public String delProductType() throws Exception {
		ProductType p = productTypeService.findProductTypeById(power.getPid());
		if (p != null) {
			productTypeService.deleteProductType(p);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public String updateProductType() throws Exception {
		productTypeService.updateProductType(productType);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public String getIdsString() {
		return idsString;
	}

	public void setIdsString(String idsString) {
		this.idsString = idsString;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		this.response = response;
	}
}