package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.dao.BrokerageLogDao;
import com.insurance.model.AccountBalanceLog;
import com.insurance.model.BrokerageLog;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.service.BrokerageLogService;
import com.insurance.service.SalesmanService;

@Service("BrokerageLogService")
public class BrokerageLogServiceImpl extends BaseServiceImpl<BrokerageLog, String> implements BrokerageLogService {
	
	@Resource
	private BaseDao<BrokerageLog, String> baseDao;
	
	@Resource 
	private SalesmanService salesmanService;
	
	@Resource
	private BrokerageLogDao brokerageLogDao;

//	@Resource
//	public void setBaseDao(BrokerageLogDao brokerageLogDao) {
//		super.setBaseDao(brokerageLogDao);
//	}

	@Override
	public List<BrokerageLog> find(PageBean pageBean, Object[] objects) throws Exception {
		List<BrokerageLog> logs= brokerageLogDao.find(pageBean, objects);
		if(logs!=null){
			for (int i = 0; i < logs.size(); i++) {
				BrokerageLog log=logs.get(i);
				Salesman salesman= salesmanService.findById(log.getSid());
				log.setSalesman(salesman);
				logs.set(i, log);
			}
		}
		return logs;
	}

	@Override
	public List<BrokerageLog> find(PageBean pageBean) {
		return brokerageLogDao.find(pageBean);
	}

	@Override
	public long getTotalCount(String orderId) {
		String hql = "select count(*) from " + BrokerageLog.class.getSimpleName() + " as o where o.orderId = ?";
		return baseDao.count(hql, new Object[] { orderId });
	}
}