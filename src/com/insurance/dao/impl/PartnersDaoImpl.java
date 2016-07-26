package com.insurance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dao.PartnersDao;
import com.insurance.model.PageBean;
import com.insurance.model.Partners;

/**
 * 合作伙伴数据实现类
 * 
 * @author huzhihong
 * 
 *         创建日期：2015年6月5日
 */

@Repository("PartnersDao")
@Transactional
public class PartnersDaoImpl extends BaseDaoImpl<Partners, String> implements PartnersDao {

	@Override
	public List<Partners> findPartnersListByPartnersType(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partners getPartners(String Partners_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Partners> findAllPartners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Partners partners) {
		super.saveorUpdate(partners);

	}

	@Override
	public long getPartnersCount() {
		String hql = "select count(*) from Partners";
		return super.count(hql);
	}

	/**
	 * 分页查询合作伙伴表
	 */
	@Override
	public List<Partners> findPartners(PageBean pageBean, Object[] objects) {
		if (pageBean != null) {
			return super.find("from Partners order by createDate Desc", pageBean, objects);
		} else {
			return null;
		}
	}

}
