package com.insurance.service;

import java.util.List;

import com.insurance.model.Manager;
import com.insurance.model.PageBean;

/**
 * 管理员Service
 * @author fly
 * 2014-11-17
 */
public interface ManagerService {
	
	public void saveMananger(Manager manager);
	
	public void updateManager(Manager manager);
	
	public Manager findbyId(int id) throws Exception;
	
	public void deleteManager(Manager manager);
	
	public List<Manager>findAllManager();
	
	public List<Manager>findManager(PageBean pageBean, Object[] param);
	
	public List<Manager>findManager(String hql,Object[]param);
	
	public Manager findManagerByNameAndPassword(Manager manager);
	
	public Long getManagerCount();

}
