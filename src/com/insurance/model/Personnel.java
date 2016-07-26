package com.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 个人信息表
 * 
 * @author huzhihong
 * 
 *         创建日期：2015年6月11日
 */
@Entity
@Table(name = "t_personnel")
public class Personnel extends BaseEntity {
	private static final long serialVersionUID = 132883530803885148L;

	public enum PersonType {
		团体, 个人
	}

	public enum CertificateType {
		身份证, 军官证, 港澳台证, 护照, 异常身份证, 出生证, 工商登记证, 其他证件
	}

	// 性别 （M 男 ，F 女）
	public enum Gender {
		M, F
	}

	// 被保险人籍（保费计算用） （0 省内 ，1 省外）
	public enum OutOfProvPerson {
		省内, 省外
	}

	private PersonType personType;// Y 人员类型 参见编码
	private String PersonnelName;// 字符 150 Y 人员姓名
	private CertificateType certificateType;// 枚举 2 Y 证件类型 参见编码
	private String certificateNo;// 字符 20 Y 证件号码
	private String mobileTelephone;// 字符 50 Y 手机
	private String homeAreaCode;// 字符 10 家庭电话区号
	private String homeTelephone;// 字符 30 家庭电话
	private String officeAreaCode;// 字符 10 办公电话区号
	private String officeTelephone;// 字符 30 办公电话
	private String postcode;// 字符 6 Y 邮政编码
	private String email;// 字符 200 E-mail地址
	private Date driverLicenseExpirationDate;// 驾驶证有效止期，精确到天
	private Date DrivingExpirationDate;// 行驶证年审到期日期，精确到天
	private Date Address;// 字符 300 Y 地址
	private Gender Gender;// 枚举 1 Y 性别 参见编码
	private Date Birthday;// 枚举 1 Y 性别 参见编码
	private OutOfProvPerson outOfProvPerson;// 被保险人籍（保费计算用）

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public String getPersonnelName() {
		return PersonnelName;
	}

	public void setPersonnelName(String personnelName) {
		PersonnelName = personnelName;
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

	public String getMobileTelephone() {
		return mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	public String getHomeAreaCode() {
		return homeAreaCode;
	}

	public void setHomeAreaCode(String homeAreaCode) {
		this.homeAreaCode = homeAreaCode;
	}

	public String getHomeTelephone() {
		return homeTelephone;
	}

	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
	}

	public String getOfficeAreaCode() {
		return officeAreaCode;
	}

	public void setOfficeAreaCode(String officeAreaCode) {
		this.officeAreaCode = officeAreaCode;
	}

	public String getOfficeTelephone() {
		return officeTelephone;
	}

	public void setOfficeTelephone(String officeTelephone) {
		this.officeTelephone = officeTelephone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDriverLicenseExpirationDate() {
		return driverLicenseExpirationDate;
	}

	public void setDriverLicenseExpirationDate(Date driverLicenseExpirationDate) {
		this.driverLicenseExpirationDate = driverLicenseExpirationDate;
	}

	public Date getDrivingExpirationDate() {
		return DrivingExpirationDate;
	}

	public void setDrivingExpirationDate(Date drivingExpirationDate) {
		DrivingExpirationDate = drivingExpirationDate;
	}

	public Date getAddress() {
		return Address;
	}

	public void setAddress(Date address) {
		Address = address;
	}

	public Gender getGender() {
		return Gender;
	}

	public void setGender(Gender gender) {
		Gender = gender;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public OutOfProvPerson getOutOfProvPerson() {
		return outOfProvPerson;
	}

	public void setOutOfProvPerson(OutOfProvPerson outOfProvPerson) {
		this.outOfProvPerson = outOfProvPerson;
	}

}
