

/**
 * Plantformservice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.foresealife.damsfront.webservice;

    /*
     *  Plantformservice java interface
     */

    public interface Plantformservice {
          

        /**
          * Auto generated method signature
          * 
                    * @param asyncOrderData0
                
         */

         
                     public com.foresealife.damsfront.webservice.AsyncOrderDataResponse asyncOrderData(

                        com.foresealife.damsfront.webservice.AsyncOrderData asyncOrderData0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param asyncOrderData0
            
          */
        public void startasyncOrderData(

            com.foresealife.damsfront.webservice.AsyncOrderData asyncOrderData0,

            final com.foresealife.damsfront.webservice.PlantformserviceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    