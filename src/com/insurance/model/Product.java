package com.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 产品表
 * 
 * @author laohu
 * 
 */
@Entity
@Table(name = "t_product")
public class Product extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;// 产品名称
	private Boolean isTop;// 是否置顶

	private String productImgList;// 产品缩略图
	private String productType_id;// 产品类型
	private String summary;// 产品简介
	private String insuranceType_id;// 险种ID，接口ID；
	private String insuranceNote;// 投保须知；
	private String insuranceDeclaration;// 投保声明书；
	private String insuranceRules;// 投保规则；
	private String claimNote;// 理赔须知；
	private String price;// 起始价格
	private String amount;// 保额
	private String productBrand;// 产品品牌
	private String productBrand_id;// 产品品牌
	private String startdate;// 生效日期-详情，显示中文
	private String datelimit;// 有效期
	private String interfaceName;// 接口名字

	private double firstBrokerage;// 出单佣金率
	private double secondBrokerage;// 下线出单佣金率
	private double thridBrokerage;// 下线的下线出单佣金率
	private String brokerage;// 佣金 ，以数组保存以百分百保存， 例：5;3;2;1;1;0.5

	public double getFirstBrokerage() {
		return firstBrokerage;
	}

	public void setFirstBrokerage(double firstBrokerage) {
		this.firstBrokerage = firstBrokerage;
	}

	public double getSecondBrokerage() {
		return secondBrokerage;
	}

	public void setSecondBrokerage(double secondBrokerage) {
		this.secondBrokerage = secondBrokerage;
	}

	public double getThridBrokerage() {
		return thridBrokerage;
	}

	public void setThridBrokerage(double thridBrokerage) {
		this.thridBrokerage = thridBrokerage;
	}

	public String getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(String brokerage) {
		this.brokerage = brokerage;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDatelimit() {
		return datelimit;
	}

	public void setDatelimit(String datelimit) {
		this.datelimit = datelimit;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	@Column(length = 200)
	public String getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(String productImgList) {
		this.productImgList = productImgList;
	}

	@Column(length = 200)
	public String getProductType_id() {
		return productType_id;
	}

	public void setProductType_id(String productType_id) {
		this.productType_id = productType_id;
	}

	@Column(length = 400)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(length = 200)
	public String getInsuranceType_id() {
		return insuranceType_id;
	}

	public void setInsuranceType_id(String insuranceType_id) {
		this.insuranceType_id = insuranceType_id;
	}

	@Column(length = 5000)
	public String getInsuranceNote() {
		return insuranceNote;
	}

	public void setInsuranceNote(String insuranceNote) {
		this.insuranceNote = insuranceNote;
	}

	@Column(length = 200)
	public String getInsuranceDeclaration() {
		return insuranceDeclaration;
	}

	public void setInsuranceDeclaration(String insuranceDeclaration) {
		this.insuranceDeclaration = insuranceDeclaration;
	}

	@Column(length = 200)
	public String getInsuranceRules() {
		return insuranceRules;
	}

	public void setInsuranceRules(String insuranceRules) {
		this.insuranceRules = insuranceRules;
	}

	@Column(length = 200)
	public String getClaimNote() {
		return claimNote;
	}

	public void setClaimNote(String claimNote) {
		this.claimNote = claimNote;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	@Column(length = 32)
	public String getProductBrand_id() {
		return productBrand_id;
	}

	public void setProductBrand_id(String productBrand_id) {
		this.productBrand_id = productBrand_id;
	}

}
