package com.insurance.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dao.DepositDao;
import com.insurance.model.Deposit;
import com.insurance.model.DepositSalesman;
import com.insurance.model.Salesman;


@Repository("DepositDao")
@Transactional
public class DepositDaoImpl extends BaseDaoImpl<Deposit, String> implements DepositDao{

	@Resource
	private SessionFactory sessionFactory;
	/**
	 * 充值记录+用户表关联查询
	 * @param invate
	 * @return
	 */
	@Override
	public List<Deposit> findDeposit_Salesman() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select * from t_deposit as u left join t_salesman a on u.salesman_id = a.sid");
		List<Deposit> list = query.list();
		return list;
		
	}
	
}
