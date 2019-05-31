package com.fung.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fung.domain.Cart;
import com.fung.domain.CartItem;
import com.fung.domain.Order;
import com.fung.domain.OrderItem;
import com.fung.domain.User;
import com.fung.service.OrderService;
import com.fung.service.impl.OrderServiceImpl;
import com.fung.util.UUIDUtils;

public class OrderInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if ("fromcart".equals(method)) {
			//因为购物车中的所有数据都在   session中
			//所以直接可以从session中获取所有的数据
			//只需要创建一个 orderid就可以了
			String oid = UUIDUtils.getUserId();
			request.setAttribute("oid", oid);
			request.getRequestDispatcher("/jsp/order_info.jsp").forward(request, response);
			
		}else if ("saveorder".equals(method)) {
			//1.order的所有数据   oid  time total state address name telephone uid
			//2.orderitem的所有数据   itemid count subtotal pid  oid
			String oid = request.getParameter("oid");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime = sdf.format(d);
			
			User u = (User) request.getSession().getAttribute("user");
			String uid = u.getUid();
			Cart c = (Cart) request.getSession().getAttribute("cart");
			double total = c.getTotalPrice();
			Order o = new Order(oid, uid, address, name, telephone, 0, total, datetime);
			
			//有几个cartitem 就要就几个cartitem  一个商一行
			List<CartItem> cis = c.getCartItems();
			List<OrderItem> ois = new ArrayList<OrderItem>();
			for (CartItem cartItem : cis) {
				//2.orderitem的所有数据   itemid count subtotal pid  oid
				String itemid = UUIDUtils.getUserId();
				int count = cartItem.getCount();
				double subTotal = cartItem.getPrice();
				String pid = cartItem.getProduct().getPid();
				OrderItem oi = new OrderItem(itemid, pid, oid, count, subTotal);
				ois.add(oi);
			}
			//储存数据库
			OrderService os = new OrderServiceImpl();
			boolean flag = os.saveOrder(o, ois);
			
			//跳转页面
			if (flag) {
				//清除cart
				c.clearCart();
				request.getSession().setAttribute("cart", c);
				response.sendRedirect(request.getContextPath()+"/jsp/success.jsp");
			}else{
				response.sendRedirect(request.getContextPath()+"/jsp/fail.jsp");
				
			}
		
			
		}
		
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
