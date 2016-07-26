package com.insurance.model;
/**
 * 客户model
 * @author cuiyadong
 * @time 2014-11-18
 */
public class ClientModel {
	
	private String userId;
	private String userName;//用户名（必选）
	private String userPass;//密码（必选）
	private String userRealName;//真实姓名
	private String userMail;//邮箱（必选）
	private String userPhone;//电话（必选）
	
	private String userGender;//性别（必选）
	private String userIdentity;//身份证号码(必选)
	private String userIdentityPicture;//身份证图片(可选)
	private String userHeadPortrait;//头像图片(可选)
	private String userAddress;//住址必选
	private String userRegistrationTime;//注册时间(客户端填写)
	
	private int userLevel;//级别
	private String userState;//审核(权限)状态
	
	
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserIdentity() {
		return userIdentity;
	}
	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}
	public String getUserIdentityPicture() {
		return userIdentityPicture;
	}
	public void setUserIdentityPicture(String userIdentityPicture) {
		this.userIdentityPicture = userIdentityPicture;
	}
	public String getUserHeadPortrait() {
		return userHeadPortrait;
	}
	public void setUserHeadPortrait(String userHeadPortrait) {
		this.userHeadPortrait = userHeadPortrait;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserRegistrationTime() {
		return userRegistrationTime;
	}
	public void setUserRegistrationTime(String userRegistrationTime) {
		this.userRegistrationTime = userRegistrationTime;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	
}
