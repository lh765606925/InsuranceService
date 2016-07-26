package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 意见反馈，建议
 * 
 * @author huzhihong
 * 
 *	创建日期：2015年6月6日
 */
@Entity
@Table(name = "t_suggest")
public class Suggest extends BaseEntity {
	private static final long serialVersionUID = -5144175804199051940L;

	private String name;// 姓名
	private String reply;// 回复
	private String replydate;// 回复日期
	private String phone;// 手机号码
	private String context;// 建议内容
	
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplydate() {
		return replydate;
	}
	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

}
