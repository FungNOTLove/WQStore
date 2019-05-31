package com.fung.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.fung.dao.UserDao;
import com.fung.domain.User;
import com.fung.util.JDBCUtils;

public class UserDaoImpl implements UserDao{
	
	/**
	 * 向数据库中添加一条用户信息
	 */
	@Override
	public int insertUserinfo(User user) {
		/*
		 * 1. 获取连接池对象 C3P0连接池
		 * 2. 通过DataSource对象创建一个QueryRunner对象   DBUtils
		 */
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		int rows = 0;
		try {
			rows = qr.update(conn,sql, user.getUid(),user.getUsername()
					,user.getPassword(),user.getName(),
					user.getPhoto(),user.getEmail(),
					user.getTelephone(),user.getBirthday(),
					user.getSex(),user.getState(),user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return rows;
	}

	@Override
	public User findUserByUserName(String username) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM user WHERE username = ?";
		User user = null;
		try {
			//BeanHandler   将ResultSet处理成一个对象返回出去 是ResultSetHandler的子类
			user = qr.query(conn,sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		
		return user;
	}
	
	/**
	 * 根据用户名和密码查询出这个用户 
	 */
	@Override
	public User findUser(String username, String password) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		User user = null;
		BeanHandler<User> bh = new BeanHandler<User>(User.class);
		try {
			user = qr.query(conn,sql, bh, username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return user;
	}

}






