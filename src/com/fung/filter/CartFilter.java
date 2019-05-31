package com.fung.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fung.domain.User;

public class CartFilter implements Filter{

	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 判断用户登录没登录
		 * 如果登录  放行
		 * 如果没登录  跳转到登录页面
		 */
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//得到session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user==null) {//没登录
			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
		}else{//登录 放行
			chain.doFilter(request, response);
		}
		
		
		
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
