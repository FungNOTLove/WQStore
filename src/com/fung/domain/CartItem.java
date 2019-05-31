package com.fung.domain;

/**
 * 购物车列表项
 * @author asong
 *
 */
public class CartItem {
	//商品1  商品1的数量   商品1总价
	private Product product;
	private int count;
	private double price;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(Product product, int count) {
		super();
		this.product = product;
		this.count = count;
		this.price = product.getShop_price()*count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartItem [product=" + product + ", count=" + count + ", price="
				+ price + "]";
	}
	
	
	
	
}