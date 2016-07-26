package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.dao.ProductTypeDao;
import com.insurance.model.Article;
import com.insurance.model.ProductType;
import com.insurance.service.ProductTypeService;

@Service("ProductTypeService")
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, String> implements ProductTypeService {

	private int level = -1;
	@Resource
	private BaseDao<ProductType, String> baseDao;

	@Override
	public Long getPowerCount() {
		StringBuffer hql = new StringBuffer("select count(*) FROM Function");
		Long powerCount = baseDao.count(hql.toString());
		return powerCount;
	}

	@Override
	public void deletePower(ProductType power) {
		baseDao.delete(power);
		if (isLeaf(power.getParentId())) {
			updateStateByPowerId("open", power.getParentId());
		}

	}

	@Override
	public ProductType getFunctionById(int id) throws Exception {
		ProductType productType = null;
		productType = baseDao.get(ProductType.class, id);
		return productType;
	}

	@Override
	public void updatePower(ProductType productType) {
		baseDao.update(productType);
	}

	@Override
	public JSONArray getTreeGridPowerByParentId(int parentId) {
		List<ProductType> plist = baseDao.find("from ProductType p where p.parentId=? order by typeid",
				new Object[] { parentId });
		JSONArray jsonArray = JSONArray.fromObject(plist);
		return jsonArray;

	}

	@Override
	public JSONArray getListByParentId(int parentId) {

		JSONArray jsonArray = this.getTreeGridPowerByParentId(parentId);

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			if ("open".equals(jsonObject.getString("state"))) {
				continue;
			} else {
				jsonObject.put("children", getListByParentId(jsonObject.getInt("typeid")));
			}
		}
		return jsonArray;
	}

	@Override
	public int addProductType(ProductType productType) {
		if (isLeaf(productType.getParentId())) {
			updateStateByPowerId("closed", productType.getParentId());
		}
		productType.setState("open");
		return (Integer) baseDao.save(productType);

	}

	@Override
	public boolean isLeaf(int powerId) {
		List<ProductType> list = baseDao.find("from ProductType p where p.parentId=?", new Object[] { powerId });
		return list.size() < 1;
	}

	@Override
	public void updateStateByPowerId(String state, int powerId) {
		baseDao.executeSql("update ProductType set state=? where typeid=?", new Object[] { state, powerId });
	}

	@Override
	public ProductType findProductTypeById(int id) {
		try {
			ProductType p = baseDao.get(ProductType.class, id);
			return p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("findPowerById:Error");
		}
		return null;
	}

	@Override
	public int addPower(ProductType productType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateProductType(ProductType power) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProductType(ProductType p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductType> findAllList() {
		List<ProductType> plist = baseDao.find("from ProductType order by typeid");
		return plist;
	}

	@Override
	public List<ProductType> showTypebylevel(int level) {
		List<ProductType> plist = baseDao.find("from ProductType p where p.parentId = ? order by typeid",
				new Object[] { level });
		return plist;
	}

	@Override
	public List<ProductType> showTypebyTypeid(int typeid) {
		List<ProductType> plist = baseDao.find("from ProductType p where p.parentId = ? order by typeid",
				new Object[] { typeid });
		return plist;
	}

	@Override
	public int findTypeidByTypeName(String projecttype) {
		List<ProductType> plist = baseDao.find("from ProductType p where p.name = ? order by typeid",
				new Object[] { projecttype });
		if (plist.size() > 0) {
			ProductType productType = plist.get(0);
			return productType.getTypeid();
		}
		return 0;
	}

	@Override
	public ProductType findByTypeId(Integer typeid) {
		List<ProductType> plist = baseDao.find("from ProductType p where p.typeid = ? order by typeid",
				new Object[] { typeid });
		if (plist.size() > 0) {
			ProductType productType = plist.get(0);
			return productType;
		}
		return null;
	}

	public Long getTotalCount() {
		String hql = "select count(*) from " + ProductType.class.getSimpleName();
		return baseDao.count(hql, null);
	}
}
