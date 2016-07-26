package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Role;
/**
 * 角色service
 * @author fly
 *
 */
public interface RoleService {
	public List<Role> finRoleList(PageBean pageBean,Object[] param);
	public List<Role> finRoleList();

	public Long getRoleCount();

	public Role getRoleById(int roleIds) throws Exception;

	public int addrole(Role role);

	public void deleterole(Role role);

	public void updaterole(Role role);
	
	public Role findroleById(int id);
	
}
