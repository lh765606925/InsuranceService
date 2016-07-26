package com.insurance.service;

import com.insurance.model.SalesmanRank;

public interface SalesmanRankService extends BaseService<SalesmanRank, String> {
	/**
	 * 通过ID主键获取等级信息
	 * 
	 * @param id
	 * @return SalesmanRank
	 * @throws Exception
	 */
	public SalesmanRank findById(String id) throws Exception;
}
