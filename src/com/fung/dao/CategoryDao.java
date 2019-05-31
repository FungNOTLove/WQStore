package com.fung.dao;

import java.util.List;

import com.fung.domain.Category;

public interface CategoryDao {
	//查询所有的Category信息
	List<Category> findAllCategory();
	//通过cid找到对应的 category
	Category findCategoryByCid(String cid);
	
	
}