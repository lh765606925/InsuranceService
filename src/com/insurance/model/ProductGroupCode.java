package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类-产品组合代码 ProductGroupCode
 * 
 * @author huzhihong
 * 
 *         创建日期：2015年8月15日
 */
@Entity
@Table(name = "t_productgroupcode")
public class ProductGroupCode extends BaseEntity {

	private static final long serialVersionUID = 1L;
	// 计划类型(基础计划，全面计划)
	public enum PlanTypes {
		base, overall
	};
	// 计划区域（亚洲(除日本)，欧洲，全球）
	public enum PlanAreas {
		a, b, c
	}

	// 计划类型(一次出行，一年多次出行)
	public enum PlanNum {
		once, oneyear
	}
	private PlanAreas planAreas;
	private PlanNum planNum;

	private PlanTypes planTypes;
	private String code;


	public PlanTypes getPlanTypes() {
		return planTypes;
	}
	public void setPlanTypes(PlanTypes planTypes) {
		this.planTypes = planTypes;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public PlanAreas getPlanAreas() {
		return planAreas;
	}
	public void setPlanAreas(PlanAreas planAreas) {
		this.planAreas = planAreas;
	}
	public PlanNum getPlanNum() {
		return planNum;
	}
	public void setPlanNum(PlanNum planNum) {
		this.planNum = planNum;
	}
}
