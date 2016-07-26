package com.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 投保人-实体类
 * 
 * @author huzhihong.com
 */
@Entity
@Table(name = "t_policyholder")
public class PolicyHolder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 婚姻状况： 已婚，未婚，离婚，丧偶
	public enum MaritalStatus {
		Married, Unmarried, Divorce, Widowed
	}

	private String id;//ID
	private String createDate;//创建日期


	private String policyId;// 保单ID
	private String name;// 姓名
	private String sex;// 性别
	private String birthdate;// 生日 '1989-11-12'
	private int age;// 年龄周岁
	private String nationality;// 国籍
	private String certificateType;// 证件类型
	private String certificateNo;// 证件号码
	private String certEndDate;// 证件有效期
	private String certStartDate;// 证件起始日期
	private String certValidFlag;// 证书标识

	private MaritalStatus maritalStatus;// 婚姻状况
	private String profession;// 职业
	private String secoundProfession;// 兼职职业
	private String workContent;// 工作内容
	private String workAdress;//工作单位、学校名称
	private String occupationCode;//职业代码
	private String	occupationCategory;//职业类别
	
	private String county;// 所在国家
	private String province;// 省
	private String city;// 所在城市
	private String detailAddress;// 详细地址

	private String email;// 邮箱
	private String moblie;// 手机号码
	private String telephone;// 电话号码
	private String zipNo;// 邮编

	private String residentProvince;// 所在省份
	private String residentCity;// 居住城市
	private String supplierBranch;// 供应商分公司

	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSecoundProfession() {
		return secoundProfession;
	}

	public void setSecoundProfession(String secoundProfession) {
		this.secoundProfession = secoundProfession;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkAdress() {
		return workAdress;
	}

	public void setWorkAdress(String workAdress) {
		this.workAdress = workAdress;
	}

	public String getOccupationCode() {
		return occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	public String getOccupationCategory() {
		return occupationCategory;
	}

	public void setOccupationCategory(String occupationCategory) {
		this.occupationCategory = occupationCategory;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCertEndDate() {
		return certEndDate;
	}

	public void setCertEndDate(String certEndDate) {
		this.certEndDate = certEndDate;
	}

	public String getCertStartDate() {
		return certStartDate;
	}

	public void setCertStartDate(String certStartDate) {
		this.certStartDate = certStartDate;
	}

	public String getCertValidFlag() {
		return certValidFlag;
	}

	public void setCertValidFlag(String certValidFlag) {
		this.certValidFlag = certValidFlag;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getResidentCity() {
		return residentCity;
	}

	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}

	public String getResidentProvince() {
		return residentProvince;
	}

	public void setResidentProvince(String residentProvince) {
		this.residentProvince = residentProvince;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSupplierBranch() {
		return supplierBranch;
	}

	public void setSupplierBranch(String supplierBranch) {
		this.supplierBranch = supplierBranch;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getZipNo() {
		return zipNo;
	}

	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}

}
