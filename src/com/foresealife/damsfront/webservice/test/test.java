package com.foresealife.damsfront.webservice.test;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.foresealife.damsfront.webservice.AsyncOrderData;
import com.foresealife.damsfront.webservice.AsyncOrderDataResponse;
import com.foresealife.damsfront.webservice.PlantformserviceStub;
import com.insurance.util.MD5Utils;
import com.insurance.util.SundryTest;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		add();
	}

	public static void add() {
		try {

			String testjson2 = "{'policyInfo': {'amount': '0.01','applyCode': '9702004173','channelId': '0e3c5166ac54470cb1ba7b8d6d3ec126','endDate': '2015-07-16','salesCode': '300000030','flowCode': '04','insuredAndBeneficiaryList': [{'insuredRule': {'benefits': {},'choiceDuty': {},'outPut': {}},'policyInsured': {'birthdate': '2004-06-02','certEndDate': '','certStartDate': '','certValidFlag': '2','certificateNo': '55566','certificateType': '201','chiefInsuredRelation': 'null','city': '','count': 1,'county': 'CHN','detailAddress': '','email': '327127640@qq.com','holderRelation': '301','id': '"+SundryTest.uuid()+"','isChiefInsured': 'null','isLegal': 'null','moblie': '13268527439','name': '是不是','nationality': 'null','policyId': '"
					+ SundryTest.uuid()
					+ "','premium': '0.01','profession': 'null','province': '','residentCity': '440300','residentProvince': '440000','sex': '2','sort': 'null','supplierBranch': 'null','telephone': 'null','zipNo': '100110'}}],'insuredSelect': 'null','isLegal': 'null','orderCharge': {'buyerAccountUsername': '','payAmount': '','payBusinessCode': '','payCode': '','payMethod': '','payer': '','sellerAccountUsername': ''},'orderCode': '9702004174','orderId': '"+SundryTest.uuid()+"','policyHolder': {'birthdate': '2005-06-02','certEndDate': '','certStartDate': '','certValidFlag': '2','certificateNo': '123456','certificateType': '201','city': '','county': 'CHN','detailAddress': '','email': 'hh_laohu@163.com','id': '"
					+ SundryTest.uuid()
					+ "','moblie': '13268527439','name': '好困惑','nationality': 'null','policyId': '"
					+ SundryTest.uuid()
					+ "','profession': 'null','province': '','residentCity': '那那样','residentProvince': '是','sex': '1','supplierBranch': 'null','telephone': 'null','zipNo': '100110'},'policyId': '"
					+ SundryTest.uuid()
					+ "','productId': '292ea6c4ea86419799e3e40ce6d68a22','startDate': '2015-06-28'}}";

			System.out.println(testjson2);
			testjson2="{'policyInfo': {'amount': '100.0','applyCode': '2015063062290393','channelId': '0e3c5166ac54470cb1ba7b8d6d3ec126','endDate': '2016-06-29','salesCode': '300000030','flowCode': '04','insuredAndBeneficiaryList': [{'insuredRule': {'benefits': {},'choiceDuty': {},'outPut': {}},'policyInsured': {'birthdate': '1989-02-05','certEndDate': '','certStartDate': '','certValidFlag': '2','certificateNo': '43092219890205423x','certificateType': '201','chiefInsuredRelation': 'null','city': '','count': 1,'county': 'CHN','detailAddress': '','email': 'hh_laohu@163.com','holderRelation': '301','id': '22211153C5DF491EB97F3156ED936C6D','isChiefInsured': 'null','isLegal': 'null','moblie': '13268527439','name': '胡志宏','nationality': 'null','policyId': '3C133044B223470DB470C8E2F8EFEB17','premium': '','profession': 'null','province': '','residentCity': '440300','residentProvince': '440000','sex': '1','sort': 'null','supplierBranch': 'null','telephone': 'null','zipNo': '100110'}}],'insuredSelect': 'null','isLegal': 'null','orderCharge': {'buyerAccountUsername': '','payAmount': '','payBusinessCode': '','payCode': '','payMethod': '','payer': '','sellerAccountUsername': ''},'orderCode': '9702004173','orderId': 'A645E78AD5344618843C7C854A6BADA6','policyHolder': {'birthdate': '1989-02-05','certEndDate': '','certStartDate': '','certValidFlag': '2','certificateNo': '43092219890205423x','certificateType': '201','city': '','county': 'CHN','detailAddress': '','email': 'hh_laohu@163.com','id': '7E4E62CACAD84289BFCC6AA24932CF81','moblie': '13268527439','name': '胡志宏','nationality': 'null','policyId': '15B22DF6960442B2AD0882251B501CA5','profession': 'null','province': '','residentCity': '深圳','residentProvince': '广东','sex': '1','supplierBranch': 'null','telephone': 'null','zipNo': '100110'},'policyId': '0F1B3C26F7374F66AE9AD0FE56EE9FB4','productId': 'd76a9ecf50c6487aad051550cae23817','startDate': '2015-07-01'}}";
			System.out.println(testjson2);
			// UTF-8传输
			try {
				testjson2 = new String(testjson2.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			/**
			 * 功能方法测试
			 */
			PlantformserviceStub plantformservicestub = new PlantformserviceStub();
			AsyncOrderData asyncorderdata = new AsyncOrderData();

			// MD5
			MD5Utils md5utils = new MD5Utils();
			String md5str = "";
			try {
				md5str = md5utils.createMD5(testjson2 + "qinghaimd5");
			} catch (Exception e) {
				e.printStackTrace();
			}
			testjson2 = "JSON||04||" + testjson2;
			asyncorderdata.setJsonstr(testjson2);
			asyncorderdata.setMd5Salt(md5str);

			System.out.println(testjson2);
			System.out.println(md5str);

			AsyncOrderDataResponse asyncorderdataresponse;
			asyncorderdataresponse = plantformservicestub.asyncOrderData(asyncorderdata);
			System.out.println("第三方客户端测试:+++++" + asyncorderdataresponse.get_return());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
