package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.fung.service.CategoryService;
import com.fung.service.impl.CategoryServiceImpl;
/**
 * 查询所有的category信息的
 * 响应给前端 header.jsp
 * @author asong
 *
 */
public class CategoryServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		CategoryService cs = new CategoryServiceImpl();
		JSONArray jsonArray = cs.findAllCategory();
		
		response.getWriter().print(jsonArray);
		
	}

}