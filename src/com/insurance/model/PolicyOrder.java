package com.insurance.model;

import javax.persistence.Entity;

@Entity
public class PolicyOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String orderId;//订单流水号，默认16位时间随机树
	private PolicyInfo policyInfo;//保单信息
	private PolicyHolder policyHolder;//投保人信息
	private Policyinsured policyinsured;//被保人信息
	private Product product;//保险产品信息
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public PolicyInfo getPolicyInfo() {
		return policyInfo;
	}
	public void setPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
	}
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}
	public Policyinsured getPolicyinsured() {
		return policyinsured;
	}
	public void setPolicyinsured(Policyinsured policyinsured) {
		this.policyinsured = policyinsured;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
