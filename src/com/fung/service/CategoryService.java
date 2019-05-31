package com.fung.service;

import com.fung.domain.Category;

import net.sf.json.JSONArray;

public interface CategoryService {
	//找到所有的Category 并封装成一个JSONArray
	JSONArray findAllCategory();
	//找到单个category
	Category findCategoryByCid(String cid);
}
