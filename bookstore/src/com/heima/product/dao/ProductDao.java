package com.heima.product.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.heima.product.domain.PageBean;
import com.heima.product.domain.Product;
import com.heima.product.utils.C3P0Util;

public class ProductDao {


	public List<Product> findBooks(int currentPage, int pageSize, String category) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from products where 1=1 ";
		List list = new ArrayList();
		if (!"".equals(category)) {
			sql+="and category=? ";
			list.add(category);
		}
		sql+="limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
	}

	public int count(String category) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select count(*) from products";
		if (!"".equals(category)) {
			sql+=" where category='" + category + "'";
		}
		long l = (Long) qr.query(sql, new ScalarHandler(1));
		return (int) l;
	}

	public Product findBookById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from products where id=?";
		return qr.query(sql, new BeanHandler<Product>(Product.class), id);
	}

}
