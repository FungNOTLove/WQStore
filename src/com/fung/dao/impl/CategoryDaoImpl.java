package com.fung.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.fung.dao.CategoryDao;
import com.fung.domain.Category;
import com.fung.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> findAllCategory() {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM category";
		List<Category> lists = null;
		BeanListHandler<Category> blh = new BeanListHandler<Category>(Category.class);
		try {
			lists = qr.query(conn,sql, blh);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return lists;
	}

	@Override
	public Category findCategoryByCid(String cid) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM category WHERE cid=?";
		Category c = null;
		try {
			c = qr.query(conn,sql, new BeanHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return c;
	}

}














