package com.heima.product.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.heima.product.domain.User;
import com.heima.product.utils.C3P0Util;

public class UserDao {

	public void addUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into user(username,PASSWORD,gender," +
				"email,telephone,introduce,activecode," +
				"state,registtime) values(?,?,?,?,?,?,?,?,?)";
		qr.update(sql,user.getUsername(),user.getPassword(),
				user.getGender(),user.getEmail(),user.getTelephone(),
				user.getIntroduce(),user.getActiveCode(),
				user.getState(),user.getRegistTime());
		
	}
	
	//根据激活码查找用户
	public User getUserByActiveCode(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from user where activecode=?";
		return qr.query(sql, new BeanHandler<User>(User.class), activeCode);
	}
	//激活用户
	public void ActiveUser(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update user set state=1 where activeCode=?";
		qr.update(sql, activeCode);
	}

	/**
	 * 根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username,password);
	}

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public User findUserById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from user where id=?";
		return qr.query(sql, new BeanHandler<User>(User.class), id);
	}

	/**
	 * 根据用户ID修改用户信息
	 * @param user
	 * @throws SQLException 
	 */
	public void modifyUserById(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update user set password=?, gender=?, telephone=? where id=?";
		qr.update(sql, user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
	
		
	}

}
