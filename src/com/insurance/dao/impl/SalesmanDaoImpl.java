package com.insurance.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dao.SalesmanDao;
import com.insurance.model.Salesman;


/**
 * 会员DaoImpl
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月15日
 */
@Repository("SalesmanDao")
@Transactional
public class SalesmanDaoImpl extends BaseDaoImpl<Salesman, String>  implements SalesmanDao {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Salesman> findByInvate(String invate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT * FROM t_salesman WHERE invate IN (SELECT s.phone FROM t_salesman s WHERE s.invate='13609628348')");
		List<Salesman> list = query.list();
		return list;
		
	}
	
	
	public List<String> findByInvate(){
		String hql="FROM Salesman s WHERE s.invate=?";
		super.find(hql, new Object[]{""});
		return null;
	}

}
