package com.insurance.service;

import java.util.List;

import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.OrderCharge;

/**
 * 支付信息service
 * 
 * @author laohu
 * 
 */
public interface OrderchargeService extends BaseService<OrderCharge, String> {
	public List<OrderCharge> findOrderCharge(PageBean pageBean, Object[] param);
	public List<OrderCharge> findOrderCharge();

	public Long getOrderChargeCount();

	public OrderCharge getOrderChargeById(int OrderChargeIds) throws Exception;

	public int addOrderCharge(OrderCharge orderCharge);

	public void deleteOrderCharge(OrderCharge orderCharge);

	public void updateOrderCharge(OrderCharge orderCharge);

	public OrderCharge findOrderChargeById(String id);

	public List<OrderCharge> findOrderChargeList();
	public void add1(String testjson2);

	/**
	 * 插入支付信息
	 * 
	 * @param OrderCharge
	 * @return
	 */
	public String insert(OrderCharge orderCharge);

	/**
	 * 更新支付信息信息
	 * 
	 * @param OrderCharge
	 */
	public void update(OrderCharge orderCharge);

	/**
	 * 删除支付信息信息
	 * 
	 * @param ids
	 */
	public void deletes(String ids);

	/**
	 * 根据ids查询支付信息信息
	 * 
	 * @param ids
	 * @return
	 */
	public OrderCharge findById(String ids);

	/**
	 * 删除支付信息
	 * 
	 * @param a
	 */
	public void delete(OrderCharge a);

}
