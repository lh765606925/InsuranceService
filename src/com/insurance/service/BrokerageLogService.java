package com.insurance.service;

import java.util.List;

import com.insurance.model.BrokerageLog;
import com.insurance.model.PageBean;

/**
 * Service接口 - 佣金发放日志
 * 
 * @author Administrator
 * 
 */
public interface BrokerageLogService extends BaseService<BrokerageLog, String> {

	public List<BrokerageLog> find(PageBean pageBean, Object[] objects) throws Exception;

	public List<BrokerageLog> find(PageBean pageBean);

	public long getTotalCount(String orderId);

}
