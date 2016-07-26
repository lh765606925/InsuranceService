package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.ProductDetailinfoDao;
import com.insurance.model.PageBean;
import com.insurance.model.Product;
import com.insurance.model.ProductDetailinfo;
import com.insurance.service.ProductDetailinfoService;
/**
 * 产品详情Service实现类
 * 
 * @author laohu 2015-05-26
 */

@Service("ProductDetailinfoService")
public class ProductDetailinfoServiceImpl extends BaseServiceImpl<ProductDetailinfo, String>
		implements
			ProductDetailinfoService {

	@Resource
	private ProductDetailinfoDao productDetailinfoDao;
	@Override
	public List<ProductDetailinfo> findProductDetailinfo() {
		return productDetailinfoDao.findProductDetailinfo();
	}

	@Override
	public ProductDetailinfo findDetailinfoByProduct_id(String product_id) {
		return productDetailinfoDao.findDetailinfoByProduct_id();
	}
	@Override
	public List<ProductDetailinfo> findProductDetailinfoByProductId(String product_id) {
		return productDetailinfoDao.findProductDetailinfoByProductId(product_id);
	}

	@Override
	public long getProductCount() {
		return productDetailinfoDao.getProductCount();
	}

	@Override
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean) {
		return productDetailinfoDao.findProductDetailinfo(pageBean);
	}

	@Override
	public ProductDetailinfo findById(String id) {
		return productDetailinfoDao.findById(id);
	}

	@Override
	public int insert(ProductDetailinfo productDetailinfo) {
		return productDetailinfoDao.insert(productDetailinfo);
	}

	@Override
	public int insert(List<ProductDetailinfo> list) {
		return productDetailinfoDao.insert(list);
	}

	@Override
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean, Object[] prampObjects) {
		return productDetailinfoDao.findProductDetailinfo(pageBean, prampObjects);
	}

	@Override
	public List<ProductDetailinfo> findByProduct_id(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletes(String ids) {
		productDetailinfoDao.deletes(ids);
	}

	public void delete(ProductDetailinfo productDetailinfo) {
		productDetailinfoDao.delete(productDetailinfo);
	}

	public void update(ProductDetailinfo productDetailinfo) {
		productDetailinfoDao.update(productDetailinfo);
	}
}
