package com.insurance.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest requestR = (HttpServletRequest) request;
		HttpServletResponse responseR = (HttpServletResponse) response;
		HttpSession session = requestR.getSession();
		String url = requestR.getServletPath();
		String contextPath = requestR.getContextPath();
		if (!(url.endsWith("/login.jsp")) && !(url.endsWith("login.action")) && !(url.startsWith("/salesman"))
				&& !(url.endsWith("registerSalesman.action")) && !(url.endsWith("salesman_login.action"))
				&& !(url.contains("registerByMobile")) && !(url.contains(WapConstant.AccountRecord))
				&& !(url.contains(WapConstant.ADDBANKString)) && !(url.contains(WapConstant.addHead))
				&& !(url.contains(WapConstant.deposit_addDepositByMobile))
				&& !(url.contains(WapConstant.iNSERTIDString)) && !(url.contains(WapConstant.INSERTString))
				&& !(url.contains(WapConstant.LOGINString)) && !(url.contains(WapConstant.product_cxjiekou))
				&& !(url.contains(WapConstant.product_insert)) && !(url.contains(WapConstant.product_priceForPingAn))
				&& !(url.contains(WapConstant.product_rsjiekou)) && !(url.contains(WapConstant.product_send))
				&& !(url.contains(WapConstant.product_xx)) && !(url.contains(WapConstant.URLIMAGEString))
				&& !(url.contains(WapConstant.UPDATEString)) && !(url.contains(WapConstant.UPDATEPASS))
				&& !(url.contains(WapConstant.UPDATEHEADIMAGE)) && !(url.contains(WapConstant.product_zhjiekou))
				&& !(url.contains(WapConstant.READ_VERSION)) && !(url.contains(WapConstant.TOTALMoneyString))
				&& !(url.endsWith("existSalesmanWithUserPhone.action"))) {
//			
//			if(url.contains(WapConstant.UPDATE_VERSION)){
//				if (session.getAttribute("currentSalesman") == null) {
//					// 转入管理员登陆页面
//					responseR.sendRedirect(contextPath + "/background/login.jsp");
//					return;
//				}
//			}
			
//			if (session.getAttribute("currentManager") == null) {
//				// 转入管理员登陆页面
//				responseR.sendRedirect(contextPath + "/background/login.jsp");
//				return;
//			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
