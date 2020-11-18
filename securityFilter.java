package com.bookstore.controller.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.model.dao.user.UserDao;


@WebFilter("/*.do")
public class securityFilter implements Filter {
	
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}


   

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	 HttpServletRequest req=(HttpServletRequest) request;
	 HttpServletResponse res=(HttpServletResponse) response;

	 
	 boolean isValid=false;
	 HttpSession httpsession=((HttpServletRequest) request).getSession(false);
	 if(httpsession!=null) {
		 UserDao user=(UserDao) httpsession.getAttribute("user");
		 if(user!=null) {
			 isValid=true;
		 }
	 }
	 if(isValid) {
		 chain.doFilter(request, response);
	 }
	 else {
		 res.sendRedirect("login.jsp?message=login please");
	 }
	
	}
	
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
}
