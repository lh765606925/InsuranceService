package com.insurance.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dao.VehicleDao;
import com.insurance.model.Vehicle;

/**
 * 车辆DaoImpl
 * 
 * @author huzhihong
 *
 * 创建日期：2015年6月15日
 */
@Repository("VehicleDao")
@Transactional
public class VehicleDaoImpl extends BaseDaoImpl<Vehicle, String>  implements VehicleDao {

}
