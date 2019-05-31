package com.fung.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import com.fung.dao.CategoryDao;
import com.fung.dao.impl.CategoryDaoImpl;
import com.fung.domain.Category;
import com.fung.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	@Override
	public JSONArray findAllCategory() {
		CategoryDao cd = new CategoryDaoImpl();
		List<Category> list = cd.findAllCategory();
		//将这个list变成一个jsonarray
		JSONArray jsonArray = new JSONArray();
		if (list==null) {
			return jsonArray;
		}
		jsonArray.addAll(list);
		
		return jsonArray;
	}

	@Override
	public Category findCategoryByCid(String cid) {
		CategoryDao cd = new CategoryDaoImpl();
		return cd.findCategoryByCid(cid);
	}

}







