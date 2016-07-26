package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.util.MD5Utils;
import com.insurance.dao.BaseDao;
import com.insurance.model.Manager;
import com.insurance.model.PageBean;
import com.insurance.service.ManagerService;
@Service("managerService")
/**
 * 管理员Service
 * @author fly
 * 2014-11-17
 */
public class ManagerServiceImpl extends BaseServiceImpl<Manager, String>  implements ManagerService {

	@Resource
	private BaseDao<Manager,String> baseDao;
	
	public void deleteManager(Manager manager) {
		baseDao.delete(manager);

	}

	public List<Manager> findAllManager() {
		String hql = "from Manager";
		return baseDao.find(hql);
	}
	
	public Manager findManagerByNameAndPassword(Manager manager) {
		if(manager!=null){
			return baseDao.get("from Manager m where m.userName=? and m.passWord=?", new Object[]{manager.getUserName(),MD5Utils.getMD5(manager.getPassWord().getBytes())});
		}else{
			return null;
		}
	}

	public Manager findbyId(int id) throws Exception {
		return baseDao.get(Manager.class, id);
	}

	public void saveMananger(Manager manager) {
		manager.setPassWord(MD5Utils.getMD5(manager.getPassWord().getBytes()));
		baseDao.save(manager);
	}

	public void updateManager(Manager manager) {
		baseDao.update(manager);

	}

	@Override
	public List<Manager> findManager(String hql, Object[] param) {
		return baseDao.find(hql, param);
	}

	@Override
	public Long getManagerCount() {
		return baseDao.count("select count(*) from Manager");
	}

	@Override
	public List<Manager> findManager(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from Manager", pageBean, param);
		}
		return null;
	}

}
