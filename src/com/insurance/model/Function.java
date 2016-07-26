package com.insurance.model;

/**
 * Function entity. @author MyEclipse Persistence Tools
 */

public class Function implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer funId;
	private Power power;
	private String funName;

	// Constructors

	/** default constructor */
	public Function() {
	}

	/** full constructor */
	public Function(Integer funId, Power power, String funName) {
		this.funId = funId;
		this.power = power;
		this.funName = funName;
	}

	// Property accessors

	public Integer getFunId() {
		return this.funId;
	}

	public void setFunId(Integer funId) {
		this.funId = funId;
	}

	public Power getPower() {
		return this.power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getFunName() {
		return this.funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

}