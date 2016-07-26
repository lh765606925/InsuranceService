package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.axis2.databinding.types.soapencoding.Decimal;

/**
 * @ 3.3.2.6. 车辆风险系数
 * 
 * @author huzhihong.com
 * @说明：返回承保相关浮动系数。
 * @创建日期：2015年9月28日
 */

@Entity
@Table(name = "t_riskItem")
public class RiskItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * @name 5.1.39. 违法浮动原因（187）
	 * @V1 上一个年度没有道路交通安全违法行为
	 * @V2 上两个年度没有道路交通安全违法行为
	 * @V3 上三个及以上年度没有道路交通安全违法行为
	 * @V4 上一个年度发生各类道路交通违法行为（除V5-V7）低于五次的
	 * @V5 上一个年度每次违反道路交通信号灯通行的；逆向行驶的（最高不超过30％）
	 * @V6 上一个年度发生驾驶与准驾车型不符的机动车的；发生机动车驾驶证被暂扣期间驾驶机动车的
	 * @V7 上一个年度发生饮酒（含醉酒）后驾驶机动车的；
	 * @V8 上一个年度发生各类道路交通违法行为五次（含）以上的
	 */
	public enum PeccancyAdjustReason {
		V1, V2, V3, V4, V5, V6, V7, V8
	}

	/**
	 * @5.1.40. 理赔浮动原因（188）
	 * @代码 名称 说明
	 * @A1 上一个年度未发生有责任道路交通事故，浮动
	 * @A2 上两个年度未发生有责任道路交通事故，浮动
	 * @A3 上三个及以上年度未发生有责任道路交通事故，浮动
	 * @A4 上一个年度发生一次有责任不涉及死亡的道路交通事故，浮动
	 * @A5 上一个年度发生两次及或两次以上有责任道路交通事故，浮动
	 * @A6 上一个年度发生有责任道路交通死亡事故，浮动
	 */
	public enum ClaimAdjustReason {
		A1, A2, A3, A4, A5, A6
	}

	private Decimal peccancyAdjustRatio;// 数值 3,2 Y 违法调整系数
	private Decimal claimAdjustRatio;// 数值 3,2 Y 理赔调整系数
	private PeccancyAdjustReason peccancyAdjustReason;// 2 违法浮动原因 参见编码
	private ClaimAdjustReason claimAdjustReason;// 2 理赔浮动原因 参见编码

	public Decimal getPeccancyAdjustRatio() {
		return peccancyAdjustRatio;
	}

	public void setPeccancyAdjustRatio(Decimal peccancyAdjustRatio) {
		this.peccancyAdjustRatio = peccancyAdjustRatio;
	}

	public Decimal getClaimAdjustRatio() {
		return claimAdjustRatio;
	}

	public void setClaimAdjustRatio(Decimal claimAdjustRatio) {
		this.claimAdjustRatio = claimAdjustRatio;
	}

	public PeccancyAdjustReason getPeccancyAdjustReason() {
		return peccancyAdjustReason;
	}

	public void setPeccancyAdjustReason(PeccancyAdjustReason peccancyAdjustReason) {
		this.peccancyAdjustReason = peccancyAdjustReason;
	}

	public ClaimAdjustReason getClaimAdjustReason() {
		return claimAdjustReason;
	}

	public void setClaimAdjustReason(ClaimAdjustReason claimAdjustReason) {
		this.claimAdjustReason = claimAdjustReason;
	}

}
