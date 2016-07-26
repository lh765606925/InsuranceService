package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.PageBean;
import com.insurance.model.PolicyHolder;
import com.insurance.model.Product;
import com.insurance.model.ProductDetailinfo;
import com.insurance.model.ProductPrice;
import com.insurance.service.ProductPriceService;

@Service("ProductPriceService")
public class ProductPriceServiceImpl extends BaseServiceImpl<ProductDetailinfo, String> implements ProductPriceService {

	@Resource
	private BaseDao<ProductPrice, String> baseDao;

	private ProductPrice price;

	public ProductPrice getPrice() {
		return price;
	}

	public void setPrice(ProductPrice price) {
		this.price = price;
	}

	@Override
	public List<ProductPrice> findProductPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductPrice findDetailinfoByProduct_id(String product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getProductCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductPrice> findProductPrice(PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPrice> findProductPrice(PageBean pageBean, Object[] prampObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductPrice findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPrice> findByProduct_id(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ProductPrice productPrice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(List<ProductPrice> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductPrice> findProductPriceByProductId(String product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletes(String ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ProductPrice a) {

	}

	@Override
	public void update(ProductPrice productPrice) {

	}

	@Override
	public Double getPrice(int days, String productId) {
		List<ProductPrice> slist = baseDao.find(
				"from ProductPrice s where s.productId=? and s.maxDay > ? and s.minDay <= ? ", new Object[]{productId,
						days, days});
		Double rs = 0.00;
		if (slist.size() > 0) {
			price = slist.get(0);
			return price.getPrice();
		} else {
			return rs;
		}
	}

}
