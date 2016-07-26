package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.PartnersDao;
import com.insurance.model.PageBean;
import com.insurance.model.Partners;
import com.insurance.service.PartnersService;

/**
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月5日
 */

@Service("PartnersService")
public class PartnersServiceImpl extends BaseServiceImpl<Partners, String> implements PartnersService {
	@Resource
	private PartnersDao partnersDao;

	@Override
	public List<Partners> findPartnersListByPartnersType(String string) {
		return partnersDao.findPartnersListByPartnersType(string);
	}

	@Override
	public Partners getPartners(String partners_id) {
		return partnersDao.getPartners(partners_id);
	}

	@Override
	public List<Partners> findAllPartners() {
		return partnersDao.findAllPartners();
	}

	@Override
	public void insert(Partners partners) {
		partnersDao.insert(partners);
	}

	@Override
	public long getPartnersCount() {
		return partnersDao.getPartnersCount();
	}

	@Override
	public List<Partners> findPartners(PageBean pageBean, Object[] objects) {
		return partnersDao.findPartners(pageBean,objects);
	}

}
