package com.insurance.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 佣金发放日志表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "t_brokeragelog")
public class BrokerageLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 佣金日志类型（订单佣金、系统佣金、活动佣金）
	public enum BrokerageLogType {
		orderbrokerage, systembrokerage, activitiesbrokerage
	};

	private BrokerageLogType brokerageLogType;// 订单日志类型
	private String orderSn;// 订单编号
	private String operator;// 操作员
	private String info;// 日志信息
	private String orderId;// 订单
	private String order_sname;//订单用户名
	private BigDecimal orderTotalPrice;//订单总金额
	private int sid;// 用户ID
	private BigDecimal amount;// 佣金金额
	private Salesman salesman;

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public String getOrder_sname() {
		return order_sname;
	}
	
	public void setOrder_sname(String order_sname) {
		this.order_sname = order_sname;
	}
	
	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}
	
	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public BrokerageLogType getBrokerageLogType() {
		return brokerageLogType;
	}

	public void setBrokerageLogType(BrokerageLogType brokerageLogType) {
		this.brokerageLogType = brokerageLogType;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
