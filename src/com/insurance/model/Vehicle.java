package com.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_vehicle")
public class Vehicle extends BaseEntity {

	private static final long serialVersionUID = 3241831384037850707L;

	public enum EcdemicVehicleFlag {
		否, 是, 港澳车
	}

	public enum VehicleType {
		客车非营业, 货车, 挂车, 特一挂车
	}

	public enum VehicleStyle {
		H15, H16, H17
	}

	// 能源类型
	public enum FuelType {
		燃油, 纯电动, 燃料电池, 插电式混合动力, 其他混合动力
	}

	public enum SpecialCarFlag {
		车辆所有权转移重新投保, 临时入境的境外机动车, 临时上道路行驶的机动车, 距报废期限不足一年的机动车, 其它保监会规定允许投保短期单的车
	}

	// 车辆来历凭证种类:销售发票,法院调解书,法院裁定书,法院判决书,仲裁裁决书,相关文书（继承、赠予、协议抵债）,批准文件,调拨证明,修理发票
	public enum VehicleCertificateType {
		XSFP, FYTJS, FYCDS, FYPJS, ZCCJS, XGWS, BZWJ, DBZM, XLFP
	}

	// 1 非营业
	// 2 营业
	public enum UsageAttributeCode {
		a, 营业
	}

	private String vehicleName;// 车型名称，适用模糊查询
	private String vehicleFrameNo;// 车架号，不适用模糊查询
	private String autoModelCode;// 车型编码
	private String usageAttributeCode;// 使用性质
	private String vehicleLicenceCode;// 号牌号码
	private String licenseTypeCode;// 号牌种类
	private String engineNo;// 发动机号
	private String owner;// 张三
	private String firstRegisterDate;// 20110202
	private String ownershipAttributeCode;// 所属性质
	private VehicleType vehicleType;// 车辆种类
	private VehicleStyle vehicleStyle;// 交管车辆种类
	private String vehicleBrand;// 交管车辆种类
	private SpecialCarFlag specialCarFlag;// 交管车辆种类
	private String transferDate;// 交管车辆种类
	private EcdemicVehicleFlag ecdemicVehicleFlag;// 外地车标志 参见编码
	private Boolean loanVehicleFlag;// 是否车贷投保多年标志；0：不是车贷投保多年；1：车贷投保多年
	private Boolean isMotorcade;// 车队标志；0：非车队车；1：车队车
	private String fleetNo;// 字符 50 车队号
	private int vehicleSeats;// 核定载客数
	private Double exhaustCapability;// 排气量(单位：升)
	private Double weight;// 整备质量(单位：吨)
	private Double vehicleTonnages;// 核定载质量(单位：吨)
	private Double purchasePrice;// 新车购置价
	private Double power;// 新车购置价
	private FuelType fuelType;// 能源类型
	private Date vehicleCertificateDate;// 开具车辆来历凭证所载日期
	private String vehicleCertificateNo;// 车辆来历凭证编号
	private VehicleCertificateType vehicleCertificateType;// 车辆来历凭证种类
	private String autoModelChnName;//车型中文名字
	private String appointAreaCode;//指定查询地区代码
	private Date runCardCertificateDate ;//行驶证发证日期 格式：精确到天

	public String getVehicleLicenceCode() {
		return vehicleLicenceCode;
	}

	public void setVehicleLicenceCode(String vehicleLicenceCode) {
		this.vehicleLicenceCode = vehicleLicenceCode;
	}

	public String getLicenseTypeCode() {
		return licenseTypeCode;
	}

	public void setLicenseTypeCode(String licenseTypeCode) {
		this.licenseTypeCode = licenseTypeCode;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getFirstRegisterDate() {
		return firstRegisterDate;
	}

	public void setFirstRegisterDate(String firstRegisterDate) {
		this.firstRegisterDate = firstRegisterDate;
	}

	public String getOwnershipAttributeCode() {
		return ownershipAttributeCode;
	}

	public void setOwnershipAttributeCode(String ownershipAttributeCode) {
		this.ownershipAttributeCode = ownershipAttributeCode;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleStyle getVehicleStyle() {
		return vehicleStyle;
	}

	public void setVehicleStyle(VehicleStyle vehicleStyle) {
		this.vehicleStyle = vehicleStyle;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public SpecialCarFlag getSpecialCarFlag() {
		return specialCarFlag;
	}

	public void setSpecialCarFlag(SpecialCarFlag specialCarFlag) {
		this.specialCarFlag = specialCarFlag;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public EcdemicVehicleFlag getEcdemicVehicleFlag() {
		return ecdemicVehicleFlag;
	}

	public void setEcdemicVehicleFlag(EcdemicVehicleFlag ecdemicVehicleFlag) {
		this.ecdemicVehicleFlag = ecdemicVehicleFlag;
	}

	public Boolean getLoanVehicleFlag() {
		return loanVehicleFlag;
	}

	public void setLoanVehicleFlag(Boolean loanVehicleFlag) {
		this.loanVehicleFlag = loanVehicleFlag;
	}

	public Boolean getIsMotorcade() {
		return isMotorcade;
	}

	public void setIsMotorcade(Boolean isMotorcade) {
		this.isMotorcade = isMotorcade;
	}

	public String getFleetNo() {
		return fleetNo;
	}

	public void setFleetNo(String fleetNo) {
		this.fleetNo = fleetNo;
	}

	public int getVehicleSeats() {
		return vehicleSeats;
	}

	public void setVehicleSeats(int vehicleSeats) {
		this.vehicleSeats = vehicleSeats;
	}

	public Double getExhaustCapability() {
		return exhaustCapability;
	}

	public void setExhaustCapability(Double exhaustCapability) {
		this.exhaustCapability = exhaustCapability;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getVehicleTonnages() {
		return vehicleTonnages;
	}

	public void setVehicleTonnages(Double vehicleTonnages) {
		this.vehicleTonnages = vehicleTonnages;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Date getVehicleCertificateDate() {
		return vehicleCertificateDate;
	}

	public void setVehicleCertificateDate(Date vehicleCertificateDate) {
		this.vehicleCertificateDate = vehicleCertificateDate;
	}

	public String getVehicleCertificateNo() {
		return vehicleCertificateNo;
	}

	public void setVehicleCertificateNo(String vehicleCertificateNo) {
		this.vehicleCertificateNo = vehicleCertificateNo;
	}

	public VehicleCertificateType getVehicleCertificateType() {
		return vehicleCertificateType;
	}

	public void setVehicleCertificateType(VehicleCertificateType vehicleCertificateType) {
		this.vehicleCertificateType = vehicleCertificateType;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleFrameNo() {
		return vehicleFrameNo;
	}

	public void setVehicleFrameNo(String vehicleFrameNo) {
		this.vehicleFrameNo = vehicleFrameNo;
	}

	public String getAutoModelCode() {
		return autoModelCode;
	}

	public void setAutoModelCode(String autoModelCode) {
		this.autoModelCode = autoModelCode;
	}

	public String getUsageAttributeCode() {
		return usageAttributeCode;
	}

	public void setUsageAttributeCode(String usageAttributeCode) {
		this.usageAttributeCode = usageAttributeCode;
	}

	public String getAutoModelChnName() {
		return autoModelChnName;
	}

	public void setAutoModelChnName(String autoModelChnName) {
		this.autoModelChnName = autoModelChnName;
	}
}
