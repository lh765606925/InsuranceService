package com.insurance.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.dao.VehicleDao;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.model.Vehicle;
import com.insurance.model.VehicleModel;
import com.insurance.service.VehicleService;
import com.insurance.util.QRCodeUtil;
import com.insurance.util.Return_Code;
import com.insurance.util.StringUtil;

@Service("VehicleService")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, String> implements VehicleService {
	@Resource
	private BaseDao<Vehicle, String> baseDao;
	@Resource
	private VehicleDao vehicleDao;

	private String table = "t_vehicle";

	/**
	 * 模糊查询车型信息
	 */
	@Override
	public List<Vehicle> searchVehicleModel(String jsonsString) {
		System.out.println("接收到的jsonString:" + jsonsString);
		Vehicle vehicleModel = Return_Code.getGson().fromJson(jsonsString, Vehicle.class);
		// if(vehicleModel.)
		return null;
	}

	@Override
	public String addVehicle(String vehicleString) {
		Vehicle vehicle = null;
		if (vehicleString == null) {
			return "param is null";
		}
		try {
			vehicle = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "param fromJson failure";
		}
		// if (isExit("phone", vehicle.getAutoModelCode())) {
		// return "该手机号已被注册";
		// }
		baseDao.saveorUpdate(vehicle);
		// InputStream inputStream =
		// this.getClass().getClassLoader().getResourceAsStream("config.properties");
		// Properties p = new Properties();
		// try {
		// p.load(inputStream);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		//
		// String filename = QRCodeUtil.encode("http://" +
		// p.getProperty("serverIP")
		// + ":8080/InsuranceService/salesman/sale_register.jsp?invate=" +
		// sale.getPhone(),
		// p.getProperty("logoPath"), path, false);
		//
		// baseDao.saveorUpdate(sale);

		return "车辆添加成功";
	}

	public boolean isExit(String str, String strval) {
		return baseDao.isExit(table, str, strval);
	}

	// 批量删除
	@Override
	public void deletes(String sids) {
		baseDao.executeHql("delete from Vehicle s where s.sid in (" + sids + ")");
	}

	// 根据ID查询车辆信息
	@Override
	public Vehicle findById(int id) throws Exception {
		return (Vehicle) baseDao.get(Vehicle.class, id);
	}

	@Override
	public String updateByMobile(String vehicleString) {
		try {
			Vehicle vc = Return_Code.getGson().fromJson(vehicleString, Vehicle.class);
			update(vc);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "param fromJson failure";
		}
	}

	@Override
	public long getVehicleCount() {
		return baseDao.count("select count(*) from Vehicle");
	}

	
	@Override
	public List<Vehicle> find(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from Vehicle", pageBean, param);
		}
		return baseDao.find("from Vehicle", param);
	}
}
