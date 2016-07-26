package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.Finance;
import com.insurance.model.OrderCharge;
import com.insurance.model.PageBean;
import com.insurance.service.OrderchargeService;
import com.insurance.util.SundryTest;

/**
 * 支付信息Service
 * 
 * @author laohu 2014-11-17
 */

@Service("OrderchargeService")
public class OrderchargeServiceImpl extends BaseServiceImpl<OrderCharge, String> implements OrderchargeService {

	@Resource
	private BaseDao<OrderCharge, String> baseDao;

	@Override
	public List<OrderCharge> findOrderCharge(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from OrderCharge order by payCode desc", pageBean, param);
		}
		return baseDao.find("from OrderCharge order by payCode desc", param);
	}

	@Override
	public List<OrderCharge> findOrderCharge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getOrderChargeCount() {
		return baseDao.count("select count(*) from OrderCharge");
	}

	@Override
	public OrderCharge getOrderChargeById(int OrderChargeIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addOrderCharge(OrderCharge orderCharge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteOrderCharge(OrderCharge orderCharge) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrderCharge(OrderCharge orderCharge) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderCharge findOrderChargeById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderCharge> findOrderChargeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add1(String testjson2) {
		// TODO Auto-generated method stub

	}

	@Override
	public String insert(OrderCharge orderCharge) {
		orderCharge.setId(SundryTest.uuid());
		baseDao.saveorUpdate(orderCharge);
		return "添加支付信息成功";
	}

	@Override
	public void update(OrderCharge orderCharge) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletes(String ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderCharge findById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(OrderCharge a) {
		// TODO Auto-generated method stub

	}

}
