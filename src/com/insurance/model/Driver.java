package com.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_driver")
public class Driver extends BaseEntity {
	private static final long serialVersionUID = 1107808030710906880L;

	// 联系人性别（M 男 ，F 女）
	public enum SexCode {
		M, F
	}
	// 5.1.31.	驾驶证类型:大型客车,牵引车,城市公交车,中型客车,大型货车,小型汽车,小型自动档汽车
	//低速载货汽车,三轮汽车,残疾人专用小型自动档载客汽车,普通三轮摩托车,普通二轮摩托车,轻便摩托车,轮式自行机械车,无轨电车,有轨电车
	public enum DriveVehicleType {
		A1,A2,A3,B1,B2,C1,C2,C3,C4,C5,D,E,F,M,N,P
	}
	
	private String personnelName;// 字体 150 Y 人员姓名
	private SexCode sexCode;// 性别
	private Date birthday;// 字体 150 Y 人员姓名
	private String driveLicenseNo;// 字体 150 Y 人员姓名
	private Boolean isMajorDriver;// 1 主驾人，0非主驾人
	private Date firstIssueDate;// 发照日期，精确到天
	private DriveVehicleType driveVehicleType;// 驾驶证类型
	
	
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public SexCode getSexCode() {
		return sexCode;
	}
	public void setSexCode(SexCode sexCode) {
		this.sexCode = sexCode;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDriveLicenseNo() {
		return driveLicenseNo;
	}
	public void setDriveLicenseNo(String driveLicenseNo) {
		this.driveLicenseNo = driveLicenseNo;
	}
	public Boolean getIsMajorDriver() {
		return isMajorDriver;
	}
	public void setIsMajorDriver(Boolean isMajorDriver) {
		this.isMajorDriver = isMajorDriver;
	}
	public Date getFirstIssueDate() {
		return firstIssueDate;
	}
	public void setFirstIssueDate(Date firstIssueDate) {
		this.firstIssueDate = firstIssueDate;
	}
	public DriveVehicleType getDriveVehicleType() {
		return driveVehicleType;
	}
	public void setDriveVehicleType(DriveVehicleType driveVehicleType) {
		this.driveVehicleType = driveVehicleType;
	}

}
