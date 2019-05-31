package com.fung.service;

import java.util.List;

import com.fung.domain.Order;
import com.fung.domain.OrderItem;
import com.fung.domain.PageBean;

public interface OrderService {
	boolean saveOrder(Order o,List<OrderItem> ois);
	
	//订单列表的分页
	PageBean<Order> getCurrPageOrder(String uid,int currPage);
	
	//获取orderitem的方法
	List<OrderItem> getOrderItemsByOid(String oid);
	
} 
