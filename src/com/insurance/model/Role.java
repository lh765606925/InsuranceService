package com.insurance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色model
 * 
 * @author ASUS
 * 
 */
@SuppressWarnings("all")
@Entity
@Table(name="t_role")
public class Role implements java.io.Serializable {

	private int roleId;
	private String roleName;// 角色名称
	private String powerIds;// 权限id
	private String roleDesc;// 角色描述
	
	private List<Manager> managerList = new ArrayList<Manager>();

	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
    /**
     * 角色名称
     * @return
     */
	@Column(length=20)
	public String getRoleName() {
		return roleName;
	}
    /**
     * 角色名称
     * @param roleName
     */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    /**
     * 权限id
     * @return
     */
	@Column(length=60)	
	public String getPowerIds() {
		return powerIds;
	}
    /**
     * 权限id
     * @param powerIds
     */
	public void setPowerIds(String powerIds) {
		this.powerIds = powerIds;
	}
    /**
     * 角色描述
     * @return
     */
	@Column(length=200)
	public String getRoleDesc() {
		return roleDesc;
	}
    /**
     * 角色描述
     * @param roleDesc
     */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	@OneToMany(mappedBy="role",fetch=FetchType.EAGER)
	public List<Manager> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<Manager> managerList) {
		this.managerList = managerList;
	}

}
