package com.insurance.util;

import org.apache.struts2.ServletActionContext;

/**
 * 字符串工具类
 * @author 
 *
 */
public class StringUtil {

	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得图片后缀
	 * @param str
	 * @return
	 */
	public static String getSuffix(String str){
		int i=str.lastIndexOf(".");
		if(i<0){
			return ".jpg";//无后缀返回默认后缀
		}else{
			return str.substring(i);
		}
	}
	
	/**
	 * 获得Tomcat webapp路径
	 * @return
	 */
	public static String getWebApp(){
		String a=ServletActionContext.getServletContext().getRealPath("");
		return (a.substring(0, a.lastIndexOf("\\")));
	}
}
