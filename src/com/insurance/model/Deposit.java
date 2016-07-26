package com.insurance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 充值记录表
 * 
 * @author laohu
 * 
 */
@Entity
@Table(name = "t_deposit")
public class Deposit implements java.io.Serializable {
	/**
	 * laohu 20150509
	 */
	private static final long serialVersionUID = 1L;

	private String id;// ID
	private String createDate;// 充值日期
	private Double credit;// 存入金额
	private Integer depositType;// 存款类型
	private Integer salesman_id;
	private Salesman salesman;//
	private String name;// 充值人姓名
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public Integer getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(Integer salesman_id) {
		this.salesman_id = salesman_id;
	}

	public Deposit() {
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String sid) {
		this.id = sid;
	}

	public String getCreateDate() {
		return createDate.substring(0, 19);
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public Integer getDepositType() {
		return depositType;
	}

	public void setDepositType(Integer depositType) {
		this.depositType = depositType;
	}
}
