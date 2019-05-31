package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fung.domain.User;
import com.fung.service.UserService;
import com.fung.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 *  login.jsp 通过  ajax发送的请求
		 *  所有数据都保存在  request
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//将数据提交给  service层处理
		UserService us= new UserServiceImpl();
		User user = us.login(username, password);
		//判断成功或失败
		if (user==null) {
			response.getWriter().write("false");
		}else{
			//将User对象保存到Session中  来保持登录状态
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.getWriter().write("true");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
