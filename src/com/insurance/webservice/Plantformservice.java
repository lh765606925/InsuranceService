package com.insurance.webservice;

/*
 *  Plantformservice java interface
 */

public interface Plantformservice {

	/**
	 * Auto generated method signature
	 * 
	 * @param checkProductData2
	 */

	public CheckProductDataResponse checkProductData(

	CheckProductData checkProductData2) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param checkProductData2
	 */
	public void startcheckProductData(

	CheckProductData checkProductData2,

	final PlantformserviceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param sum4
	 */

	public SumResponse sum(

	Sum sum4) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param sum4
	 */
	public void startsum(

	Sum sum4,

	final PlantformserviceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 */

	public TestResponse test(

	) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 */
	public void starttest(

	final PlantformserviceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param asyncProductData8
	 */

	public AsyncProductDataResponse asyncProductData(

	AsyncProductData asyncProductData8) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param asyncOrderData0
	 */

	public AsyncOrderDataResponse asyncOrderData(AsyncOrderData asyncOrderData0) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param asyncProductData8
	 */
	public void startasyncProductData(

	AsyncProductData asyncProductData8,

	final PlantformserviceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 */

	public GetClientIpAxisResponse getClientIpAxis(

	) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 */
	public void startgetClientIpAxis(

	final PlantformserviceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param testxml12
	 */

	public TestxmlResponse testxml(

	Testxml testxml12) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param testxml12
	 */
	public void starttestxml(

	Testxml testxml12,

	final PlantformserviceCallbackHandler callback)

	throws java.rmi.RemoteException;

	//
}
