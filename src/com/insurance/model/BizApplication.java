package com.insurance.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author huzhihong.com
 * 
 *         创建日期：2015年9月28日
 */
@Entity
@Table(name = "t_bizApplication")
public class BizApplication extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String lastPolicyNo;// 上年保单号 22
	private Date effectiveDate;// Y 起保日期；格式：精确到日
	private Date expireDate;// Y 终保日期；格式：精确到日
	private String planCode;// Y 险种

	public String getLastPolicyNo() {
		return lastPolicyNo;
	}

	public void setLastPolicyNo(String lastPolicyNo) {
		this.lastPolicyNo = lastPolicyNo;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
}
