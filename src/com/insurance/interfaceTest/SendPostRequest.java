package com.insurance.interfaceTest;

import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.File;  
import java.io.FileReader;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.net.URLEncoder;  
public class SendPostRequest {  
    static String sessionId = "";  
    public static void main(String[] args) throws Exception {  
        URL url = new URL("http://localhost:8080/GetRequest/");  
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
        connection.setDoOutput(true);  
        // Read from the connection. Default is true.  
        connection.setDoInput(true);  
        // Set the post method. Default is GET  
        connection.setRequestMethod("POST");  
        // Post cannot use caches  
        // Post 请求不能使用缓存  
        connection.setUseCaches(false);  
        // This method takes effects to  
        // every instances of this class.  
        // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。  
        // connection.setFollowRedirects(true);  
        // This methods only  
        // takes effacts to this  
        // instance.  
        // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数  
        connection.setInstanceFollowRedirects(false);  
        // Set the content type to urlencoded,  
        // because we will write  
        // some URL-encoded content to the  
        // connection. Settings above must be set before connect!  
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的  
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode  
        // 进行编码  
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，  
        // 要注意的是connection.getOutputStream会隐含的进行connect。  
        connection.connect();  
        Long sendTime = System.currentTimeMillis();  
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());  
        // 要传的参数  
        String content = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("XXX", "UTF-8");  
        content += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("XXXX", "UTF-8");  
        content += "&" + URLEncoder.encode("sendTime", "UTF-8") + "=" + URLEncoder.encode(sendTime.toString(), "UTF-8");  
        //读海量文件  
        StringBuilder postStrBuf = new StringBuilder();  
        for (int i = 0; i < 1; i++) {  
            File file = new File("C:/Users/ASUS/Desktop/111.xml");  
            BufferedReader reader = null;  
            String postStr = null;  
            reader = new BufferedReader(new FileReader(file));  
            while ((postStr = reader.readLine()) != null) {  
                postStrBuf.append(postStr);  
            }  
        }  
        content = content + "&" + URLEncoder.encode("file", "UTF-8") + "=" + URLEncoder.encode(postStrBuf.toString(), "UTF-8");  
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
        out.writeBytes(content);  
        out.flush();  
        out.close(); // flush and close  
        //Get Session ID  
        String key = "";  
        if (connection != null) {  
            for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {  
                if (key.equalsIgnoreCase("set-cookie")) {  
                    sessionId = connection.getHeaderField(key);  
                    sessionId = sessionId.substring(0, sessionId.indexOf(";"));  
                }  
            }  
        }  
        connection.disconnect();  
    }  
}  