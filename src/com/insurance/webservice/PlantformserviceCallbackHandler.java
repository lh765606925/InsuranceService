
/**
 * PlantformserviceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.insurance.webservice;

    /**
     *  PlantformserviceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class PlantformserviceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public PlantformserviceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public PlantformserviceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for checkProductData method
            * override this method for handling normal response from checkProductData operation
            */
           public void receiveResultcheckProductData(
                    CheckProductDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkProductData operation
           */
            public void receiveErrorcheckProductData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sum method
            * override this method for handling normal response from sum operation
            */
           public void receiveResultsum(
                    SumResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sum operation
           */
            public void receiveErrorsum(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for test method
            * override this method for handling normal response from test operation
            */
           public void receiveResulttest(
                    TestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from test operation
           */
            public void receiveErrortest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for asyncProductData method
            * override this method for handling normal response from asyncProductData operation
            */
           public void receiveResultasyncProductData(
                    AsyncProductDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from asyncProductData operation
           */
            public void receiveErrorasyncProductData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getClientIpAxis method
            * override this method for handling normal response from getClientIpAxis operation
            */
           public void receiveResultgetClientIpAxis(
                    GetClientIpAxisResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getClientIpAxis operation
           */
            public void receiveErrorgetClientIpAxis(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for testxml method
            * override this method for handling normal response from testxml operation
            */
           public void receiveResulttestxml(
                    TestxmlResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from testxml operation
           */
            public void receiveErrortestxml(java.lang.Exception e) {
            }
                


    }
    