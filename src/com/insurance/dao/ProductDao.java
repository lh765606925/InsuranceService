package com.insurance.dao;

import java.util.List;

import com.insurance.model.Product;

/**
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月5日
 */
public interface ProductDao extends BaseDao<Product, String> {

	/**
	 * 
	 * @param invate
	 * @return
	 */
	public List<Product> findByInvate(String invate);

	/**
	 * 
	 * @param string
	 * @return
	 */
	public List<Product> findProductListByProductType(String string);

	public Product getProduct(String product_id);

	public List<Product> getHotProductList(int total);
}
