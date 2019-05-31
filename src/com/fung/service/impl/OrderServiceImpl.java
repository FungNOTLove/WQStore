package com.fung.service.impl;

import java.util.List;

import com.fung.dao.OrderDao;
import com.fung.dao.impl.OrderDaoImpl;
import com.fung.domain.Order;
import com.fung.domain.OrderItem;
import com.fung.domain.PageBean;
import com.fung.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public boolean saveOrder(Order o, List<OrderItem> ois) {
		OrderDao od = new OrderDaoImpl();
		int row1 = od.insertNewOrder(o);
		int row2 = od.insertNewOrderItems(ois);
		if (row1>0&&row2>0) {
			return true;
		}
		
		
		return false;
	}

	@Override
	public PageBean<Order> getCurrPageOrder(String uid, int currPage) {
		
		int count = 2;
		OrderDao od = new OrderDaoImpl();
		List<Order> all = od.getOrderByUid(uid);
		int total = all.size();
		int maxPages = total%count==0?total/count:total/count+1;
		List<Order> currPageOrders = od.getCurrPageOrder(uid, currPage, count);
		PageBean<Order> page = new PageBean<Order>(currPageOrders, maxPages, currPage, count);
		return page;
		
	}

	@Override
	public List<OrderItem> getOrderItemsByOid(String oid) {
		// TODO Auto-generated method stub
		return new OrderDaoImpl().getOrderItemsByOid(oid);
	}

}
