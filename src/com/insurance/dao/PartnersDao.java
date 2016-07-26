package com.insurance.dao;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Partners;

/**
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月5日
 */

public interface PartnersDao  extends BaseDao<Partners, String> {

	public List<Partners> findPartnersListByPartnersType(String string);

	public Partners getPartners(String Partners_id);
	
	public List<Partners> findAllPartners();
	
	public void insert(Partners partners);
	
	public void update(Partners partners);
	
	public void delete(String ids);

	public long getPartnersCount();

	public List<Partners> findPartners(PageBean pageBean, Object[] objects);
	
	
}
