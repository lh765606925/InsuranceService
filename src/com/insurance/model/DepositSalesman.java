package com.insurance.model;

/**
 * ManagerPower entity. @author MyEclipse Persistence Tools
 */

public class DepositSalesman implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Deposit deposit;

	private Salesman salesman;

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	// Constructors

	/** default constructor */
	public DepositSalesman() {
	}

	/**
	 * 
	 */
	/** full constructor */
	public DepositSalesman(Deposit deposit, Salesman salesman) {
		this.deposit = deposit;
		this.salesman = salesman;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}