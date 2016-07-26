package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_userfeedback")
public class UserFeedback extends BaseEntity {

	private static final long serialVersionUID = -1415466724472771031L;
	public String sid;// 用户ID
	public String real_name;// 真实姓名
	public String phone;// 手机号码
	public String content;// 反馈内容
	public String reply;// 回复

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}
