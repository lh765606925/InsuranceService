package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Product;

/**
 * 产品service
 * 
 * @author fly
 * 
 */
public interface ProductService {
	public List<Product> findProduct(PageBean pageBean, Object[] param);

	public List<Product> findProduct();

	public Long getProductCount();

	public Product getProductById(String productid) throws Exception;

	public int addProduct(Product Product);

	public void deleteProduct(Product Product);

	public void updateProduct(Product Product);

	public Product findProductById(String id);

	public List<Product> findProductList();

	/**
	 * 插入产品
	 * 
	 * @param product
	 * @return
	 */
	public int insert(Product product);

	/**
	 * 更新产品信息
	 * 
	 * @param product
	 */
	public void update(Product product);

	/**
	 * 删除产品信息
	 * 
	 * @param ids
	 */
	public void deletes(String ids);

	/**
	 * 根据ids查询产品信息
	 * 
	 * @param ids
	 * @return
	 */
	public Product findById(String ids);

	/**
	 * 删除产品
	 * 
	 * @param a
	 */
	public void delete(Product a);

	/**
	 * 根据指定类型查询产品
	 * @param string 
	 * @return
	 */
	public List<Product> findProductListByProductType(String string);

	/**
	 * 分页查询产品信息
	 * @param pageBean
	 * @return
	 */
	public List<Product> findProductList(PageBean pageBean);

	public Product getProduct(String product_id);

	public List<Product> getHotProductList(int total);

	public List<Product> findProduct_by_brand(PageBean pageBean, Object[] param);

}
