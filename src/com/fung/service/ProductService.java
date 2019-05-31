package com.fung.service;

import java.util.List;

import com.fung.domain.PageBean;
import com.fung.domain.Product;

public interface ProductService {
	
	//得到当前页数据   to
	PageBean<Product> getCurrPageBean2Product(int currPage,String cid);
	
	//得到商品的详情
	Product getProductInfoByPid(String pid);
	
	//得到最热商品
	List<Product> getHotProducts();
}
