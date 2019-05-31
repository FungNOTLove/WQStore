package com.fung.service.impl;

import java.util.List;

import com.fung.dao.ProductDao;
import com.fung.dao.impl.ProductDaoImpl;
import com.fung.domain.PageBean;
import com.fung.domain.Product;
import com.fung.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public PageBean<Product> getCurrPageBean2Product(int currPage, String cid) {
		/**
		 * 1.获取总页数
		 * 		总数/12
		 */
		ProductDao pd = new ProductDaoImpl();
		List<Product> allProducts = pd.getAllProductsByCid(cid);
		//得到总产品数量
		int allCount = allProducts.size();
		int count = 12;//每页数据量
		//得到总页数
		int maxPages = allCount%count==0?allCount/count:allCount/count+1;
		//起始下标   0  12   24
		int startIndex = (currPage-1)*12;
		List<Product> currProducts = pd.getCurrPageProducts(cid, startIndex, count);
		PageBean<Product> pageBean = 
				new PageBean<Product>(currProducts, maxPages, currPage, count);
		
		return pageBean;
	}

	@Override
	public Product getProductInfoByPid(String pid) {
		ProductDao pd = new ProductDaoImpl();
		return pd.getProductByPid(pid);
	}

	@Override
	public List<Product> getHotProducts() {
		ProductDao pd = new ProductDaoImpl();
		return pd.getHotProduct();
	}

}
