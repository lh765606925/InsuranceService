package com.insurance.util;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * 
 * Title: JSON-XML转换工具
 * desc:
 * Copyright: Copyright(c)Gb 2012
 * 
 * @author http://www.ij2ee.com
 * @time 上午8:20:40
 * @version 1.0
 * @since
 */
public class XmlJSON {
	private static final String STR_JSON = "{\"name\":\"Michael\",\"address\":{\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";
	@SuppressWarnings("unused")
	private static final String xml="<?xml version=\"1.0\" encoding=\"GBK\"?><Packet><Head><RequestType>V010</RequestType><ResponseCode>1</ResponseCode><ErrorCode>0000</ErrorCode><ErrorMessage>成功</ErrorMessage><loginMode>local</loginMode></Head><Body><VehicleModelList><VehicleModel><AutoModelChnName>斯达-斯太尔ZZ1234FC71</AutoModelChnName><PurchasePrice>230000</PurchasePrice><PurchasePriceTax>249700</PurchasePriceTax><VehSeries>飞龙</VehSeries><Maker>中国重型汽车集团公司</Maker><KindredPrice>0</KindredPrice><KindredPriceTax>0</KindredPriceTax><ImportFlag>0</ImportFlag><AirbagCount>0</AirbagCount><AutoModelCode>SDBDVD0002</AutoModelCode><VehicleBrand>中国重汽</VehicleBrand><VehicleSeats>2</VehicleSeats><ExhaustCapability>0</ExhaustCapability><Weight>10.520</Weight><VehicleTonnages>19.35</VehicleTonnages><PriceType>04</PriceType></VehicleModel><VehicleModel><AutoModelChnName>斯达-斯太尔ZZ1234FC69</AutoModelChnName><PurchasePrice>210000</PurchasePrice><PurchasePriceTax>227900</PurchasePriceTax><TransmissionType>手动档</TransmissionType><VehSeries>飞龙</VehSeries><Maker>中国重型汽车集团公司</Maker><KindredPrice>0</KindredPrice><KindredPriceTax>0</KindredPriceTax><ImportFlag>0</ImportFlag><AirbagCount>0</AirbagCount><AutoModelCode>SDBDVD0003</AutoModelCode><VehicleBrand>中国重汽</VehicleBrand><VehicleSeats>3</VehicleSeats><ExhaustCapability>0</ExhaustCapability><Weight>9.630</Weight><VehicleTonnages>20.24</VehicleTonnages><PriceType>04</PriceType></VehicleModel><VehicleModel><AutoModelChnName>斯达-斯太尔ZZ1234FC70</AutoModelChnName><PurchasePrice>220000</PurchasePrice><PurchasePriceTax>238800</PurchasePriceTax><TransmissionType>手动档</TransmissionType><VehSeries>飞龙</VehSeries><Maker>中国重型汽车集团公司</Maker><KindredPrice>0</KindredPrice><KindredPriceTax>0</KindredPriceTax><ImportFlag>0</ImportFlag><AirbagCount>0</AirbagCount><AutoModelCode>SDBDVD0005</AutoModelCode><VehicleBrand>中国重汽</VehicleBrand><VehicleSeats>2</VehicleSeats><ExhaustCapability>0</ExhaustCapability><Weight>10.230</Weight><VehicleTonnages>19.64</VehicleTonnages><Remark>发动机型号:重汽WD615.61J;底盘型号:斯达-斯太尔ZZ1234FC70</Remark><PriceType>04</PriceType></VehicleModel></VehicleModelList></Body></Packet>";
	
	public static String xml2JSON(String xml) {
		return new XMLSerializer().read(xml).toString();
	}

	public static String json2XML(String json) {
		JSONObject jobj = JSONObject.fromObject(json);
		String xml = new XMLSerializer().write(jobj);
		return xml;
	}

	public static void main(String[] args) {
		String xml = json2XML(STR_JSON);
		System.out.println("xml = " + xml);
		String json = xml2JSON(xml);
		System.out.println("json=" + json);
	}
}