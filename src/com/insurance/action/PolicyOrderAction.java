package com.insurance.action;

import javax.annotation.Resource;

import com.insurance.model.PolicyHolder;
import com.insurance.model.PolicyInfo;
import com.insurance.model.Policyinsured;
import com.insurance.model.Product;
import com.insurance.service.PolicyholderService;
import com.insurance.service.PolicyinsuredService;

public class PolicyOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private PolicyinsuredService policyInsuredService;
	
	@Resource
	private PolicyholderService policyholderService;
	
	private PolicyInfo policyInfo;//保单信息
	private PolicyHolder policyHolder;//投保人信息
	private Policyinsured policyinsured;//被保人信息
	private Product product;//保险产品信息
	
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
