package com.insurance.model;

import javax.persistence.Column;

public class BasePart extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String tpApplyQueryNo;// 100 Y 投保查询流水号
	private String renewalFlag;// 1 Y 转保业务标志 参见编码
	private String checkCode;// 3000 校验码图片转换后的串
	private String question;// 1000 问题描述(问题,参考答案)

	@Column(length = 100, nullable = false)
	public String getTpApplyQueryNo() {
		return tpApplyQueryNo;
	}

	public void setTpApplyQueryNo(String tpApplyQueryNo) {
		this.tpApplyQueryNo = tpApplyQueryNo;
	}

	@Column(length = 1, nullable = false)
	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	@Column(length = 3000, nullable = true)
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Column(length = 1000, nullable = true)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
