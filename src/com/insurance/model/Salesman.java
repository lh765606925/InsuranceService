package com.insurance.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author huzhihong.com
 * @email hh_laohu@163.com
 * 
 *        创建日期：2015年8月24日
 */
@Entity
@Table(name = "t_salesman")
public class Salesman implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int sid;
	private String name;// 昵称
	private String passWord;// 密码
	private String realName;// 真实姓名
	private String email;// 邮箱
	private String phone;// 手机号码
	private String sex;// 性别
	private String idNum;// 身份证号
	private String idPic1;// 身份正照片1
	private String idPic2;// 身份正照片2
	private String gangAo;// 港澳通行证号
	private String gangaoPic1;// 港澳通行证照片
	private String gangaoPic2;// 港澳通行证照片
	private String headPic;// 头像
	private String nowAddr;// 现住址
	private String workAddr;// 工作地址
	private String congYe;// 从业证号
	private String cyPic1;// 从业照片1
	private String cyPic2;// 从业照片2
	private String zhanYe;// 展业证号
	private String zyPic1;// 展业证照片1
	private String zyPic2;// 展业照片2
	private String bank; // 银行名称
	private String bankUser;// 银行开户名
	private String bankNum;// 银行帐号
	private int level;// 等级
	private String status;// 状态
	private Integer workYear;// 工作年限
	private String qrcode;// 二维码
	private int invateNum;// 邀请人数
	private Timestamp regtime;// 注册时间
	private Double money;// 账户余额
	private String record;// 付款记录
	private String salesmanRankId;//用户积分等级ID

	private String thirdPartyUniqueID;// 第三方唯一ID
	private String invate;// 邀请人
	private String invate2;// 邀请人的邀请人
	private String invate3;// 邀请人的邀请人的邀请人
	private String invate4;// 邀请人的邀请人的邀请人
	private String invate5;// 邀请人的邀请人的邀请人
	private String invate6;// 邀请人的邀请人的邀请人

	public String getSalesmanRankId() {
		return salesmanRankId;
	}

	
	public void setSalesmanRankId(String salesmanRankId) {
		this.salesmanRankId = salesmanRankId;
	}
	public String getThirdPartyUniqueID() {
		return thirdPartyUniqueID;
	}

	public void setThirdPartyUniqueID(String thirdPartyUniqueID) {
		this.thirdPartyUniqueID = thirdPartyUniqueID;
	}

	@Column(length = 20)
	public String getInvate2() {
		return invate2;
	}

	public void setInvate2(String invate2) {
		this.invate2 = invate2;
	}

	@Column(length = 20)
	public String getInvate3() {
		return invate3;
	}

	public void setInvate3(String invate3) {
		this.invate3 = invate3;
	}

	@Column(length = 20)
	public String getInvate4() {
		return invate4;
	}

	public void setInvate4(String invate4) {
		this.invate4 = invate4;
	}

	@Column(length = 20)
	public String getInvate5() {
		return invate5;
	}

	public void setInvate5(String invate5) {
		this.invate5 = invate5;
	}

	@Column(length = 20)
	public String getInvate6() {
		return invate6;
	}

	public void setInvate6(String invate6) {
		this.invate6 = invate6;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column(length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Column(length = 20)
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double moneypass) {
		this.money = moneypass;
	}

	@Column(length = 20)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(length = 20)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 身份证号
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getIdNum() {
		return idNum;
	}

	/**
	 * 身份证号
	 * 
	 * @param idnum
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * 身份证照片1
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getIdPic1() {
		return idPic1;
	}

	/**
	 * 身份证照片1
	 * 
	 * @param idpic1
	 */
	public void setIdPic1(String idPic1) {
		this.idPic1 = idPic1;
	}

	/**
	 * 身份证照片2
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getIdPic2() {
		return idPic2;
	}

	/**
	 * 身份证照片2
	 * 
	 * @param idpic2
	 */
	public void setIdPic2(String idPic2) {
		this.idPic2 = idPic2;
	}

	/**
	 * 港澳通行证号
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getGangAo() {
		return gangAo;
	}

	/**
	 * 港澳通行证号
	 * 
	 * @param gangAo
	 */
	public void setGangAo(String gangAo) {
		this.gangAo = gangAo;
	}

	/**
	 * 港澳通行证照片1
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getGangaoPic1() {
		return gangaoPic1;
	}

	/**
	 * 港澳通行证照片1
	 * 
	 * @param gangaoPic1
	 */
	public void setGangaoPic1(String gangaoPic1) {
		this.gangaoPic1 = gangaoPic1;
	}

	/**
	 * 港澳通行证照片2
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getGangaoPic2() {
		return gangaoPic2;
	}

	/**
	 * 港澳通行证照片2
	 * 
	 * @param gangaoPic2
	 */
	public void setGangaoPic2(String gangaoPic2) {
		this.gangaoPic2 = gangaoPic2;
	}

	/**
	 * 头像
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getHeadPic() {
		return headPic;
	}

	/**
	 * 头像
	 * 
	 * @param headpic
	 */
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	/**
	 * 现住址
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getNowAddr() {
		return nowAddr;
	}

	/**
	 * 现住址
	 * 
	 * @param nowaddr
	 */
	public void setNowAddr(String nowAddr) {
		this.nowAddr = nowAddr;
	}

	/**
	 * 工作地址
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getWorkAddr() {
		return workAddr;
	}

	/**
	 * 工作地址
	 * 
	 * @param workaddr
	 */
	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}

	/**
	 * 从业证号
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getCongYe() {
		return congYe;
	}

	/**
	 * 从业证号
	 * 
	 * @param congye
	 */
	public void setCongYe(String congYe) {
		this.congYe = congYe;
	}

	/**
	 * 从业证照片1
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getCyPic1() {
		return cyPic1;
	}

	/**
	 * 从业证照片1
	 * 
	 * @param congyepic1
	 */
	public void setCyPic1(String cyPic1) {
		this.cyPic1 = cyPic1;
	}

	/**
	 * 从业证照片2
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getCyPic2() {
		return cyPic2;
	}

	/**
	 * 从业证照片2
	 * 
	 * @param congyepic2
	 */
	public void setCyPic2(String cyPic2) {
		this.cyPic2 = cyPic2;
	}

	/**
	 * 展业证号
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getZhanYe() {
		return zhanYe;
	}

	/**
	 * 展业证号
	 * 
	 * @param zhanye
	 */
	public void setZhanYe(String zhanYe) {
		this.zhanYe = zhanYe;
	}

	/**
	 * 展业证照片1
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getZyPic1() {
		return zyPic1;
	}

	/**
	 * 展业证照片1
	 * 
	 * @param zhanyepic1
	 */
	public void setZyPic1(String zyPic1) {
		this.zyPic1 = zyPic1;
	}

	/**
	 * 展业证照片2
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getZyPic2() {
		return zyPic2;
	}

	/**
	 * 展业证照片2
	 * 
	 * @param zhanyepic2
	 */
	public void setZyPic2(String zyPic2) {
		this.zyPic2 = zyPic2;
	}

	/**
	 * 银行名称
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getBank() {
		return this.bank;
	}

	/**
	 * 银行名称
	 * 
	 * @param bank
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 银行开户名
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getBankUser() {
		return bankUser;
	}

	/**
	 * 银行开户名
	 * 
	 * @param bankuser
	 */
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	/**
	 * 银行帐号
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getBankNum() {
		return bankNum;
	}

	/**
	 * 银行帐号
	 * 
	 * @param banknum
	 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	/**
	 * 等级
	 * 
	 * @return
	 */
	@Column(length = 20)
	public int getLevel() {
		return this.level;
	}

	/**
	 * 等级
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * 状态
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 工作年限
	 * 
	 * @return
	 */
	@Column(length = 20)
	public Integer getWorkYear() {
		return workYear;
	}

	/**
	 * 工作年限
	 * 
	 * @param junioryear
	 */
	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}

	/**
	 * 二维码
	 * 
	 * @return
	 */
	@Column(length = 200)
	public String getQrcode() {
		return this.qrcode;
	}

	/**
	 * 二维码
	 * 
	 * @param qrcode
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	/**
	 * 注册时间
	 * 
	 * @return
	 */
	@Column
	public Timestamp getRegtime() {
		return regtime;
	}

	/**
	 * 注册时间
	 * 
	 * @param regtime
	 */
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

	/**
	 * 邀请人
	 * 
	 * @return
	 */
	@Column(length = 20)
	public String getInvate() {
		return invate;
	}

	/**
	 * 邀请人
	 * 
	 * @param invate
	 */
	public void setInvate(String invate) {
		this.invate = invate;
	}

	/**
	 * 邀请人数
	 * 
	 * @return
	 */
	@Column(length = 20)
	public int getInvateNum() {
		return invateNum;
	}

	/**
	 * 邀请人数
	 * 
	 * @param invatenum
	 */
	public void setInvateNum(int invateNum) {
		this.invateNum = invateNum;
	}

	@Override
	public String toString() {
		return "Salesman [sid=" + sid + ", name=" + name + ", passWord=" + passWord + ", realName=" + realName
				+ ", email=" + email + ", phone=" + phone + ", sex=" + sex + ", idNum=" + idNum + ", idPic1=" + idPic1
				+ ", idPic2=" + idPic2 + ", gangAo=" + gangAo + ", gangaoPic1=" + gangaoPic1 + ", gangaoPic2="
				+ gangaoPic2 + ", headPic=" + headPic + ", nowAddr=" + nowAddr + ", workAddr=" + workAddr + ", congYe="
				+ congYe + ", cyPic1=" + cyPic1 + ", cyPic2=" + cyPic2 + ", zhanYe=" + zhanYe + ", zyPic1=" + zyPic1
				+ ", zyPic2=" + zyPic2 + ", bank=" + bank + ", bankUser=" + bankUser + ", bankNum=" + bankNum
				+ ", level=" + level + ", status=" + status + ", workYear=" + workYear + ", qrcode=" + qrcode
				+ ", invate=" + invate + ", invateNum=" + invateNum + ", regtime=" + regtime + "，money=" + money
				+ ",record=" + record + "]";
	}

}