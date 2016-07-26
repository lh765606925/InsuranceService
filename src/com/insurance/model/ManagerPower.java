package com.insurance.model;

/**
 * ManagerPower entity. @author MyEclipse Persistence Tools
 */

public class ManagerPower implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Power power;
	private Manager manager;

	// Constructors

	/** default constructor */
	public ManagerPower() {
	}

	/** full constructor */
	public ManagerPower(Power power, Manager manager) {
		this.power = power;
		this.manager = manager;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Power getPower() {
		return this.power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}