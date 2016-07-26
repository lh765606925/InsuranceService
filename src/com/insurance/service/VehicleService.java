package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.model.VehicleModel;
import com.insurance.model.Vehicle;

/**
 * 车辆Service 
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月15日
 */
public interface VehicleService extends BaseService<Vehicle, String> {

	public List<Vehicle> searchVehicleModel(String jsonsString);

	public String addVehicle(String vehicleString);

	public void deletes(String ids);

	public Vehicle findById(int id) throws Exception;

	public String updateByMobile(String vehicleString);

	public long getVehicleCount();

	public List<Vehicle> find(PageBean pageBean, Object[] objects);


}
