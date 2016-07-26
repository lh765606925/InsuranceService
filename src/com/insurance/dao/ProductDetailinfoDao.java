package com.insurance.dao;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.ProductDetailinfo;

public interface ProductDetailinfoDao  extends BaseDao<ProductDetailinfo, String>{
	public List<ProductDetailinfo> findProductDetailinfo();
	public ProductDetailinfo findDetailinfoByProduct_id();
	public int insert(List<ProductDetailinfo> list);
	public List<ProductDetailinfo> findProductDetailinfoByProductId(String product_id);
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean);
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean,Object[] pram);
	public long getProductCount();
	public int insert(ProductDetailinfo productDetailinfo);
	public ProductDetailinfo findById(String id);
	public void deletes(String ids);
}
