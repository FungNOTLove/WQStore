package com.fung.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 购物车实体类
 * @author asong
 *
 */
public class Cart {
	private LinkedList<CartItem> cartItems = new LinkedList<CartItem>();
	private double totalPrice;
	public Cart() {
	}
	
	
	
	public LinkedList<CartItem> getCartItems() {
		return cartItems;
	}



	public void setCartItems(LinkedList<CartItem> cartItems) {
		this.cartItems = cartItems;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public double getTotalPrice() {
		double total = 0;
		if (cartItems!=null) {
			for (CartItem cartItem : cartItems) {
				total+=cartItem.getPrice();
			}
		}
		totalPrice = total;
		return totalPrice;
	}
	
	//将cartItem放入 cart中
	//存在不存在 如果存在,只修改数据就行,如果不存在 添加到cartitems中
	public void addCartItem(CartItem cartItem){
		//如何判断存不存在  判断商品  通过pid来判断
		//要加入的商品的pid
		String  addPid = cartItem.getProduct().getPid();
		//定义一个标记   如果是true 代表不存在 如果是false代表存在
		boolean flag = true;
		for (CartItem item : cartItems) {
			if (item.getProduct().getPid().equals(addPid)) {//代表找到了
				//设置新的总个数
				item.setCount(item.getCount()+cartItem.getCount());
				//设置新的总价钱
				item.setPrice(item.getPrice()+cartItem.getPrice());
				flag = false;
				break;
			}
		}
		if (flag) {//没找到
			cartItems.addFirst(cartItem);
		}
	}
	
	public void removeCartItem(String pid){
		for (CartItem item : cartItems) {
			if (item.getProduct().getPid().equals(pid)) {//代表找到了
				cartItems.remove(item);
			}
		}
	}
	
	
	public void clearCart(){
		cartItems.clear();
	}
	
}





