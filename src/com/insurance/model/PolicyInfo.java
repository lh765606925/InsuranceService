package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_policyinfo")
public class PolicyInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * @author laohu
	 *         保单信息表
	 */
	@Id
	private String policyId;// 保单ID
	private Double amount;// 保额
	private String applyCode;// '9702004173'
	private String channelId;// 渠道ID
	private String startDate;// 保单开始履行时间
	private String endDate;// 保单有效截止时间
	private String salesman_id;// 短险平台ID
	private String productId;// 产品ID
	private String policyHolder_id;// 受益人ID
	private String policyInsured_id;// 投保人ID
	private String orderId;// 交易记录ID
	private String orderCode;// 交易编号
	private String orderCharge_id;// 交易编号
	private String isLegal;//
	private String insuredSelect;//
	private String insuredAndBeneficiaryList_id;// 交易编号
	private String flowCode;// 交易编号
	private String salesman_name;// 短险平台会员名字
	private String product_name;// 产品名字
	private String policyHolder_name;// 投保人名字
	private Integer applyMonth;
	private Integer applyDay;
	private String policyState;// 保单状态 0未付款 1已付款 2已注销
	private PolicyHolder policyHolder;//受益人
	private Policyinsured policyinsured;//投保人
	private Salesman salesman;//业务员

	@Transient
	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	@Transient
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}

	@Transient
	public Policyinsured getPolicyinsured() {
		return policyinsured;
	}

	public void setPolicyinsured(Policyinsured policyinsured) {
		this.policyinsured = policyinsured;
	}
	public String getPolicyInsured_id() {
		return policyInsured_id;
	}

	public void setPolicyInsured_id(String policyInsured_id) {
		this.policyInsured_id = policyInsured_id;
	}
	public String getPolicyState() {
		return policyState;
	}

	public void setPolicyState(String policyState) {
		this.policyState = policyState;
	}

	public String getSalesman_name() {
		return salesman_name;
	}

	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getPolicyHolder_name() {
		return policyHolder_name;
	}

	public void setPolicyHolder_name(String policyHolder_name) {
		this.policyHolder_name = policyHolder_name;
	}

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyHolder_id() {
		return policyHolder_id;
	}

	public void setPolicyHolder_id(String policyHolder_id) {
		this.policyHolder_id = policyHolder_id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCharge_id() {
		return orderCharge_id;
	}

	public void setOrderCharge_id(String orderCharge_id) {
		this.orderCharge_id = orderCharge_id;
	}

	public String getIsLegal() {
		return isLegal;
	}

	public void setIsLegal(String isLegal) {
		this.isLegal = isLegal;
	}

	public String getInsuredSelect() {
		return insuredSelect;
	}

	public void setInsuredSelect(String insuredSelect) {
		this.insuredSelect = insuredSelect;
	}

	public String getInsuredAndBeneficiaryList_id() {
		return insuredAndBeneficiaryList_id;
	}

	public void setInsuredAndBeneficiaryList_id(String insuredAndBeneficiaryList_id) {
		this.insuredAndBeneficiaryList_id = insuredAndBeneficiaryList_id;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

}
