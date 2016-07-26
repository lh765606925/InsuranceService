package com.sino.dms.webservice.test;

import java.rmi.RemoteException;

import com.sino.dms.webservice.AsyncProductData;
import com.sino.dms.webservice.AsyncProductDataResponse;
import com.sino.dms.webservice.PlantformserviceStub;
import com.sino.dms.webservice.Sum;
import com.sino.dms.webservice.SumResponse;
import com.sino.dms.webservice.TestResponse;
import com.sino.dms.webservice.Testxml;
import com.sino.dms.webservice.TestxmlResponse;

public class test {

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		PlantformserviceStub plantformservicestub = new PlantformserviceStub();
		TestResponse testresponse = plantformservicestub.test();
		System.out.println("" + testresponse.get_return());

		Sum sum = new Sum();
		sum.setSum(5);
		SumResponse sumresponse = plantformservicestub.sum(sum);
		System.out.println("sum:" + sumresponse.get_return());

		String str = ""
				+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<personnel>"
				+ "<person id=\"Big.Boss\" age=\"1\" x=\"4\" d=\"1999-05-03\" dt=\"1999-05-31T13:20:00-05:00\">"
				+ "<name><family>Boss</family> <given>Big</given></name>"
				+ "<email>chief@foo.com</email>"
				+ "<link subordinates=\"one.worker two.worker three.worker four.worker five.worker\"/>"
				+ "<note>Some text...</note>" + "<salary>100</salary>"
				+ "</person>" + "</personnel>";

		Testxml testxml = new Testxml();
		testxml.setXmlstr(str);
		TestxmlResponse testxmlresponse = plantformservicestub.testxml(testxml);
		System.out.println("第三方客户端调用 ：：：testxml+++" + testxmlresponse.get_return());
		
		/**
		 * 异步测试
		 */
		AsyncProductData  asyncproductdata=new AsyncProductData();
		asyncproductdata.setJsonstr("test");
		AsyncProductDataResponse  asyncproductdataresponse=plantformservicestub.asyncProductData(asyncproductdata);
		
		System.out.println("第三方客户端调用：TESTASYNCRODUCTDATA++"+asyncproductdataresponse.get_return());
		
	}
	
	/* private class AsynCallback extends HelloCallbackHandler {  
	       @Override 
	       public void receiveResulthello(HelloResponse result) {  
         System.out.println(result.local_return);  
     }  
	    } */ 


}
