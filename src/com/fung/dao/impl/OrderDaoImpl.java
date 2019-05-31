package com.fung.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.fung.dao.OrderDao;
import com.fung.domain.Order;
import com.fung.domain.OrderItem;
import com.fung.domain.Product;
import com.fung.util.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public int insertNewOrder(Order o) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		int rows = 0;
		String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		try {
			rows = qr.update(conn, sql, o.getOid(),o.getDatetime(),o.getTotal()
					,o.getState(),o.getAddress(),o.getName(),o.getTelephone(),o.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return rows;
	}

	@Override
	public int insertNewOrderItems(List<OrderItem> ois) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
		int rows = 0;
		for (OrderItem oi : ois) {
			try {
			rows +=	qr.update(conn,sql,oi.getItemid(),oi.getCount(),oi.getSubtotal()
						,oi.getPid(),oi.getOid());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JDBCUtils.close(conn);
		return rows;
	}

	@Override
	public List<Order> getOrderByUid(String uid) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM orders WHERE uid = ?";
		List<Order> os = null;
		try {
			os= qr.query(conn, sql, new BeanListHandler<Order>(Order.class), uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return os;
	}

	@Override
	public List<Order> getCurrPageOrder(String uid, int currPage, int count) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM orders WHERE uid = ? ORDER BY ordertime desc LIMIT ?,? ";
		int start = (currPage-1)*count;
		List<Order> os = null;
		try {
			os= qr.query(conn, sql, new BeanListHandler<Order>(Order.class), uid,start,count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtils.close(conn);
		return os;
	}

	@Override
	public List<OrderItem> getOrderItemsByOid(String oid) {
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT * FROM orderitem WHERE oid = ? ";
		List<OrderItem> ois = new ArrayList<OrderItem>(); 
		try {
			ois = qr.query(conn, sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ProductDaoImpl pd = new ProductDaoImpl();
		for (OrderItem oi : ois) {
			String pid = oi.getPid();
			Product p = pd.getProductByPid(pid);
			oi.setP(p);
		}
		return ois;
	}

}
