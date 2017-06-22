package com.heima.product.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	//创建数据库连接池
	private static DataSource dataSource = new ComboPooledDataSource();
	
	
	public static DataSource getDataSource() {
		return dataSource;
	}

	//获取连接
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器错误");
		}
	}
	
	public static void release(Connection conn,Statement stmt,ResultSet rs){
		//关闭资源
				if(rs!=null){
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					rs = null;
				}
				if(stmt!=null){
					try {
						stmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					stmt = null;
				}
				if(conn!=null){
					try {
						conn.close();//关闭连接
					} catch (Exception e) {
						e.printStackTrace();
					}
					conn = null;
				}
	}
	
}
