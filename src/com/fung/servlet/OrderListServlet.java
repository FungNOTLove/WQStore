package com.fung.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fung.domain.Order;
import com.fung.domain.OrderItem;
import com.fung.domain.PageBean;
import com.fung.domain.User;
import com.fung.service.OrderService;
import com.fung.service.impl.OrderServiceImpl;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//pagebean<Order>  List<Order> os当前用户的所有订单
		//maxPages  currPage  count
		request.setCharacterEncoding("UTF-8");
		int currPage = Integer.valueOf(request.getParameter("currpage"));
		OrderService os = new OrderServiceImpl();
		String uid = ((User)request.getSession().getAttribute("user")).getUid();
		PageBean<Order> page = os.getCurrPageOrder(uid, currPage);
		List<Order> orders = page.getLists();
		//得到所有的oid   查询orderItem这张表 得到每一个oid对应的所有的 orderitem
		for (Order order : orders) {
			String oid = order.getOid();
			List<OrderItem> ois = os.getOrderItemsByOid(oid);
			order.setOis(ois);
			//这个order包含的信息:
			//oid ordertime total state address name telephone uid
			//List<orderitem>  count subtotal 
			//product   pimage  pname shop_price
		}
		page.setLists(orders);
		System.out.println(page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/jsp/order_list.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
