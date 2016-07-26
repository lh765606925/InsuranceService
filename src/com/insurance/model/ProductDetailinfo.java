package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 产品详情表
 * 
 * @author laohu
 * 
 */
@Entity
@Table(name = "t_productDetailinfo")
public class ProductDetailinfo extends BaseEntity {

	private static final long serialVersionUID = 8356471897228240224L;
	private String name;// 责任名称
	private String product_id;// 产品ID
	private String insuranceliability;// 保险责任
	private String price;// 起始价格
	private String amount;// 保额
	private String pricerate;// 费用率
	private String note;// 备注
	private String plan1;// 计划1保额
	private String plan2;// 计划2保额
	private String plan3;// 计划3保额
	private String plan4;// 签证宝保额
	private String productcode;// 险种代码
	private String responsibilitycode;// 责任代码

	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getResponsibilitycode() {
		return responsibilitycode;
	}
	public void setResponsibilitycode(String responsibilitycode) {
		this.responsibilitycode = responsibilitycode;
	}
	public String getPlan1() {
		return plan1;
	}
	public void setPlan1(String plan1) {
		this.plan1 = plan1;
	}
	public String getPlan2() {
		return plan2;
	}
	public void setPlan2(String plan2) {
		this.plan2 = plan2;
	}
	public String getPlan3() {
		return plan3;
	}
	public void setPlan3(String plan3) {
		this.plan3 = plan3;
	}
	public String getPlan4() {
		return plan4;
	}
	public void setPlan4(String plan4) {
		this.plan4 = plan4;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsuranceliability() {
		return insuranceliability;
	}
	public void setInsuranceliability(String insuranceliability) {
		this.insuranceliability = insuranceliability;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPricerate() {
		return pricerate;
	}
	public void setPricerate(String pricerate) {
		this.pricerate = pricerate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
