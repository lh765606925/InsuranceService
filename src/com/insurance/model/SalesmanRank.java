package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务员积分等级表
 * 
 * @author huzhihong.com
 * 
 */
@Entity
@Table(name = "t_salesmanrank")
public class SalesmanRank extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isDefault;//是否默认
	private String name;//等级名称
	private double preferentialScale;//优惠率，买东西的折扣
	private double firstdiscount;//首次购买折扣
	private double otherdiscount;//其他折扣
	private double firstBrokerage;//出单佣金率
	private double secondBrokerage;//下线出单佣金率
	private double thridBrokerage;//下线的下线出单佣金率
	private String proxytype;//等级类型
	private int pointregion;//积分区间
	private int pointregion1;//积分区间
	
	public String getProxytype() {
		return proxytype;
	}

	public void setProxytype(String proxytype) {
		this.proxytype = proxytype;
	}

	public int getPointregion() {
		return pointregion;
	}

	public void setPointregion(int pointregion) {
		this.pointregion = pointregion;
	}

	public int getPointregion1() {
		return pointregion1;
	}

	public void setPointregion1(int pointregion1) {
		this.pointregion1 = pointregion1;
	}

	
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

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPreferentialScale() {
		return preferentialScale;
	}

	public void setPreferentialScale(double preferentialScale) {
		this.preferentialScale = preferentialScale;
	}

	public double getFirstdiscount() {
		return firstdiscount;
	}

	public void setFirstdiscount(double firstdiscount) {
		this.firstdiscount = firstdiscount;
	}

	public double getOtherdiscount() {
		return otherdiscount;
	}

	public void setOtherdiscount(double otherdiscount) {
		this.otherdiscount = otherdiscount;
	}

	public SalesmanRank() {
	}

}
