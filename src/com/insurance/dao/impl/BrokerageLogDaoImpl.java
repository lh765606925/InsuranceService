package com.insurance.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.insurance.dao.BrokerageLogDao;
import com.insurance.model.BrokerageLog;
import com.insurance.model.Orders;
import com.insurance.model.PageBean;
import com.insurance.service.SalesmanService;

/**
 * Dao实现类 - 佣金发放日志
 */

@Repository
public class BrokerageLogDaoImpl extends BaseDaoImpl<BrokerageLog, String> implements BrokerageLogDao {

	
	@Override
	public List<BrokerageLog> find(PageBean pageBean) {
		String hql = "from " + BrokerageLog.class.getSimpleName() + " as b order by b.createDate Desc";
		List<BrokerageLog> logs = find(hql, pageBean, null);
		if (logs != null && logs.size() > 0) {
			return logs;
		}
		return null;
	}

	@Override
	public List<BrokerageLog> find(PageBean pageBean, Object[] objects) {
		String hql = "from " + BrokerageLog.class.getSimpleName() + " as b where b.orderId = ? order by b.amount Desc";
		List<BrokerageLog> logs = find(hql, pageBean, objects);
		if (logs != null && logs.size() > 0) {
			return logs;
		}
		return null;
	}

}