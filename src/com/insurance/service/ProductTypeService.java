package com.insurance.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.google.gson.JsonArray;
import com.insurance.model.Power;
import com.insurance.model.ProductType;

/**
 * Service接口 - 商品类型
 */

public interface ProductTypeService extends BaseService<ProductType, String> {

	public void updateStateByPowerId(String state, int powerId);

	public JSONArray getTreeGridPowerByParentId(int parentId);

	// 获取所有产品分类
	public List<ProductType> findAllList();

	public JSONArray getListByParentId(int parentId);

	public int addProductType(ProductType power);

	public ProductType findProductTypeById(int pid);

	public void updateProductType(ProductType power);

	public void deleteProductType(ProductType p);

	Long getPowerCount();

	void deletePower(ProductType power);

	ProductType getFunctionById(int id) throws Exception;

	void updatePower(ProductType productType);

	int addPower(ProductType productType);

	boolean isLeaf(int typeid);

	List<ProductType> showTypebylevel(int level);

	public List<ProductType> showTypebyTypeid(int typeid);

	/**
	 * 根据类型获得产品类型typeid
	 * 
	 * @param 产品类型名称
	 * @return 产品类型ID
	 */
	public int findTypeidByTypeName(String projecttype);

	/**
	 * 根据类型ID获得产品类型信息
	 * 
	 * @param typeid
	 * @return 产品类型信息
	 */
	public ProductType findByTypeId(Integer typeid);

	public Long getTotalCount();
}