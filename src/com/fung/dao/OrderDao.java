package com.fung.dao;

import java.util.List;

import com.fung.domain.Order;
import com.fung.domain.OrderItem;

public interface OrderDao {
	//插入  order 数据
	int insertNewOrder(Order order);
	//插入 orderitem数据
	int insertNewOrderItems(List<OrderItem> ois);
	//查询所有该用户的order信息
	List<Order> getOrderByUid(String uid);
	List<Order> getCurrPageOrder(String uid,int currPage,int count);
	List<OrderItem> getOrderItemsByOid(String oid);
}