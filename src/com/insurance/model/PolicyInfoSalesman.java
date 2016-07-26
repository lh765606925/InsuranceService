package com.insurance.model;

public class PolicyInfoSalesman  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private PolicyInfo policyInfo;//保单信息
	private Salesman salesman;//会员信息
	
	/** default constructor */
	public PolicyInfoSalesman() {
	}

	/** full constructor */
	public PolicyInfoSalesman(PolicyInfo policyInfo, Salesman salesman) {
		this.policyInfo = policyInfo;
		this.salesman = salesman;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PolicyInfo getPolicyInfo() {
		return policyInfo;
	}

	public void setPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

}
