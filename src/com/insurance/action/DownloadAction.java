package com.insurance.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.bean.FlieNameEntity;
import com.insurance.util.FileUtil;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.ResponseUtil;
import com.insurance.util.SftpClientUtil;

/**
 * @author huzhihong
 *
 * 创建日期：2015年11月10日
 */
public class DownloadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private String username;
	private String password;
	private String name;
	private String filePath;
	private String abc;

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 下载所有文件
	 * 
	 */
	public String allfile() throws Exception {
		try {
			PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
			host = propertiesUtil.readValue("SFTP_HOST");// sftp服务器IP
			port = Integer.parseInt(propertiesUtil.readValue("SFTP_PORT"));// 端口
			username = propertiesUtil.readValue("SFTP_USERNAME");
			password = propertiesUtil.readValue("SFTP_PASSWORD");
			String directory = propertiesUtil.readValue("SFTP_FINANCE_FILE_PATH");// 服务器文件路径
			String saveDirectory = propertiesUtil.readValue("SFTP_LOCAL_FINANCE_FILE_PATH");// 下载到本地的路径
			SftpClientUtil sftpClientUtil = new SftpClientUtil(host, port, username, password);
			// 打开连接
			sftpClientUtil.connect();
			// 下载目录下全部文件
			sftpClientUtil.downloadByDirectory(directory, saveDirectory);
			// 关闭连接
			sftpClientUtil.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("leftPage", "common/finance_left.jsp");
		request.setAttribute("mainPage", "finance/Download.jsp");
		return SUCCESS;
	}

	/**
	 * 下载所有文件
	 * 
	 */
	public String fileByName() throws Exception {
		SftpClientUtil sftpClientUtil = new SftpClientUtil(host, port, username, password);
		sftpClientUtil.downloadByDirectory("/wls/paicsftp", "C://");
		return SUCCESS;
	}

	/**
	 * 获取指定目录下文件列表 此地指获取所有财务对账文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		FileUtil test = new FileUtil();
		Vector<String> files = new Vector<String>();
		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String saveDirectory = propertiesUtil.readValue("SFTP_LOCAL_FINANCE_FILE_PATH");// 下载到本地的路径
		String linkString = propertiesUtil.readValue("LINKURL");// http://113.105.94.20:8080/InsuranceData/FinanceFile/
		test.getFileList(files, saveDirectory, test.getIsIncludeSubFolder());
		List<FlieNameEntity> slist = new ArrayList<FlieNameEntity>();
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();

		for (String string : files) {
			FlieNameEntity fileEntity = new FlieNameEntity();
			fileEntity.setName(string);
			fileEntity.setLink(linkString + string);
			slist.add(fileEntity);
			System.out.println(string);
		}
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	public String showList() throws Exception {
		request.setAttribute("leftPage", "common/finance_left.jsp");
		request.setAttribute("mainPage", "finance/Download.jsp");
		return SUCCESS;
	}

}
