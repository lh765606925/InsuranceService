package com.insurance.model;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//import javax.persistence.Transient;
//
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
//import org.hibernate.annotations.OrderBy;
import javax.persistence.Table;

/**
 * 实体类 - 商品类型
 */

@Entity
@Table(name = "t_producttype")
public class ProductType extends BaseEntity {

	private static final long serialVersionUID = -6173231303962800416L;

	private String name;// 类型名称
	
//	private List<ProductAttribute> productAttributeList;// 商品属性
//	private Set<Product> productSet;// 商品
	
	// Fields

	private int typeid;//分类ID
	private String typeName;//分类名称
    private int parentId;//父类ID
    private String powerDesc;//序号
    private String state;//状态
    private String iconCls;//图标
    private String background_color;//分类图标背景色
    private String background_imgpath;//分类图标背景图片路径
    private int level;//层级
    private int hasnext;//是否有子目录
    
	// Constructors

	public int getHasnext() {
		return hasnext;
	}

	public void setHasnext(int hasnext) {
		this.hasnext = hasnext;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/** default constructor */
	public ProductType() {
	}
	
	public int getTypeid() {
		return typeid;
	}



	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public int getParentId() {
		return parentId;
	}



	public void setParentId(int parentId) {
		this.parentId = parentId;
	}



	public String getPowerDesc() {
		return powerDesc;
	}



	public void setPowerDesc(String powerDesc) {
		this.powerDesc = powerDesc;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getIconCls() {
		return iconCls;
	}



	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}



	public String getBackground_color() {
		return background_color;
	}



	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}



	public String getBackground_imgpath() {
		return background_imgpath;
	}



	public void setBackground_imgpath(String background_imgpath) {
		this.background_imgpath = background_imgpath;
	}



	

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
//	@Cascade(value = { CascadeType.DELETE })
//	@OrderBy(clause = "orderList asc")
//	public List<ProductAttribute> getProductAttributeList() {
//		return productAttributeList;
//	}
//
//	public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
//		this.productAttributeList = productAttributeList;
//	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
//	public Set<Product> getProductSet() {
//		return productSet;
//	}
//
//	public void setProductSet(Set<Product> productSet) {
//		this.productSet = productSet;
//	}
	
	// 获得已启用的商品属性
//	@Transient
//	public List<ProductAttribute> getEnabledProductAttributeList() {
//		if (productAttributeList == null) {
//			return null;
//		}
//		List<ProductAttribute> enabledProductAttributeList = new ArrayList<ProductAttribute>();
//		for (ProductAttribute productAttribute : productAttributeList) {
//			if (productAttribute.getIsEnabled()) {
//				enabledProductAttributeList.add(productAttribute);
//			}
//		}
//		return enabledProductAttributeList;
//	}

}