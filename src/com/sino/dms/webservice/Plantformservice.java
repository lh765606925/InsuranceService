

/**
 * Plantformservice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.sino.dms.webservice;

    /*
     *  Plantformservice java interface
     */

    public interface Plantformservice {
          

        /**
          * Auto generated method signature
          * 
                    * @param checkProductData2
                
         */

         
                     public com.sino.dms.webservice.CheckProductDataResponse checkProductData(

                        com.sino.dms.webservice.CheckProductData checkProductData2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param checkProductData2
            
          */
        public void startcheckProductData(

            com.sino.dms.webservice.CheckProductData checkProductData2,

            final com.sino.dms.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param sum4
                
         */

         
                     public com.sino.dms.webservice.SumResponse sum(

                        com.sino.dms.webservice.Sum sum4)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param sum4
            
          */
        public void startsum(

            com.sino.dms.webservice.Sum sum4,

            final com.sino.dms.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
         */

         
                     public com.sino.dms.webservice.TestResponse test(

                        )
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
          */
        public void starttest(

            

            final com.sino.dms.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param asyncProductData8
                
         */

         
                     public com.sino.dms.webservice.AsyncProductDataResponse asyncProductData(

                        com.sino.dms.webservice.AsyncProductData asyncProductData8)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param asyncProductData8
            
          */
        public void startasyncProductData(

            com.sino.dms.webservice.AsyncProductData asyncProductData8,

            final com.sino.dms.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
         */

         
                     public com.sino.dms.webservice.GetClientIpAxisResponse getClientIpAxis(

                        )
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
          */
        public void startgetClientIpAxis(

            

            final com.sino.dms.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param testxml12
                
         */

         
                     public com.sino.dms.webservice.TestxmlResponse testxml(

                        com.sino.dms.webservice.Testxml testxml12)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param testxml12
            
          */
        public void starttestxml(

            com.sino.dms.webservice.Testxml testxml12,

            final com.sino.dms.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    