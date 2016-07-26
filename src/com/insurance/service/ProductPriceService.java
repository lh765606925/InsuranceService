package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.ProductPrice;

public interface ProductPriceService {

	public List<ProductPrice> findProductPrice();

	public ProductPrice findDetailinfoByProduct_id(String product_id);

	public long getProductCount();

	public List<ProductPrice> findProductPrice(PageBean pageBean);

	public List<ProductPrice> findProductPrice(PageBean pageBean, Object[] prampObjects);

	public ProductPrice findById(String id);

	public List<ProductPrice> findByProduct_id(String id);

	public int insert(ProductPrice productPrice);

	public int insert(List<ProductPrice> list);

	public List<ProductPrice> findProductPriceByProductId(String product_id);

	public void deletes(String ids);

	public void delete(ProductPrice a);

	public void update(ProductPrice productPrice);

	public Double getPrice(int days,String productId) ;
}