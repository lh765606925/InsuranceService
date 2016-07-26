package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Product;
import com.insurance.model.ProductDetailinfo;

/**
 * 产品详情service
 * 
 * @author laohu
 *
 */
public interface ProductDetailinfoService {
	
	public List<ProductDetailinfo> findProductDetailinfo();
	
	public ProductDetailinfo findDetailinfoByProduct_id(String product_id);

	public long getProductCount();

	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean);
	
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean ,Object[] prampObjects);
	

	public ProductDetailinfo findById(String id);
	public  List<ProductDetailinfo> findByProduct_id(String id);

	public int insert(ProductDetailinfo productDetailinfo);
	
	public int insert(List<ProductDetailinfo> list);

	public List<ProductDetailinfo> findProductDetailinfoByProductId(String product_id);

	public void deletes(String ids);

	public void delete(ProductDetailinfo a);

	public void update(ProductDetailinfo productDetailinfo);
}
