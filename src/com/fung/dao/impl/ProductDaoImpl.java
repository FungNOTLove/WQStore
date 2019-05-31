package com.fung.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.fung.dao.ProductDao;
import com.fung.domain.Product;
import com.fung.util.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> getAllProductsByCid(String cid) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM product WHERE cid=?";
		try {
			products = qr.query(conn,sql,
					new BeanListHandler<Product>(Product.class), cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return products;
	}

	@Override
	public List<Product> getCurrPageProducts(String cid,int startIndex, int count) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?";
		try {
			products = qr.query(conn,sql,
					new BeanListHandler<Product>(Product.class), 
					cid,startIndex,count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return products;
	}

	@Override
	public Product getProductByPid(String pid) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM product WHERE pid=?";
		Product product = null;
		try {
			product = qr.query(conn,sql, new BeanHandler<Product>(Product.class),pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return product;
	}

	@Override
	public List<Product> getHotProduct() {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM product WHERE is_hot=1 LIMIT 0,9";
		try {
			products = qr.query(conn,sql,
					new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return products;
	}

}
















