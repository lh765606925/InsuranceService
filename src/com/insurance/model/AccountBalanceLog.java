package com.insurance.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 实体类-余额日志
 * 
 * @author Administrator
 * 
 */

@Entity
@Table(name = "t_accountbalancelog")
public class AccountBalanceLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// 余额日志类型（充值、购买、活动佣金、佣金）
	public enum AccountBalanceLogType {
		deposit, buys, activities, brokerage
	};

	private AccountBalanceLogType type;// 操作员
	private String operator;// 操作员
	private String info;// 日志信息
	private BigDecimal amount;// 佣金金额
	private int sid;// 用户ID
	private Salesman salesman;

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public AccountBalanceLogType getType() {
		return type;
	}

	public void setType(AccountBalanceLogType type) {
		this.type = type;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
}
