package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_ordercharge")
public class OrderCharge implements java.io.Serializable {

	/**
	 * 支付信息
	 */
	private static final long serialVersionUID = 1L;
	private String id;// ID
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String buyerAccountUsername;//付款账号（支付宝账号）
	private String payAmount;//支付金额
	private String payBusinessCode;//交易号
	private String payCode;//支付流水号
	private String payMethod;//支付方式
	private String payer;//支付方
	private String sellerAccountUsername;//收款人账号

	public String getBuyerAccountUsername() {
		return buyerAccountUsername;
	}
	public void setBuyerAccountUsername(String buyerAccountUsername) {
		this.buyerAccountUsername = buyerAccountUsername;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayBusinessCode() {
		return payBusinessCode;
	}
	public void setPayBusinessCode(String payBusinessCode) {
		this.payBusinessCode = payBusinessCode;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getSellerAccountUsername() {
		return sellerAccountUsername;
	}
	public void setSellerAccountUsername(String sellerAccountUsername) {
		this.sellerAccountUsername = sellerAccountUsername;
	}

}
