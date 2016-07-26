package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 4.1.3. 组织信息
 * 
 * @author huzhihong
 * 
 *         创建日期：2015年6月11日
 */


@Entity
@Table(name = "t_group")
public class Group extends BaseEntity {
	private static final long serialVersionUID = 7053956131844039943L;

	public enum CertificateType {
		企业法人营业执照, 中华人名共和国组织机构代码证, 税务登记证, 社保保险登记证, 办学许可证, 其他
	}

	// 联系人性别（M 男 ，F 女）
	public enum LinkManSexCode {
		M, F
	}

	private String groupName;// 字符 500 Y 组织名称
	private String groupAbbr;// 字符 500 组织简称
	private CertificateType certificateType;// 组织客户证件类型
	private String certificateNo;// 字符 20 Y 证件号码
	private String businessRegisterId;// 字符 30 Y 工商注册号
	private String address;// 字符 100 Y 地址
	private String industryCode;// 字符 5 行业类型
	private String bankCode;// 字符 10 开户银行
	private String bankAccount;// 字符 30 银行账号
	private String linkManName;// 字符 30 Y 联系人
	private LinkManSexCode linkManSexCode;// 枚举 1 Y 联系人性别
	private String linkManMobileTelephone;// 字符 50 Y 手机
	private String linkManOfficeAreaCode;// 字符 10 办公电话区号
	private String linkManOfficeTelephone;// 字符 30 办公电话
	private String linkManDepartment;// 字符 60 所属部门
	private String linkManPosition;// 字符 20 职位
	private String postcode;// 字符 6 Y 邮编
	private String taxRegisterNo;// 字符 15 税务登记证号

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupAbbr() {
		return groupAbbr;
	}
	public void setGroupAbbr(String groupAbbr) {
		this.groupAbbr = groupAbbr;
	}
	public CertificateType getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(CertificateType certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getBusinessRegisterId() {
		return businessRegisterId;
	}
	public void setBusinessRegisterId(String businessRegisterId) {
		this.businessRegisterId = businessRegisterId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getLinkManName() {
		return linkManName;
	}
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	public LinkManSexCode getLinkManSexCode() {
		return linkManSexCode;
	}
	public void setLinkManSexCode(LinkManSexCode linkManSexCode) {
		this.linkManSexCode = linkManSexCode;
	}
	public String getLinkManMobileTelephone() {
		return linkManMobileTelephone;
	}
	public void setLinkManMobileTelephone(String linkManMobileTelephone) {
		this.linkManMobileTelephone = linkManMobileTelephone;
	}
	public String getLinkManOfficeAreaCode() {
		return linkManOfficeAreaCode;
	}
	public void setLinkManOfficeAreaCode(String linkManOfficeAreaCode) {
		this.linkManOfficeAreaCode = linkManOfficeAreaCode;
	}
	public String getLinkManOfficeTelephone() {
		return linkManOfficeTelephone;
	}
	public void setLinkManOfficeTelephone(String linkManOfficeTelephone) {
		this.linkManOfficeTelephone = linkManOfficeTelephone;
	}
	public String getLinkManDepartment() {
		return linkManDepartment;
	}
	public void setLinkManDepartment(String linkManDepartment) {
		this.linkManDepartment = linkManDepartment;
	}
	public String getLinkManPosition() {
		return linkManPosition;
	}
	public void setLinkManPosition(String linkManPosition) {
		this.linkManPosition = linkManPosition;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTaxRegisterNo() {
		return taxRegisterNo;
	}
	public void setTaxRegisterNo(String taxRegisterNo) {
		this.taxRegisterNo = taxRegisterNo;
	}

}
