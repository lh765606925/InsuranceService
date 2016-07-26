package com.insurance.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dao.ProductDetailinfoDao;
import com.insurance.model.PageBean;
import com.insurance.model.ProductDetailinfo;

@Repository("ProductDetailinfoDao")
@Transactional
public class ProductDetailinfoDaoImpl extends BaseDaoImpl<ProductDetailinfo, String> implements ProductDetailinfoDao {


	@Override
	public List<ProductDetailinfo> findProductDetailinfo() {
		return super.find("from ProductDetailinfo order by createDate Desc");
	}
	@Override
	public List<ProductDetailinfo> findProductDetailinfoByProductId(String product_id) {
		try {

			// super.find("from ProductDetailinfo order by createDate Desc");
			return super.find("from ProductDetailinfo p where p. product_id = ?", new Object[]{product_id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ProductDetailinfo findDetailinfoByProduct_id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(List<ProductDetailinfo> list) {
		try {
			ProductDetailinfo productDetailinfo = null;
			for (int i = 0; i < list.size(); i++) {
				productDetailinfo = list.get(i);
				super.save(productDetailinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list.size();
	}
	@Override
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean,Object[] pram) {
		if (pageBean != null) {
			return super.find("from ProductDetailinfo order by createDate Desc", pageBean,pram);
		}
		return null;
	}
	@Override
	public long getProductCount() {

		return super.count("select count(*) from ProductDetailinfo");
	}
	@Override
	public List<ProductDetailinfo> findProductDetailinfo(PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insert(ProductDetailinfo productDetailinfo) {
		//获取当前时间		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		productDetailinfo.setCreateDate(sdf1.format(d));
		super.saveorUpdate(productDetailinfo);
		return 0;
	}
	
	/**
	 * 根据产品详情ID查找产品详情
	 */
	@Override
	public ProductDetailinfo findById(String id) {
		ProductDetailinfo productDetailinfo=null;
		List<ProductDetailinfo> list=super.find("from ProductDetailinfo pd where pd.id=?",new Object[]{id});
		if (list.size()>0) {
			productDetailinfo=list.get(0);
		}
		return productDetailinfo;
	}
	@Override
	public void deletes(String ids) {
		super.executeHql("delete from ProductDetailinfo s where s.id in (" + ids + ")");
	}

}
