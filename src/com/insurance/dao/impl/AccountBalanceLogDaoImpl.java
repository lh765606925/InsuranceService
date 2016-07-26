package com.insurance.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.insurance.dao.AccountBalanceLogDao;
import com.insurance.dao.BaseDao;
import com.insurance.model.AccountBalanceLog;
import com.insurance.model.BrokerageLog;
import com.insurance.model.Orders;
import com.insurance.model.PageBean;

@Repository
public class AccountBalanceLogDaoImpl extends BaseDaoImpl<AccountBalanceLog, String> implements AccountBalanceLogDao {

	@Resource
	private BaseDao<AccountBalanceLog, String> baseDao;

	@Override
	public List<AccountBalanceLog> find(PageBean pageBean, Object[] objects) {
		String hql = "from " + AccountBalanceLog.class.getSimpleName() + " as b where b.sid = ? order by b.createDate Desc";
		List<AccountBalanceLog> logs = find(hql, pageBean, objects);
		if (logs != null && logs.size() > 0) {
			return logs;
		}
		return null;
	}

	@Override
	public List<AccountBalanceLog> find(PageBean pageBean) {
		String hql = "from " + AccountBalanceLog.class.getSimpleName() + " as b order by b.createDate Desc";
		List<AccountBalanceLog> logs = find(hql, pageBean, null);
		if (logs != null && logs.size() > 0) {
			return logs;
		}
		return null;
	}

	@Override
	public long getTotalCount(int sid) {
		String hql = "select count(*) from " + AccountBalanceLog.class.getSimpleName() + " as o where o.sid = ?";
		return baseDao.count(hql, new Object[] { sid });
	}

}
