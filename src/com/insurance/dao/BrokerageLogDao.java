package com.insurance.dao;

import java.util.List;

import com.insurance.model.BrokerageLog;
import com.insurance.model.PageBean;

/**
 * Dao接口 - 佣金发放日志
 * 
 * @author Administrator
 *
 */
public interface BrokerageLogDao extends BaseDao<BrokerageLog, String> {

	public List<BrokerageLog> find(PageBean pageBean, Object[] objects);

	public List<BrokerageLog> find(PageBean pageBean);

}