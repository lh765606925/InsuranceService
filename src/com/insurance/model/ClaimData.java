package com.insurance.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.axis2.databinding.types.soapencoding.Decimal;

/**
 * 3.3.2.5. 上年度有责理赔列表 代码：ClaimList中包含多条ClaimData。
 * 
 * @author huzhihong.com
 * 
 *         创建日期：2015年9月28日
 */

@Entity
@Table(name = "t_claimData")
public class ClaimData extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String InsuranceCompany;// 8 保险公司
	private String RegisreationNo;// 50 立案号
	private String ClaimNumber;// 50 赔案号
	private Date AccidentTime;// 出险时间；格式：精确到分
	private Date FinishCaseTime;// Y 结案时间；格式：精确到分
	private boolean AccidentDeath;// 1 Y 是否发生有责任交通死亡事故；
	private Decimal ClaimAmount;// 赔款金额

	public String getInsuranceCompany() {
		return InsuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		InsuranceCompany = insuranceCompany;
	}

	public String getRegisreationNo() {
		return RegisreationNo;
	}

	public void setRegisreationNo(String regisreationNo) {
		RegisreationNo = regisreationNo;
	}

	public String getClaimNumber() {
		return ClaimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		ClaimNumber = claimNumber;
	}

	public Date getAccidentTime() {
		return AccidentTime;
	}

	public void setAccidentTime(Date accidentTime) {
		AccidentTime = accidentTime;
	}

	public Date getFinishCaseTime() {
		return FinishCaseTime;
	}

	public void setFinishCaseTime(Date finishCaseTime) {
		FinishCaseTime = finishCaseTime;
	}

	public boolean isAccidentDeath() {
		return AccidentDeath;
	}

	public void setAccidentDeath(boolean accidentDeath) {
		AccidentDeath = accidentDeath;
	}

	public Decimal getClaimAmount() {
		return ClaimAmount;
	}

	public void setClaimAmount(Decimal claimAmount) {
		ClaimAmount = claimAmount;
	}
}
