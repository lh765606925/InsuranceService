package com.insurance.action;


import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.insurance.model.ProductPrice;
import com.insurance.service.ProductPriceService;
import com.insurance.util.DateUtil;
import com.insurance.util.ResponseUtil;

@Controller
public class ProductPriceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private ProductPriceService productPriceService;

	private ProductPrice productPrice;
	private String startDate;
	private String endDate;
	private String productId;
	private int days;

	public String getPrice() throws Exception {
		if (startDate == null) {
			return "startDate is null";
		}
		if (endDate == null) {
			return "endDate is null";
		}
		if (productId == null) {
			return "productId is null";
		}
		System.out.println(startDate);
		System.out.println(endDate);
		// 获取时间差，中间有几天
		days = DateUtil.daysBetween(startDate, endDate);
		// 获取当前产品报价
		Double price = productPriceService.getPrice(days, productId);
		System.out.println(days);
		System.out.println(productId);
		System.out.println(price);
		// 返回给前端
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("price", price+0.00);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

}
