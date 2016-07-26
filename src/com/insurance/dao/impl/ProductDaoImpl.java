package com.insurance.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dao.ProductDao;
import com.insurance.model.Product;

@Repository("ProductDao")
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product, String> implements ProductDao {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Product> findByInvate(String invate) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createSQLQuery("SELECT * FROM t_product WHERE invate IN (SELECT s.phone FROM t_product s WHERE s.invate='13609628348')");
		List<Product> list = query.list();
		return list;

	}

	public List<String> findByInvate() {
		String hql = "FROM Salesman s WHERE s.invate=?";
		super.find(hql, new Object[]{""});
		return null;
	}

	/**
	 * 根据产品类型查询产品列表
	 */
	@Override
	public List<Product> findProductListByProductType(String string) {
		String hql = "FROM Product s WHERE s.productType_id = ?";
		return super.find(hql, new Object[]{string});
	}

	@Override
	public Product getProduct(String product_id) {
		Product product = null;
		String hql = "FROM Product s WHERE s.insuranceType_id = ?";
		List<Product> list = super.find(hql, new Object[]{product_id});
		if (list.size() > 0) {
			product = list.get(0);
		}
		return product;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getHotProductList(int total) {
		String hql = "from Product as product order by product.isTop desc, product.createDate desc";
		return getSession().createQuery(hql).setMaxResults(total).list();
	
	}
}