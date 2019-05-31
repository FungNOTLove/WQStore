package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fung.domain.Product;
import com.fung.service.ProductService;
import com.fung.service.impl.ProductServiceImpl;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取9条最热商品信息
		ProductService ps = new ProductServiceImpl();
		List<Product> products = ps.getHotProducts();
		request.setAttribute("hotproduct", products);
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
	}

}
