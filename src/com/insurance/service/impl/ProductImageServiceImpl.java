package com.insurance.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;








import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.insurance.bean.ProductImage;
import com.insurance.bean.SystemConfig;
import com.insurance.service.ProductImageService;
import com.insurance.util.CommonUtil;
import com.insurance.util.ImageUtil;
import com.insurance.util.SystemConfigUtil;

/**
 * Service实现类 - 商品图片
 */

@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	public ProductImage buildProductImage(File uploadProductImageFile) {
		SystemConfig systemConfig = SystemConfigUtil.getSystemConfig();
		String sourceProductImageFormatName = ImageUtil.getImageFormatName(uploadProductImageFile);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String dateString = simpleDateFormat.format(new Date());
		String uuid = CommonUtil.getUUID();
		
		String sourceProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + "." + sourceProductImageFormatName;
		String bigProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + ProductImage.BIG_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;
		String smallProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + ProductImage.SMALL_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;
		String thumbnailProductImagePath = SystemConfig.UPLOAD_IMAGE_DIR + dateString + "/" + uuid + ProductImage.THUMBNAIL_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;

		File sourceProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(sourceProductImagePath));
		File bigProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(bigProductImagePath));
		File smallProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(smallProductImagePath));
		File thumbnailProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(thumbnailProductImagePath));
		File watermarkImageFile = new File(ServletActionContext.getServletContext().getRealPath(systemConfig.getWatermarkImagePath()));

		File sourceProductImageParentFile = sourceProductImageFile.getParentFile();
		File bigProductImageParentFile = bigProductImageFile.getParentFile();
		File smallProductImageParentFile = smallProductImageFile.getParentFile();
		File thumbnailProductImageParentFile = thumbnailProductImageFile.getParentFile();

		if (!sourceProductImageParentFile.exists()) {
			sourceProductImageParentFile.mkdirs();
		}
		if (!bigProductImageParentFile.exists()) {
			bigProductImageParentFile.mkdirs();
		}
		if (!smallProductImageParentFile.exists()) {
			smallProductImageParentFile.mkdirs();
		}
		if (!thumbnailProductImageParentFile.exists()) {
			thumbnailProductImageParentFile.mkdirs();
		}

		try {
			BufferedImage srcBufferedImage = ImageIO.read(uploadProductImageFile);
			// 将上传图片复制到原图片目录
			FileUtils.copyFile(uploadProductImageFile, sourceProductImageFile);
			// 商品图片（大）缩放、水印处理
			ImageUtil.zoomAndWatermark(srcBufferedImage, bigProductImageFile, systemConfig.getBigProductImageHeight(), systemConfig.getBigProductImageWidth(), watermarkImageFile, systemConfig.getWatermarkPosition(), systemConfig.getWatermarkAlpha().intValue());
			// 商品图片（小）缩放、水印处理
			ImageUtil.zoomAndWatermark(srcBufferedImage, smallProductImageFile, systemConfig.getSmallProductImageHeight(), systemConfig.getSmallProductImageWidth(), watermarkImageFile, systemConfig.getWatermarkPosition(), systemConfig.getWatermarkAlpha().intValue());
			// 商品图片缩略图处理
			ImageUtil.zoom(srcBufferedImage, thumbnailProductImageFile, systemConfig.getThumbnailProductImageHeight(), systemConfig.getThumbnailProductImageWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ProductImage productImage = new ProductImage();
		productImage.setId(uuid);
		productImage.setSourceProductImagePath(sourceProductImagePath);
		productImage.setBigProductImagePath(bigProductImagePath);
		productImage.setSmallProductImagePath(smallProductImagePath);
		productImage.setThumbnailProductImagePath(thumbnailProductImagePath);
		return productImage;
	}
	//前台图集图像处理
	//小图、大图，水印图
	public ProductImage buildPhotosImage(File uploadProductImageFile) {
		SystemConfig systemConfig = SystemConfigUtil.getSystemConfig();
		String sourceProductImageFormatName = ImageUtil.getImageFormatName(uploadProductImageFile);
		String uuid = CommonUtil.getUUID();
		String sourcePhotosImagePath = SystemConfig.UPLOAD_PHOTOS_DIR +  uuid + "." + sourceProductImageFormatName;
		String bigPhotosImagePath = SystemConfig.UPLOAD_PHOTOS_DIR +  uuid + ProductImage.BIG_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;
		String smallPhotosImagePath = SystemConfig.UPLOAD_PHOTOS_DIR +  uuid + ProductImage.SMALL_PRODUCT_IMAGE_FILE_NAME_SUFFIX + "." + ProductImage.PRODUCT_IMAGE_FILE_EXTENSION;

		File sourceProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(sourcePhotosImagePath));
		File bigPhotosImageFile = new File(ServletActionContext.getServletContext().getRealPath(bigPhotosImagePath));
		File smallPhotosImageFile = new File(ServletActionContext.getServletContext().getRealPath(smallPhotosImagePath));
		File watermarkImageFile = new File(ServletActionContext.getServletContext().getRealPath(systemConfig.getWatermarkImagePath()));

		File sourcePhotosImageParentFile = sourceProductImageFile.getParentFile();
		File bigPhotosImageParentFile = bigPhotosImageFile.getParentFile();
		File smallPhotosImageParentFile = smallPhotosImageFile.getParentFile();

		if (!sourcePhotosImageParentFile.exists()) {
			sourcePhotosImageParentFile.mkdirs();
		}
		if (!bigPhotosImageParentFile.exists()) {
			bigPhotosImageParentFile.mkdirs();
		}
		if (!smallPhotosImageParentFile.exists()) {
			smallPhotosImageParentFile.mkdirs();
		}

		try {
			BufferedImage srcBufferedImage = ImageIO.read(uploadProductImageFile);
			// 将上传图片复制到原图片目录
//			FileUtils.copyFile(uploadProductImageFile, sourceProductImageFile);
			// 商品图片（大）缩放、水印处理
//			ImageUtil.zoomAndWatermark(srcBufferedImage, bigPhotosImageFile, systemConfig.getBigPhotosImageHeight(), systemConfig.getBigPhotosImageWidth(), watermarkImageFile, systemConfig.getWatermarkPosition(), systemConfig.getWatermarkAlpha().intValue());
			//缩略图(小图)
			ImageUtil.zoom(srcBufferedImage, smallPhotosImageFile, systemConfig.getThumbnailProductImageHeight(), systemConfig.getThumbnailProductImageWidth());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ProductImage indexImage = new ProductImage();
		indexImage.setId(uuid);
		indexImage.setSourceProductImagePath(sourcePhotosImagePath);
//		indexImage.setBigPhotosImagePath(bigPhotosImagePath);
		indexImage.setSmallPhotosImagePath(smallPhotosImagePath);
		return indexImage;
	}
	
	public void deleteFile(ProductImage productImage) {
		File sourceProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getSourceProductImagePath()));
		if (sourceProductImageFile.exists()) {
			sourceProductImageFile.delete();
		}
		File bigProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getBigProductImagePath()));
		if (bigProductImageFile.exists()) {
			bigProductImageFile.delete();
		}
		File smallProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getSmallProductImagePath()));
		if (smallProductImageFile.exists()) {
			smallProductImageFile.delete();
		}
		File thumbnailProductImageFile = new File(ServletActionContext.getServletContext().getRealPath(productImage.getThumbnailProductImagePath()));
		if (thumbnailProductImageFile.exists()) {
			thumbnailProductImageFile.delete();
		}
	}

}