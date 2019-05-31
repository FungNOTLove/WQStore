package com.fung.domain;

import java.util.Date;
import java.util.List;

public class Order {
	private String oid;
	private String uid;
	private String address;
	private String name;
	private String telephone;
	private int state;
	private double total;
	private String datetime;
	
	private List<OrderItem> ois;
	
	
	public Order() {
		// TODO Auto-generated constructor stub
	}


	public Order(String oid, String uid, String address, String name,
			String telephone, int state, double total, String datetime) {
		super();
		this.oid = oid;
		this.uid = uid;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.state = state;
		this.total = total;
		this.datetime = datetime;
	}


	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public String getDatetime() {
		return datetime;
	}


	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	

	public List<OrderItem> getOis() {
		return ois;
	}


	public void setOis(List<OrderItem> ois) {
		this.ois = ois;
	}


	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", address=" + address
				+ ", name=" + name + ", telephone=" + telephone + ", state="
				+ state + ", total=" + total + ", datetime=" + datetime
				+ ", ois=" + ois + "]";
	}


	


	
	
	
	
	
	
}
