package com.insurance.service;

import java.io.File;

import com.insurance.bean.ProductImage;



/**
 * Service接口 - 商品图片
 */

public interface ProductImageService {
	
	/**
	 * 生成商品图片（包括原图、大图、小图、缩略图）
	 * 
	 * @param uploadProductImageFile
	 *            上传图片文件
	 * 
	 */
	public ProductImage buildProductImage(File uploadProductImageFile);
	/**
	 * 生成相册商品图片（包括原图、大图、小图）
	 * 
	 * @param uploadPhotosImageFile
	 *            上传图片文件
	 * 
	 */
	public ProductImage buildPhotosImage(File uploadPhotosImageFile);
	
	/**
	 * 根据ProductImage对象删除图片文件
	 * 
	 * @param productImage
	 *            ProductImage
	 * 
	 */
	public void deleteFile(ProductImage productImage);

}