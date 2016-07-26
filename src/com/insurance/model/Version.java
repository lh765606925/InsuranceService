package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_version")
public class Version implements java.io.Serializable {
	/**
	 * laohu 20150509
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	private String id;// ID
	private String releasedate;// 发布日期
	private String versioninfo;// 版本信息
	private String versioncode; // 版本号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReleasedate() {
		return releasedate.substring(0,19);
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	public String getVersioninfo() {
		return versioninfo;
	}

	public void setVersioninfo(String versioninfo) {
		this.versioninfo = versioninfo;
	}

	public String getVersioncode() {
		return versioncode;
	}

	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}

}
