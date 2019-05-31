package com.fung.domain;

public class OrderItem {
	private String itemid;
	private String pid;
	private String oid;
	private int count;
	private double subtotal;
	
	private Product p;
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public OrderItem(String itemid, String pid, String oid, int count,
			double subtotal) {
		super();
		this.itemid = itemid;
		this.pid = pid;
		this.oid = oid;
		this.count = count;
		this.subtotal = subtotal;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	@Override
	public String toString() {
		return "OrderItem [itemid=" + itemid + ", pid=" + pid + ", oid=" + oid
				+ ", count=" + count + ", subtotal=" + subtotal + ", p=" + p.toString()
				+ "]";
	}
	
	
}
