package com.insurance.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 3.3.2.4. 违法信息列表 代码：PeccList中包含多条PeccData。
 * 
 * @author huzhihong.com
 * 
 * @创建日期：2015年9月28日
 */

@Entity
@Table(name = "t_peccData")
public class PeccData extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String peccancyId;// 22 Y 违法编号（全国唯一标识）
	private String decisionId;// 10 Y 决定书编号
	private String decisionType;// 1 Y 决定书类型
	private Date peccancyTime;// Y 违法时间；格式：精确到天
	private Date manageTime;// Y 违法处理时间；格式精确到秒
	private String peccancyPlace;// 200 Y 违法地点
	private String peccancyCode;// 4 Y 违法行为 参见编码
	private String drivingLicence;// 18 违法驾驶员驾驶证号，如果无证驾驶，则为空。

	public String getPeccancyId() {
		return peccancyId;
	}

	public void setPeccancyId(String peccancyId) {
		this.peccancyId = peccancyId;
	}

	public String getDecisionId() {
		return decisionId;
	}

	public void setDecisionId(String decisionId) {
		this.decisionId = decisionId;
	}

	public String getDecisionType() {
		return decisionType;
	}

	public void setDecisionType(String decisionType) {
		this.decisionType = decisionType;
	}

	public Date getPeccancyTime() {
		return peccancyTime;
	}

	public void setPeccancyTime(Date peccancyTime) {
		this.peccancyTime = peccancyTime;
	}

	public Date getManageTime() {
		return manageTime;
	}

	public void setManageTime(Date manageTime) {
		this.manageTime = manageTime;
	}

	public String getPeccancyPlace() {
		return peccancyPlace;
	}

	public void setPeccancyPlace(String peccancyPlace) {
		this.peccancyPlace = peccancyPlace;
	}

	public String getPeccancyCode() {
		return peccancyCode;
	}

	public void setPeccancyCode(String peccancyCode) {
		this.peccancyCode = peccancyCode;
	}

	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
}
