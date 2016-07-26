package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.Role;
import com.insurance.service.RoleService;
@Service("roleSerivce")
public class RoleServiceImpl extends BaseServiceImpl<Deposit, String> implements RoleService {

	@Resource
	private BaseDao<Role,String> baseDao;
	@Override
	public List<Role> finRoleList(PageBean pageBean,Object[] param) {
		if(pageBean!=null){
			return baseDao.find("from Role", pageBean, new Object[]{});
		}
			return null;
	}

	@Override
	public Long getRoleCount() {
		return baseDao.count("select count(*) from Role");
	}

	@Override
	public Role getRoleById(int roleIds) throws Exception {
		return null;
	}

	@Override
	public int addrole(Role role) {
		baseDao.save(role);
		return 0;
	}

	@Override
	public void deleterole(Role role) {
		baseDao.delete(role);

	}

	@Override
	public void updaterole(Role role) {

	}

	@Override
	public Role findroleById(int id) {
		try {
			return baseDao.get(Role.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Role> finRoleList() {
      return baseDao.find("from Role");
	}

}
