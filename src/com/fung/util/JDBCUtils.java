package com.fung.util;

import java.sql.Connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *  提供连接池的 工具类
 *  单例模式
 *  
 * @author fung
 *
 */
public class JDBCUtils {

	/**
	 * 单例模式 三步骤
	 * 1.构造方法私有化
	 * 2.本类创建对象
	 * 3.提供给外部一个公开的接口
	 */
	//连接池
	private static DataSource ds;
	
	private JDBCUtils(){
		
	}
	
	 static{
		ds = new ComboPooledDataSource();
	}
	
	public synchronized static Connection getConnection(){
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}









