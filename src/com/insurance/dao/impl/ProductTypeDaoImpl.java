package com.insurance.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.insurance.dao.ProductTypeDao;
import com.insurance.model.ProductType;

/**
 * Dao实现类 - 商品类型
 */

@Repository
public class ProductTypeDaoImpl extends BaseDaoImpl<ProductType, String> implements ProductTypeDao {


	// 关联处理
	@Override
	public void delete(String id) {
		ProductType productType = super.load(id);
		this.delete(productType);
	}

	// 关联处理
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			ProductType productType = super.load(id);
			this.delete(productType);
		}
	}

}