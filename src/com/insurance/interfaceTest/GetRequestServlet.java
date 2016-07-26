package com.insurance.interfaceTest;

import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
/** 
 * Servlet implementation class GetRequestServlet 
 */  
public class GetRequestServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    //private static final Logger log = Logger.getLogger(GetRequestServlet.class);  
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public GetRequestServlet() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String file = request.getParameter("file");  
        String sendTimeStr = request.getParameter("sendTime");  
        long sendTime = Long.valueOf(sendTimeStr);  
        response.setContentType("text/html;charset=utf-8");  
        System.out.println(file);  
        System.out.println("当前从发送到接受所用时间为：" + (System.currentTimeMillis() - sendTime));  
        //PrintWriter out = response.getWriter();  
        //out.println("hello world!" + file);  
    }  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
    }  
}  