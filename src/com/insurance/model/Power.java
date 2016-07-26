package com.insurance.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 权限model
 */
@SuppressWarnings("all")
@Entity
@Table(name="t_power")
public class Power implements java.io.Serializable {

	// Fields

	private int pid;
	private String powerName;
    private int parentId;
    private String powerDesc;
    private String state;
    private String iconCls;
	// Constructors

	/** default constructor */
	public Power() {
	}

	@Id
	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * 权限名称
	 * @return
	 */
	@Column(length=20)
	public String getPowerName() {
		return powerName;
	}

	/**
	 * 权限名称
	 * @param powerName
	 */
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	
	/**
	 * 权限父节点id
	 * @return
	 */
    @Column(length=10)
	public int getParentId() {
		return parentId;
	}

    /**
     * 权限父节点id
     * @param parentId
     */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * 权限描述
	 * @return
	 */
    @Column(length=200)
	public String getPowerDesc() {
		return powerDesc;
	}

    /**
     * 权限描述
     * @param powerDesc
     */
	public void setPowerDesc(String powerDesc) {
		this.powerDesc = powerDesc;
	}
	
    /**
     * 状态
     * @return
     */
    @Column(length=20)
	public String getState() {
		return state;
	}

    /**
     * 状态
     * @param state
     */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 权限图标
	 * @return
	 */
	@Column(length=20)
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * 权限图标
	 * @param iconCls
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	

}