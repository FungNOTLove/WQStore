package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fung.domain.Category;
import com.fung.domain.Product;
import com.fung.service.CategoryService;
import com.fung.service.ProductService;
import com.fung.service.impl.CategoryServiceImpl;
import com.fung.service.impl.ProductServiceImpl;

public class ProductInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pid = request.getParameter("pid");
//		response.getWriter().write(pid);
		ProductService ps = new ProductServiceImpl();
		Product p = null;
		p = ps.getProductInfoByPid(pid);
		request.setAttribute("product", p);
		String cid = p.getCid();
		CategoryService cs = new CategoryServiceImpl();
		Category c = cs.findCategoryByCid(cid);
		request.setAttribute("category", c);
		//**************************历史记录开启
		makeHistory(request,response,p.getPid());
		//**************************历史记录结束
		
		request.getRequestDispatcher("/jsp/product_info.jsp").forward(request,response);
		
	}
	/**
	 *  创建历史记录
	 *  将 浏览过的pid 放入  cookie中
	 * @param request
	 * @param response
	 * @param pid
	 */
	private void makeHistory(HttpServletRequest request,
			HttpServletResponse response, String pid) {
		//1.获取cookie对象
		Cookie[] cookies = request.getCookies();
		Cookie history = null;
		if (cookies!=null && cookies.length>0) {//有cookie  查找histroy这个cookie
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("history")) {
					history = cookie;
				}
			}
		}
		//如果找不到
		if (history==null) {
			history = new Cookie("history", "");
		}
		
		String newValue = createValue(history,pid);
		history.setValue(newValue);
		history.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(history);
		
	}
	private String createValue(Cookie history, String pid) {
		String oldValue = history.getValue();
		if (oldValue==null||oldValue.equals("")) {
			return pid;
		}
		String[] oldValues = oldValue.split("-");
		List<String> strs = Arrays.asList(oldValues);
		LinkedList<String> list = new LinkedList<String>(strs);
		if (list.contains(pid)) {
			list.remove(pid);
			list.addFirst(pid);
		}else{
			if (list.size()>=5) {
				list.removeLast();
			}
			list.addFirst(pid);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i!=list.size()-1) {
				sb.append(list.get(i)+"-");
			}else{
				sb.append(list.get(i));
			}
		}
		
		
		return sb.toString();
	}
	

}





















