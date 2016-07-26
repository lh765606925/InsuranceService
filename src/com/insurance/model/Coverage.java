package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.axis2.databinding.types.soapencoding.Decimal;

@Entity
@Table(name = "t_coverage")
public class Coverage extends BaseEntity {
	private static final long serialVersionUID = 6154761017490522531L;

	// 5.1.12. 车身划痕损失免赔率（195）20 20%免赔 四川地区仅有此选项;0 无免赔
	public enum DeductionRate {
		MP20("20%免赔", 20), NMP("不免赔", 0);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private DeductionRate(String name, int index) {
			this.name = name;
			this.index = index;
		}

	}

	public enum CoverageCode {

		CBD("车碰车车辆损失险", "商业险适用，投保查询与保费计算时无需填写保额，系统会按车价自动匹配。"), OD("车辆损失综合险", ""), CDA("车辆损失综合险", "a"), TP("车辆损失综合险",
				"b"), THEFT("车辆损失综合险", "c"), DL("车辆损失综合险", "d");
		// 成员变量
		private String name;// 名称
		private String explanation;// 说明

		// 构造方法
		private CoverageCode(String name, String explanation) {
			this.name = name;
			this.explanation = explanation;
		}

	}

	private CoverageCode coverageCode;// 枚举 10 Y 险别责任代码 参见编码
	private Double allAmount;// 货币 全损保额
	private String subsidiary;// 字符 3 附加信息
	private DeductionRate deductionRate;// 枚举 2 车身划痕损失免赔率 参见编码
	private Decimal ariExpense;// 指定专修厂特约条款费率

	public CoverageCode getCoverageCode() {
		return coverageCode;
	}

	public void setCoverageCode(CoverageCode coverageCode) {
		this.coverageCode = coverageCode;
	}

	public Double getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public DeductionRate getDeductionRate() {
		return deductionRate;
	}

	public void setDeductionRate(DeductionRate deductionRate) {
		this.deductionRate = deductionRate;
	}

	public Decimal getAriExpense() {
		return ariExpense;
	}

	public void setAriExpense(Decimal ariExpense) {
		this.ariExpense = ariExpense;
	}
}
