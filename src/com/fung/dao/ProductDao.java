package com.fung.dao;

import java.util.List;

import com.fung.domain.Product;

public interface ProductDao {
	
	//查询出所有的商品   
	List<Product> getAllProductsByCid(String cid);
	//查询出当前页数
	//limit startIndex,count ; 从startIndex 开始读取count个
	List<Product> getCurrPageProducts(String cid,int startIndex,int count);
	//查询当前商品
	Product getProductByPid(String pid);
	//获取最热商品
	List<Product> getHotProduct();
	
}
