package com.insurance.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.dao.SalesmanRankDao;
import com.insurance.model.SalesmanRank;
import com.insurance.service.SalesmanRankService;

@Service("SalesmanRankService")
public class SalesmanRankServiceImpl extends BaseServiceImpl<SalesmanRank, String> implements SalesmanRankService {
	@Resource
	private BaseDao<SalesmanRank, String> baseDao;

	@Resource
	private SalesmanRankDao salesmanRankDao;

	
	
	@Override
	public SalesmanRank findById(String id) throws Exception {
		return (SalesmanRank) baseDao.get(SalesmanRank.class, id);
	}

	
}
