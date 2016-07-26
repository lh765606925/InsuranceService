package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author laohu 受益人信息
 * 
 */
@Entity
@Table(name = "t_policyinsured")
public class Policyinsured implements java.io.Serializable {
	
	private static final long serialVersionUID = -9096588238505190161L;
	private String id;//受益人ID
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String policyId;// 保单ID
	private String createDate;//创建时间

	private String sort;// 
	private String isLegal;// 是否法定受益人
	private String holderRelation;// 与投保人关系
	private String name;// 姓名
	private String sex;// 性别
	private String certificateNo;// 证件号码
	private String certificateType;// 证件类型
	private String birthdate;// 生日 '1989-11-12'
	private String profession;// 职业
	private String email;// 邮箱
	private String moblie;// 手机号码
	private String telephone;// 电话号码
	private String nationality;// 国籍
	private String city;// 所在城市
	private String province;// 省
	private String detailAddress;// 详细地址
	private String zipNo;// 邮编
	private String county;// 份数
	private String isChiefInsured;// 是否主险
	private String chiefInsuredRelation;// 与主被保险人关系
	private String certValidFlag;// 证件有效期标志
	private String certEndDate;// 证件有效期
	private String certStartDate;// 证件起始日期
	private String residentCity;// 常驻城市
	private String residentProvince;// 常驻省份
	private String supplierBranch;// 供应商分公司
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsLegal() {
		return isLegal;
	}
	public void setIsLegal(String isLegal) {
		this.isLegal = isLegal;
	}
	public String getHolderRelation() {
		return holderRelation;
	}
	public void setHolderRelation(String holderRelation) {
		this.holderRelation = holderRelation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getZipNo() {
		return zipNo;
	}
	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getIsChiefInsured() {
		return isChiefInsured;
	}
	public void setIsChiefInsured(String isChiefInsured) {
		this.isChiefInsured = isChiefInsured;
	}
	public String getChiefInsuredRelation() {
		return chiefInsuredRelation;
	}
	public void setChiefInsuredRelation(String chiefInsuredRelation) {
		this.chiefInsuredRelation = chiefInsuredRelation;
	}
	public String getCertValidFlag() {
		return certValidFlag;
	}
	public void setCertValidFlag(String certValidFlag) {
		this.certValidFlag = certValidFlag;
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
	public String getSupplierBranch() {
		return supplierBranch;
	}
	public void setSupplierBranch(String supplierBranch) {
		this.supplierBranch = supplierBranch;
	}
	
}
