package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fung.domain.PageBean;
import com.fung.domain.Product;
import com.fung.service.ProductService;
import com.fung.service.impl.ProductServiceImpl;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cid = request.getParameter("cid");
		int currPage = Integer.valueOf(request.getParameter("currpage"));
		//cid+currPage
		ProductService ps = new ProductServiceImpl();
		PageBean<Product> pageBean = ps.getCurrPageBean2Product(currPage, cid);
		//将数据传递给   product_list.jsp  域对象 request/session
		request.setAttribute("pagebean", pageBean);
		request.setAttribute("cid", cid);
		//*******************获取历史记录开始
		List<Product> products = getHistory(request);
		request.setAttribute("products", products);
		//*******************获取历史记录结束
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product_list.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 从cookie中得到浏览所有商品的pid
	 * 通过pid得到商品信息
	 * @param request
	 * @return
	 */
	private List<Product> getHistory(HttpServletRequest request) {
		Cookie[]cookies = request.getCookies();
		Cookie history = null;
		List<Product> products = new ArrayList<Product>();
		if (cookies!=null&&cookies.length>0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("history")) {
					history = cookie;//找到历史记录   1-2-3-4-5
					String value = history.getValue();
					String []pids = value.split("-");//找到所有的pid
					for (String pid : pids) {
						ProductService ps = new ProductServiceImpl();
						Product product = ps.getProductInfoByPid(pid);
						products.add(product);
					}
				}
			}
		}
		return products;
		
		
		
	}


}










