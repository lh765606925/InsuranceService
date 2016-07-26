package com.insurance.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.google.gson.reflect.TypeToken;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.service.SalesmanService;
import com.insurance.util.DateUtil;
import com.insurance.util.MD5;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.QRCodeUtil;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;
import com.insurance.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
/**
 * 会员Action
 * @author huzhihong.com
 * 2014-11-18
 */
public class SalesmanAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	@Resource
	private SalesmanService salesmanService;

	private List<Salesman> salesmanList;
	private String[] ids;
	private Salesman salesman;
	private String page;
	private String offset;
	private String limit;

	private List<File> idPic;// 身份正照片
	private List<String> idPicFileName;
	private List<File> cyPic;// 从业证照片
	private List<String> cyPicFileName;
	private List<File> zyPic;// 展业证照片
	private List<String> zyPicFileName;
	private String invate;// 邀请人识别码
	private String phone;// 会员注册手机号码
	private File headPic;// 头像图片

	public File getHeadPic() {
		return headPic;
	}

	public void setHeadPic(File headPic) {
		this.headPic = headPic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInvate() {
		return invate;
	}

	public void setInvate(String invate) {
		this.invate = invate;
	}

	public List<File> getIdPic() {
		return idPic;
	}

	public void setIdPic(List<File> idPic) {
		this.idPic = idPic;
	}

	public List<String> getIdPicFileName() {
		return idPicFileName;
	}

	public void setIdPicFileName(List<String> idPicFileName) {
		this.idPicFileName = idPicFileName;
	}

	public List<File> getCyPic() {
		return cyPic;
	}

	public void setCyPic(List<File> cyPic) {
		this.cyPic = cyPic;
	}

	public List<String> getCyPicFileName() {
		return cyPicFileName;
	}

	public void setCyPicFileName(List<String> cyPicFileName) {
		this.cyPicFileName = cyPicFileName;
	}

	public List<File> getZyPic() {
		return zyPic;
	}

	public void setZyPic(List<File> zyPic) {
		this.zyPic = zyPic;
	}

	public List<String> getZyPicFileName() {
		return zyPicFileName;
	}

	public void setZyPicFileName(List<String> zyPicFileName) {
		this.zyPicFileName = zyPicFileName;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public List<Salesman> getSalesmanList() {
		return salesmanList;
	}

	public void setSalesmanList(List<Salesman> salesmanList) {
		this.salesmanList = salesmanList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	private String salesbean;

	private String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getPageindex() {
		return pageindex;
	}

	public void setPageindex(String pageindex) {
		this.pageindex = pageindex;
	}

	private String pageindex;

	public String getSalesbean() {
		return salesbean;
	}

	public void setSalesbean(String salesbean) {
		this.salesbean = salesbean;
	}

	public String updateQrcode() throws Exception {
		// 测试更新二维码
		List<Salesman> slist = salesmanService.find();
		for (int i = 0; i < slist.size(); i++) {
			Salesman salesman = slist.get(i);
			String dir = "/salesman/" + salesman.getSid();
			String path = StringUtil.getWebApp() + "/InsuranceData" + dir;
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resouse/string.properties");
			Properties p = new Properties();
			try {
				p.load(inputStream);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String filename = QRCodeUtil.encode("http://" + p.getProperty("serverIP")
					+ ":8080/InsuranceService/salesman/sale_register.jsp?invate=" + salesman.getPhone(),
					p.getProperty("logoPath"), path, false);
			salesman.setQrcode(dir + "/" + filename);

			salesmanService.saveorUpdate(salesman);

		}

		return "更新二维码成功";
	}

	/**
	 * 移动端登录
	 * 
	 * @throws Exception
	 */
	public void login() throws Exception {
		String result = null;
		if (StringUtil.isEmpty(salesbean)) {
			result = "param is null";
		}
		System.out.println("登录接收:" + salesbean);
		result = salesmanService.login(salesbean);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		System.out.println("登录返回：" + result);
	}

	/**
	 * 移动端注册
	 * 
	 * @throws Exception
	 */
	public void registerByMobile() throws Exception {
		String result = null;
		System.out.println("注册接收：" + salesbean);
		if (salesbean == null) {
			result = "param is null";
		} else {
			result = salesmanService.register(salesbean);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		System.out.println("注册返回：" + result);
	}

	// http://localhost:8080/InsuranceService/back/salesman_updatepassworldtoMd5.action
	public String updatepassworldtoMd5() throws Exception {
		// 测试加密密码
		List<Salesman> slist = salesmanService.find();
		for (int i = 0; i < slist.size(); i++) {
			Salesman sale = slist.get(i);
			// 如果没有密码设置默认密码123456
			if (sale.getPassWord() == null) {
				sale.setPassWord("123456");
			}
			sale.setPassWord(MD5.getMD5(sale.getPassWord().getBytes()));
			salesmanService.saveorUpdate(sale);
		}
		return "密码加密成功";
	}

	/**
	 *@name 移动端充值
	 * 
	 * @throws Exception
	 */
	public void addMoneyByMobile() throws Exception {
		String result = null;
		TypeToken<String[]> type = new TypeToken<String[]>() {
		};
		String[] pw = Return_Code.getGson().fromJson(modpass, type.getType());
		System.out.println("修改密码接收phone：" + pw[0]);
		System.out.println("修改密码接收newpass：" + pw[1]);
		if (modpass == null) {
			result = "param is null";
		} else {
			result = salesmanService.modifyPass(pw);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		System.out.println("修改密码返回：" + result);
	}

	/**
	 * @name 统计一级会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String CountFirstmerber() throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (pagesize == null) {
				pagesize = "10";
			}
			if (pageindex == null) {
				pageindex = "0";
			}

			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			int start = (pageIndex - 1) * pageSize;
			// Salesman sale=salesmanService.findByPhone(phone);
			// 统计一级会员
			long total = salesmanService.getMerberFirstCount(phone);

			List<Salesman> slist = salesmanService.findSalesmanList(new PageBean(pageSize, start), phone);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");
		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
		return null;
	}

	/**
	 *@name  统计二级会员数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String CountSecondmerber() throws Exception {

		JSONObject jsonObject = new JSONObject();
		try {
			if (pagesize == null) {
				pagesize = "10";
			}
			if (pageindex == null) {
				pageindex = "0";
			}
			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			long total = salesmanService.getMerberSecondCount(phone);
			int start = (pageIndex - 1) * pageSize;
			if (start >= total && total != 0) {
				start = start - pageSize;
			}
			List<Salesman> slist = salesmanService.findSalesmanSecondList(new PageBean(pageSize, start), phone);

			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");
		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
		return null;
	}

	/**
	 *@name 统计三级会员数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String CountThreemerber() throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {

			if (pagesize == null) {
				pagesize = "10";
			}
			if (pageindex == null) {
				pageindex = "0";
			}
			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			long total = salesmanService.getMerberThreeCount(phone);
			int start = (pageIndex - 1) * pageSize;
			if (start >= total && total != 0) {
				start = start - pageSize;
			}
			List<Salesman> slist = salesmanService.findSalesmanThreeList(new PageBean(pageSize, start), phone);
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");

		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
		return null;

	}

	/**
	 *@name 统计四级会员数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String CountFourmerber() throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {

			if (pagesize == null) {
				pagesize = "10";
			}
			if (pageindex == null) {
				pageindex = "0";
			}

			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			long total = salesmanService.getMerberFourCount(phone);
			int start = (pageIndex - 1) * pageSize;
			if (start >= total && total != 0) {
				start = start - pageSize;
			}
			List<Salesman> slist = salesmanService.findSalesmanFourList(new PageBean(pageSize, start), phone);
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");

		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
		return null;

	}

	/**
	 * @name 统计五级会员数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String CountFivemerber() throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {

			if (pagesize == null) {
				pagesize = "10";
			}
			if (pageindex == null) {
				pageindex = "0";
			}

			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			long total = salesmanService.getMerberFiveCount(phone);
			int start = (pageIndex - 1) * pageSize;
			if (start >= total && total != 0) {
				start = start - pageSize;
			}
			List<Salesman> slist = salesmanService.findSalesmanFiveList(new PageBean(pageSize, start), phone);
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");

		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
		return null;

	}

	/**
	 *@name 统计会员数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String Countmerber() throws Exception {

		// 获取json信息

		// 一级会员统计
		long VIPONE = salesmanService.getMerberFirstCount(this.getPhone());
		// 二级会员统计
		long VIPTWO = salesmanService.getMerberSecondCount(this.getPhone());
		// 三级会员统计
		long VIPThree = salesmanService.getMerberThreeCount(this.getPhone());
		// 四级会员统计
		long VIPFour = salesmanService.getMerberFourCount(this.getPhone());
		// 五级会员统计
		long VIPFive = salesmanService.getMerberFiveCount(this.getPhone());

		// int vip1 = new Long(VIPONE).intValue();
		// int vip2 = new Long(VIPTWO).intValue();
		long countnum = VIPONE + VIPTWO + VIPThree + VIPFour + VIPFive;
		JSONObject result = new JSONObject();
		result.put("VIPONE", VIPONE);
		result.put("VIPTWO", VIPTWO);
		result.put("VIPThree", VIPThree);
		result.put("VIPFour", VIPFour);
		result.put("VIPFive", VIPFive);
		result.put("countnum", countnum);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		// }
		return null;
	}

	/**
	 * 移动端修改资料
	 * 
	 * @throws Exception
	 */
	public void updateByMobile() throws Exception {
		System.out.println("this is " + this.getClass().getSimpleName() + ".updateByMobile()");
		String result = null;
		System.out.println("修改资料接收：" + salesbean);
		if (salesbean == null) {
			result = "param is null";
		} else {
			Salesman sale = null;
			try {
				sale = Return_Code.getGson().fromJson(salesbean, Salesman.class);

				if (salesmanService.findById(sale.getSid()) == null) {
					result = "user is not exist";
				}

				if (sale.getPassWord() != null && sale.getPassWord().length() < 32) {
					// 修改密码加密
					sale.setPassWord(MD5.getMD5(sale.getPassWord().getBytes()));
				}
				salesmanService.update(sale);
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				result = "param fromJson failure";
			}
		}
		System.out.println("修改资料返回：" + result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}

	// 修改密码接收字段 （phone，newpass）
	private String modpass;

	public String getModpass() {
		return modpass;
	}

	public void setModpass(String modpass) {
		this.modpass = modpass;
	}

	/**
	 * 移动端修改密码
	 * 
	 * @throws Exception
	 */
	public void modifyPass() throws Exception {
		String result = null;
		TypeToken<String[]> type = new TypeToken<String[]>() {
		};
		String[] pw = Return_Code.getGson().fromJson(modpass, type.getType());
		System.out.println("修改密码接收phone：" + pw[0]);
		System.out.println("修改密码接收newpass：" + pw[1]);
		if (modpass == null) {
			result = "param is null";
		} else {
			result = salesmanService.modifyPass(pw);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		System.out.println("修改密码返回：" + result);
	}

	/**
	 * 证件信息
	 * 
	 * @throws Exception
	 */
	public void updateZJ() throws Exception {
		String result = null;
		System.out.println("修改证件信息接收：" + salesbean);
		if (salesbean == null) {
			result = "param is null";
		} else {
			Salesman sale = null;
			try {
				sale = Return_Code.getGson().fromJson(salesbean, Salesman.class);
				int sid = sale.getSid();

				Salesman salesman = salesmanService.findById(sid);
				String dir = "/salesman/" + sid;
				String path = StringUtil.getWebApp() + dir;
				System.out.println("idPicFileName:" + idPicFileName.get(0));
				System.out.println("idPicFileName:" + idPicFileName.get(1));
				if (idPic != null && idPic.size() > 1) {
					// 写到指定的路径中
					File file = new File(path);
					// 如果指定的路径没有就创建
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName1 = DateUtil.GetDateRandom16() + ".jpg";
						FileUtils.copyFile(idPic.get(0), new File(file, fileName1));
						// BufferedImage image = ImageIO.read((ImageInputStream)
						// new FileInputStream(file + path));
						// System.out.println(file + path);
						// fileName1 = DateUtil.GetDateRandom16() + ".jpg";
						// System.out.println(fileName1);
						// ImageUtil.zoom(image, new File(file, fileName1), 320,
						// 480);
						// System.out.println(dir + "/" + fileName1);
						sale.setIdPic1(dir + "/" + fileName1);
						String fileName2 = DateUtil.GetDateRandom16() + ".jpg";
						FileUtils.copyFile(idPic.get(1), new File(file, fileName2));
						System.out.println(dir + "/" + fileName2);
						sale.setIdPic2(dir + "/" + fileName2);
						sale.setLevel(1);
						sale.setRegtime(new Timestamp(System.currentTimeMillis()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (cyPic != null && cyPic.size() > 1) {
					// 写到指定的路径中
					File file = new File(path);
					// 如果指定的路径没有就创建
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName1 = DateUtil.GetDateRandom16() + ".jpg";
						FileUtils.copyFile(cyPic.get(0), new File(file, fileName1));
						System.out.println(dir + "/" + fileName1);
						sale.setCyPic1(dir + "/" + fileName1);
						String fileName2 = DateUtil.GetDateRandom16() + ".jpg";
						FileUtils.copyFile(cyPic.get(1), new File(file, fileName2));
						System.out.println(dir + "/" + fileName2);
						sale.setCyPic2(dir + "/" + fileName2);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (zyPic != null && zyPic.size() > 1) {
					// 写到指定的路径中
					File file = new File(path);
					// 如果指定的路径没有就创建
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName1 = DateUtil.GetDateRandom16() + ".jpg";
						FileUtils.copyFile(zyPic.get(0), new File(file, fileName1));
						System.out.println(dir + "/" + fileName1);
						sale.setZyPic1(dir + "/" + fileName1);
						String fileName2 = DateUtil.GetDateRandom16() + ".jpg";
						FileUtils.copyFile(zyPic.get(1), new File(file, fileName2));
						System.out.println(dir + "/" + fileName2);
						sale.setZyPic2(dir + "/" + fileName2);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				salesman.setIdPic1(sale.getIdPic1());
				salesman.setIdPic2(sale.getIdPic2());
				salesman.setCyPic1(sale.getCyPic1());
				salesman.setCyPic2(sale.getCyPic2());
				salesman.setZyPic1(sale.getZyPic1());
				salesman.setZyPic2(sale.getZyPic2());
				System.err.println(salesman.getIdPic1());
				System.err.println(salesman.getIdPic2());
				System.err.println(salesman.getCyPic1());
				System.err.println(salesman.getCyPic2());
				System.err.println(salesman.getZyPic1());
				System.err.println(salesman.getZyPic2());
				salesmanService.update(salesman);
				result = Return_Code.getGson().toJson(sale);
			} catch (Exception e) {
				e.printStackTrace();
				result = "param fromJson failure";
			}
		}
		System.out.println("修改证件信息返回：" + result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}

	/**
	 * @name 上传更新头像
	 * @case  http://192.168.1.150:8080/InsuranceService/back/salesman_uploadhead.action?salesbean={"sid":"320"}
	 * @throws Exception
	 * 
	 */

	public String uploadhead() throws Exception {
		// 查询产品是否存在
		String result1 = null;
		System.out.println("修改头像信息接收：" + salesbean);
		if (salesbean == null) {
			result1 = "param is null";
		} else {
			Salesman sale = null;
			sale = Return_Code.getGson().fromJson(salesbean, Salesman.class);
			// 图像存放路径
			String dir = "/salesman/" + sale.getSid();
			String path = StringUtil.getWebApp() + dir;

			if (headPic != null) {
				// 写到指定的路径中
				File file = new File(path);
				// 如果指定的路径没有就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					// 获取当前16位随机数为命名参数
					String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(DateUtil.GetDateRandom16());
					FileUtils.copyFile(headPic, new File(file, fileName1));
					// 头像新路径
					String countpath = path + "/" + fileName1;

					PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
					String filename = QRCodeUtil.encode("http://" + propertiesUtil.readValue("serverIP") + ":"
							+ propertiesUtil.readValue("port") + "/InsuranceService/salesman/sale_register.jsp?invate="
							+ sale.getPhone(), countpath, path, true);

					sale.setQrcode(dir + "/" + filename);
					sale.setHeadPic(dir + "/" + fileName1);

				} catch (IOException e) {
					e.printStackTrace();
				}
				sale.setSid(sale.getSid());
				salesmanService.update(sale);
			}
			sale = salesmanService.findById(sale.getSid());
			JSONObject result = new JSONObject();
			result.put("sale", sale);
			result.put("successs", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		}

		return result1;
	}

	/**
	 * 读取string.properties属性
	 * 
	 * @return 服务器 IP 地址
	 */
	public String loadpro() {
		Properties p = new Properties();
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("string.properties");

			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty("serverIP");
	}

	/**
	 * 显示会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showSalesman() throws Exception {
		request.setAttribute("leftPage", "common/salesman_left.jsp");
		request.setAttribute("mainPage", "salesmanManage.jsp");
		return SUCCESS;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	/**
	 * @name 获取数据list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		long total = salesmanService.getSalsmanCount();
		if (offSet >= total && total != 0) {
			offSet = offSet - liMit;
		}
		List<Salesman> slist = salesmanService.find(new PageBean(liMit, offSet), new Object[] {});
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * 添加会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		salesman.setMoney(0.00);
		int sid = salesmanService.insert(salesman);
		String dir = "/salesman/" + sid;
		String path = StringUtil.getWebApp() + dir;
		if (idPic != null && idPic.size() > 1) {
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(idPicFileName.get(0));
				FileUtils.copyFile(idPic.get(0), new File(file, fileName1));
				salesman.setIdPic1(dir + "/" + fileName1);
				String fileName2 = DateUtil.getDateKey() + StringUtil.getSuffix(idPicFileName.get(1));
				FileUtils.copyFile(idPic.get(1), new File(file, fileName2));
				salesman.setIdPic2(dir + "/" + fileName2);
				salesman.setLevel(1);
				salesman.setRegtime(new Timestamp(System.currentTimeMillis()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (cyPic != null && cyPic.size() > 1) {
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(cyPicFileName.get(0));
				FileUtils.copyFile(cyPic.get(0), new File(file, fileName1));
				salesman.setCyPic1(dir + "/" + fileName1);
				String fileName2 = DateUtil.getDateKey() + StringUtil.getSuffix(cyPicFileName.get(1));
				FileUtils.copyFile(cyPic.get(1), new File(file, fileName2));
				salesman.setCyPic2(dir + "/" + fileName2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (zyPic != null && zyPic.size() > 1) {
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(zyPicFileName.get(0));
				FileUtils.copyFile(zyPic.get(0), new File(file, fileName1));
				salesman.setZyPic1(dir + "/" + fileName1);
				String fileName2 = DateUtil.getDateKey() + StringUtil.getSuffix(zyPicFileName.get(0));
				FileUtils.copyFile(zyPic.get(1), new File(file, fileName2));
				salesman.setZyPic2(dir + "/" + fileName2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		salesmanService.update(salesman);

		// 完善注册信息
		perfectRegistration(salesman);

		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * @name  注册会员
	 * @return
	 * @throws Exception
	 */
	public String registerSalesman() throws Exception {
		salesman.setMoney(0.00);
		salesman.setPassWord(MD5.getMD5(salesman.getPassWord().getBytes()));
		int sid = salesmanService.insert(salesman);
		String dir = "/salesman/" + sid;
		String path = StringUtil.getWebApp() + "/InsuranceData" + dir;
		if (StringUtil.isNotEmpty(invate)) {
			Salesman sinvate = salesmanService.findByPhone(invate);
			if (sinvate != null) {
				sinvate.setInvateNum(sinvate.getInvateNum() + 1);
				salesman.setInvate(invate);
				salesmanService.update(sinvate);
			}
		}
		if (idPic != null) {
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(idPicFileName.get(0));
				FileUtils.copyFile(idPic.get(0), new File(file, fileName1));
				salesman.setIdPic1(dir + "/" + fileName1);
				String fileName2 = DateUtil.getDateKey() + StringUtil.getSuffix(idPicFileName.get(1));
				FileUtils.copyFile(idPic.get(1), new File(file, fileName2));
				salesman.setIdPic2(dir + "/" + fileName2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (cyPic != null) {
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(cyPicFileName.get(0));
				FileUtils.copyFile(cyPic.get(0), new File(file, fileName1));
				salesman.setCyPic1(dir + "/" + fileName1);
				String fileName2 = DateUtil.getDateKey() + StringUtil.getSuffix(cyPicFileName.get(1));
				FileUtils.copyFile(cyPic.get(1), new File(file, fileName2));
				salesman.setCyPic2(dir + "/" + fileName2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (zyPic != null) {
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(zyPicFileName.get(0));
				FileUtils.copyFile(zyPic.get(0), new File(file, fileName1));
				salesman.setZyPic1(dir + "/" + fileName1);
				String fileName2 = DateUtil.getDateKey() + StringUtil.getSuffix(zyPicFileName.get(1));
				FileUtils.copyFile(zyPic.get(1), new File(file, fileName2));
				salesman.setZyPic2(dir + "/" + fileName2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		salesman.setStatus("reday");
		salesman.setLevel(1);
		salesman.setRegtime(new Timestamp(System.currentTimeMillis()));
		salesmanService.update(salesman);

		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String serverip = propertiesUtil.readValue("serverIP");
		String loginpath = propertiesUtil.readValue("logoPath");
		String filename = QRCodeUtil.encode("http://" + serverip
				+ ":8080/InsuranceService/salesman/sale_register.jsp?invate=" + salesman.getPhone(), loginpath, path,
				false);
		salesman.setQrcode(dir + "/" + filename);

		salesmanService.update(salesman);

		// 完善注册信息
		this.perfectRegistration(salesman);
		return "register";
	}

	/**
	 * @name 完善注册信息
	 * @param salesman
	 */
	private void perfectRegistration(Salesman sale) {
		// 判断传入会员信息内邀请人字段是否为空
		if (StringUtil.isNotEmpty(sale.getInvate())) {

			// 根据邀请人手机号码查询"邀请人"信息
			Salesman sinvate = salesmanService.findByPhone(sale.getInvate());
			// 判断邀请人是否存在
			if (sinvate != null) {
				// 邀请人1的邀请人数加1
				sinvate.setInvateNum(sinvate.getInvateNum() + 1);
				// 更新邀请人的信息
				salesmanService.update(sinvate);
				// 设置注册会员的邀请人
				// sale.setInvate(sale.getInvate());
				// 根据邀请人的邀请人查询邀请人2的信息
				// 判断"邀请人1"的邀请人字段是否为空
				if (StringUtil.isNotEmpty(sinvate.getInvate())) {
					// 根据邀请人1的邀请人字段获取邀请人2的信息
					Salesman sinvate2 = salesmanService.findByPhone(sinvate.getInvate());
					// 判断邀请人2是否存在
					if (sinvate2 != null) {
						// 邀请人2的邀请人数加1
						sinvate2.setInvateNum(sinvate2.getInvateNum() + 1);
						// 更新邀请人2的信息
						salesmanService.update(sinvate2);
						// 设置注册会员的邀请人2
						sale.setInvate2(sinvate2.getPhone());

						// 根据"邀请人2"的邀请人查询"邀请人3"的信息
						// 判断"邀请人2"的邀请人字段是否为空
						if (StringUtil.isNotEmpty(sinvate2.getInvate())) {
							// 根据"邀请人2"的邀请人字段获取"邀请人3"的信息
							Salesman sinvate3 = salesmanService.findByPhone(sinvate2.getInvate());
							if (sinvate3 != null) {
								// "邀请人3"的邀请人数加1
								sinvate3.setInvateNum(sinvate3.getInvateNum() + 1);
								// 更新邀请人3的信息
								salesmanService.update(sinvate3);
								// 设置注册会员的邀请人3
								sale.setInvate3(sinvate3.getPhone());

								// 根据"邀请人3"的邀请人查询"邀请人4"的信息
								// 判断"邀请人3"的邀请人字段是否为空
								if (StringUtil.isNotEmpty(sinvate3.getInvate())) {
									// 根据"邀请人3"的邀请人字段获取"邀请人4"的信息
									Salesman sinvate4 = salesmanService.findByPhone(sinvate3.getInvate());
									if (sinvate4 != null) {
										// "邀请人4"的邀请人数加1
										sinvate4.setInvateNum(sinvate4.getInvateNum() + 1);
										// 更新邀请人4的信息
										salesmanService.update(sinvate4);
										// 设置注册会员的邀请人4
										sale.setInvate4(sinvate4.getPhone());

										// 根据"邀请人4"的邀请人查询"邀请人5"的信息
										// 判断"邀请人4"的邀请人字段是否为空
										if (StringUtil.isNotEmpty(sinvate4.getInvate())) {
											// 根据"邀请人4"的邀请人字段获取"邀请人5"的信息
											Salesman sinvate5 = salesmanService.findByPhone(sinvate4.getInvate());
											if (sinvate5 != null) {
												// "邀请人4"的邀请人数加1
												sinvate5.setInvateNum(sinvate5.getInvateNum() + 1);
												// 更新邀请人4的信息
												salesmanService.update(sinvate5);
												// 设置注册会员的邀请人5
												sale.setInvate5(sinvate5.getPhone());

												// 根据"邀请人5"的邀请人查询"邀请人6"的信息
												// 判断"邀请人5"的邀请人字段是否为空
												if (StringUtil.isNotEmpty(sinvate5.getInvate())) {
													// 根据"邀请人5"的邀请人字段获取"邀请人6"的信息
													Salesman sinvate6 = salesmanService.findByPhone(sinvate5
															.getInvate());
													if (sinvate6 != null) {
														// "邀请人6"的邀请人数加1
														sinvate6.setInvateNum(sinvate6.getInvateNum() + 1);
														// 更新邀请人6的信息
														salesmanService.update(sinvate6);
														// 设置注册会员的邀请人6
														sale.setInvate6(sinvate6.getPhone());
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		// 更新用户信息
		salesmanService.update(sale);
	}

	/**
	 * 更新会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSalesman() throws Exception {
		if (idPic != null) {
			String dir = "/salesman/" + salesman.getSid();
			String path = ServletActionContext.getServletContext().getRealPath(dir);
			// 写到指定的路径中
			File file = new File(path);
			// 如果指定的路径没有就创建
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				String fileName1 = DateUtil.getDateKey();
				FileUtils.copyFile(idPic.get(0), new File(file, fileName1));
				salesman.setIdPic1(dir + "/" + fileName1);
				if (idPic.size() > 1) {
					String fileName2 = DateUtil.getDateKey();
					FileUtils.copyFile(idPic.get(1), new File(file, fileName2));
					salesman.setIdPic2(dir + "/" + fileName2);
				}
				salesmanService.update(salesman);
				return showSalesman();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (StringUtil.isEmpty(salesman.getIdPic1())) {
			salesman.setIdPic1("");
		} else if (StringUtil.isEmpty(salesman.getIdPic2())) {
			salesman.setIdPic2("");
		}

		salesmanService.update(salesman);
		return "show";

	}

	/**
	 * @name 后台-删除会员
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String ids1 = "";
		JSONObject result = new JSONObject();
		if (ids.length == 1) {
			ids1 = ids[0].replace("[", "");
			ids1 = ids1.replace("]", "");
			ids = ids1.split(",");
		}
		if (ids.length == 1) {
			salesmanService.deletes(ids[0]);
		} else if (ids.length > 1) {
			salesmanService.deletes(ids1);
		} else {
			result.put("success", false);
			result.put("msg", "用户ID不能为空");
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * 检测手机号是否已被注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public String existSalesmanWithUserPhone() throws Exception {
		JSONObject result = new JSONObject();
		if (salesmanService.findByPhone(phone) != null) {
			result.put("exit", true);
		} else {
			result.put("exit", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

}
