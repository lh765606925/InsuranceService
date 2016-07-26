package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 合作伙伴表
 * 
 * @author laohu
 * 
 */
@Entity
@Table(name = "t_partners")
public class Partners extends BaseEntity {
	private static final long serialVersionUID = -5310476027343095477L;

	private String name;// 公司名称
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String intefaceName;// 接口名称；
	public String getIntefaceName() {
		return intefaceName;
	}
	public void setIntefaceName(String intefaceName) {
		this.intefaceName = intefaceName;
	}

}
