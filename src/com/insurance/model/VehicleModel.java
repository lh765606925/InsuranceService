package com.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 车型信息
 * 
 * @author huzhihong
 * 
 *         创建日期：2015年6月11日
 */

@Entity
@Table(name = "t_vehiclemodel")
public class VehicleModel extends BaseEntity {
	// 车辆来历凭证种类:销售发票,法院调解书,法院裁定书,法院判决书,仲裁裁决书,相关文书（继承、赠予、协议抵债）,批准文件,调拨证明,修理发票
	public enum ImportFlag {
		XSFP, FYTJS, FYCDS, FYPJS, ZCCJS, XGWS, BZWJ, DBZM, XLFP
	}

	// 防盗系统
	public enum TheftProof {
		GPS, GMS, 电子, 机械, 无
	}

	private static final long serialVersionUID = 4882498912898031676L;
	private String autoModelCode;// 车型编码
	private String autoModelChnName;// 车型中文名称
	private Double purchasePrice;// 新车购置价
	private Double purchasePriceTax;// 新车购置价含税
	private String transmissionType;// 变速器类型
	private String vehicleBrand;// 车辆品牌
	private String vehSeries;// 车系名称
	private String maker;// 制造厂家
	private int vehicleSeats;// 核定载客数
	private Double kindredPrice;// 类比价
	private Double kindredPriceTax;//
	private String exhaustCapability;// 数值 8,4 排气量(单位：升)
	private String vehicleTonnages;// 数值 8,3 核定载质量(单位：吨)
	private String weight;// 数值 8,4 整备质量(单位：吨)
	private TheftProof theftProof;// 枚举 防盗装置
	private ImportFlag importFlag;// 枚举 1 能源类型 参见编码
	private int airbagCount;// 数值 4 安全气囊数
	private String firstSaleDate;// 字符 6 年款(200706)
	private Double power;// 数值 8,3 功率（千瓦）
	private Date vehicleCertificateDate;// 开具车辆来历凭证所载日期
	private String vehicleCertificateNo;// 车辆来历凭证编号
	private int vehicleCertificateType;// 车辆来历凭证种类
	private String remark;// 字符 200 备注
	private String vehicleType;// 车辆种类
	private String priceType;// 车价类型
	private String modelCode;// 20 行业车型编码
	private String modelIDCode;// 20 车型识别编码
	private String tradeName;// 30 厂商名称
	private String tradeCode;// 40 厂商编码
	private String brand;// 50 品牌名称
	private String brandCode;// 20 品牌编码
	private String series;// 30 车系名称
	private String seriesCode;// 20 车系编码
	private String carName;// 30 车款名称
	private String noticeType;// 20 公告型号
	private String configType;// 20 配置款型编码
	private String categoryName;// 20 类别名称
	private String categoryCode;// 20 类别编码
	private String deptName;// 30 系别名称
	private String deptCode;// 30 系别名称编码

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelIDCode() {
		return modelIDCode;
	}

	public void setModelIDCode(String modelIDCode) {
		this.modelIDCode = modelIDCode;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSeriesCode() {
		return seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public int getAirbagCount() {
		return airbagCount;
	}

	public void setAirbagCount(int airbagCount) {
		this.airbagCount = airbagCount;
	}

	public String getFirstSaleDate() {
		return firstSaleDate;
	}

	public void setFirstSaleDate(String firstSaleDate) {
		this.firstSaleDate = firstSaleDate;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public String getAutoModelCode() {
		return autoModelCode;
	}

	public void setAutoModelCode(String autoModelCode) {
		this.autoModelCode = autoModelCode;
	}

	public String getAutoModelChnName() {
		return autoModelChnName;
	}

	public void setAutoModelChnName(String autoModelChnName) {
		this.autoModelChnName = autoModelChnName;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getPurchasePriceTax() {
		return purchasePriceTax;
	}

	public void setPurchasePriceTax(Double purchasePriceTax) {
		this.purchasePriceTax = purchasePriceTax;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehSeries() {
		return vehSeries;
	}

	public void setVehSeries(String vehSeries) {
		this.vehSeries = vehSeries;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public int getVehicleSeats() {
		return vehicleSeats;
	}

	public void setVehicleSeats(int vehicleSeats) {
		this.vehicleSeats = vehicleSeats;
	}

	public Double getKindredPrice() {
		return kindredPrice;
	}

	public void setKindredPrice(Double kindredPrice) {
		this.kindredPrice = kindredPrice;
	}

	public Double getKindredPriceTax() {
		return kindredPriceTax;
	}

	public void setKindredPriceTax(Double kindredPriceTax) {
		this.kindredPriceTax = kindredPriceTax;
	}

	public String getExhaustCapability() {
		return exhaustCapability;
	}

	public void setExhaustCapability(String exhaustCapability) {
		this.exhaustCapability = exhaustCapability;
	}

	public String getVehicleTonnages() {
		return vehicleTonnages;
	}

	public void setVehicleTonnages(String vehicleTonnages) {
		this.vehicleTonnages = vehicleTonnages;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public TheftProof getTheftProof() {
		return theftProof;
	}

	public void setTheftProof(TheftProof theftProof) {
		this.theftProof = theftProof;
	}

	public ImportFlag getImportFlag() {
		return importFlag;
	}

	public void setImportFlag(ImportFlag importFlag) {
		this.importFlag = importFlag;
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

	public int getVehicleCertificateType() {
		return vehicleCertificateType;
	}

	public void setVehicleCertificateType(int vehicleCertificateType) {
		this.vehicleCertificateType = vehicleCertificateType;
	}
}
