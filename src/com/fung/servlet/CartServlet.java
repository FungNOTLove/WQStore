package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fung.domain.Cart;
import com.fung.domain.CartItem;
import com.fung.domain.Product;
import com.fung.service.ProductService;
import com.fung.service.impl.ProductServiceImpl;

public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if ("add".equals(method)) {
			addCart(request,response);
		}else if ("delete".equals(method)) {
			removeCartItem(request,response);
		}else if ("deleteall".equals(method)) {
			removeCart(request,response);
		}
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

	}
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 */
	private void removeCart(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart==null) {
			cart = new Cart();
		}
		cart.clearCart();
		session.setAttribute("cart", cart);
	}
	/**
	 * 删除购物车中的一项
	 * @param request
	 * @param response
	 */
	private void removeCartItem(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart==null) {
			cart = new Cart();
		}
		String pid = request.getParameter("pid");
		cart.removeCartItem(pid);
		session.setAttribute("cart", cart);

	}
	/**
	 * 添加购物车
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 原先购物车有什么
		// 再来添加新的东西

		//pid+count 通过pid ==> product,count,price   cartItem

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//如果cart==null 代表购物车没有任何内容,
		if (cart==null) {
			cart = new Cart();
		}
		String pid = request.getParameter("pid");
		int count = Integer.valueOf(request.getParameter("count"));
		ProductService ps = new ProductServiceImpl();
		Product p = ps.getProductInfoByPid(pid);
		CartItem ci = new CartItem(p, count);
		cart.addCartItem(ci);
		//将cart放入session中
		session.setAttribute("cart", cart);


	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
