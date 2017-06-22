package com.heima.product.service;

import java.sql.SQLException;

import com.heima.product.dao.UserDao;
import com.heima.product.domain.User;
import com.heima.product.exception.UserException;
import com.heima.product.utils.SendJMail;

public class UserService {
	UserDao ud = new UserDao();
	
	public void regist(User user) throws UserException {
		try {
			ud.addUser(user);
			//注册成功，发送激活码
			String emailMsg = "注册成功！请前往<a href='http://www.product.com?activeCode="+ user.getActiveCode() +"'>激活</a>";
			SendJMail.sendMail(user.getEmail(), emailMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("注册失败");
		}
	}
	/**
	 * 激活用户
	 * @param activeCode
	 */
	public void activeUser(String activeCode) throws UserException {
		try {
			User user = ud.getUserByActiveCode(activeCode);
			if (user != null) {
				ud.ActiveUser(activeCode);
				return;
			}
			throw new UserException("激活失败！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败！");
		}
		
	}
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws UserException 
	 */
	public User longin(String username, String password) throws UserException {
		User user = null;
		try {
			user = ud.findUserByUsernameAndPassword(username,password);
			if (user == null) {
				throw new UserException("用户名或密码错误！");
			}
			if (user.getState() == 0 ) {
				throw new UserException("用户未激活！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("用户名或密码错误！");
		}
		return user;
		
	}
	//查询用户根据id
	public User findUserById(String id) throws UserException {
		User user=null;
		try {
			user = ud.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("查询失败");
		}
		return user;
	}
	public void modifyUser(User user) throws UserException {
		try {
			ud.modifyUserById(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("用户修改失败");
		}
		
	}

}
