package com.insurance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.insurance.dao.BaseDao;
import com.insurance.dao.ProductDao;
import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.Policyinsured;
import com.insurance.model.Product;
import com.insurance.service.ProductService;

import org.springframework.stereotype.Service;

/**
 * 产品Service实现类
 * 
 * @author laohu 2015-05-26
 */

@Service("ProductService")
public class ProductServiceImpl extends BaseServiceImpl<Product, String> implements ProductService {

	@Resource
	private BaseDao<Product, String> baseDao;

	@Resource
	private ProductDao productDao;

	@Override
	public List<Product> findProduct(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return productDao.find("from Product", pageBean, param);
		}
		return null;
	}

	@Override
	public List<Product> findProduct_by_brand(PageBean pageBean, Object[] param) {
		String hql = "from Product as p where p.productBrand_id = ?";
		if (pageBean != null) {
			return productDao.find(hql, pageBean, param);
		} else {
			return productDao.find(hql, param);
		}
	}

	@Override
	public int insert(Product product) {
		// 获取当前时间
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		product.setCreateDate(sdf1.format(d).toString());
		return (Integer) productDao.save(product);
	}

	@Override
	public Long getProductCount() {
		return productDao.count("select count(*) from Product");
	}

	/**
	 * 获取所有产品信息
	 */
	@Override
	public List<Product> findProductList() {
		String hql = "from Product order by createDate Desc";
		return productDao.find(hql);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public void deletes(String ids) {
		try {

			System.out.println("delete from Product s where s.id in (" + ids + ")");
			baseDao.executeHql("delete from Product where id in (" + ids + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据ID查询产品信息
	 */
	@Override
	public Product findById(String parseInt) {
		List<Product> slist = productDao.find("from Product s where s.id=?", new Object[] { parseInt });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 删除产品信息
	 */
	@Override
	public void delete(Product a) {
		productDao.delete(a);
	}

	@Override
	public List<Product> findProductListByProductType(String string) {
		return productDao.findProductListByProductType(string);
	}

	@Override
	public List<Product> findProduct() {
		return null;
	}

	@Override
	public Product getProductById(String productId) throws Exception {
		List<Product> slist = productDao.find("from Product s where s.insuranceType_id=?", new Object[] { productId });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int addProduct(Product Product) {
		return 0;
	}

	@Override
	public void deleteProduct(Product Product) {

	}

	@Override
	public void updateProduct(Product Product) {

	}

	@Override
	public Product findProductById(String id) {
		String hql = "from Product p where p.id = ?";
		List<Product> list = baseDao.find(hql, new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Product> findProductList(PageBean pageBean) {
		if (pageBean != null) {
			return productDao.find("from Product order by createDate desc", pageBean, null);
		}
		return productDao.find("from Product order by createDate desc");
	}

	@Override
	public Product getProduct(String product_id) {
		return productDao.getProduct(product_id);
	}

	@Override
	public List<Product> getHotProductList(int total) {
		return productDao.getHotProductList(total);
	}

}
