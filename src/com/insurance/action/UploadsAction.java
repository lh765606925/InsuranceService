package com.insurance.action;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.insurance.model.Salesman;
import com.opensymphony.xwork2.ActionSupport;
public class UploadsAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String name;

    // 上传多个文件的集合文本

    private List<File> upload;
    // /多个上传文件的类型集合
    private List<String> uploadContentType;
   // 多个上传文件的文件名集合
    private List<String> uploadFileName;
    
    private Salesman salesman;
	
    
	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

    public String getName() {
            return name;
     }

    public void setName(String name) {

       this.name = name;
    }

    public List<File> getUpload() {

       return upload;
    }

    public void setUpload(List<File> upload) {

       this.upload = upload;
    }

    public List<String> getUploadContentType() {

       return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {

       this.uploadContentType = uploadContentType;
    }

    public List<String> getUploadFileName() {

       return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {

       this.uploadFileName = uploadFileName;
    }

    public String execute() {

       String path =System.getProperty("catalina.home")+"/webapps/InsuranceSeviceData";
       // 写到指定的路径中

       File file = new File(path);

       // 如果指定的路径没有就创建

       if (!file.exists()) {

           file.mkdirs();
       }

       // 把得到的文件的集合通过循环的方式读取并放在指定的路径下

       for (int i = 0; i < upload.size(); i++) {
           try {

              //list集合通过get(i)的方式来获取索引

              FileUtils.copyFile(upload.get(i), new File(file, uploadFileName.get(i)));

           } catch (IOException e) {


              e.printStackTrace();
           }
       }

       return SUCCESS;
    }
    
    public String salesman(){
    	String path =System.getProperty("catalina.home")+"/webapps/InsuranceSeviceData/salesman/";
        // 写到指定的路径中
        File file = new File(path);
        // 如果指定的路径没有就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        // 把得到的文件的集合通过循环的方式读取并放在指定的路径下

        for (int i = 0; i < upload.size(); i++) {
            try {
               //list集合通过get(i)的方式来获取索引
               FileUtils.copyFile(upload.get(i), new File(file, uploadFileName.get(i)));
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    	return null;
    }
}
