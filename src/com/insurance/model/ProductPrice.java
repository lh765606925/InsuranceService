package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_productprice")
public class ProductPrice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String productId;// 产品代码
	private Integer minDay;// 起始天数
	private Integer maxDay;// 终止天数
	private Double price;// 价格
	private String productgrouppriceId; // 关联productgroupPrice 表
	private String name;//名称

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductgrouppriceId() {
		return productgrouppriceId;
	}
	public void setProductgrouppriceId(String productgrouppriceId) {
		this.productgrouppriceId = productgrouppriceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getMinDay() {
		return minDay;
	}
	public void setMinDay(Integer minDay) {
		this.minDay = minDay;
	}
	public Integer getMaxDay() {
		return maxDay;
	}
	public void setMaxDay(Integer maxDay) {
		this.maxDay = maxDay;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
