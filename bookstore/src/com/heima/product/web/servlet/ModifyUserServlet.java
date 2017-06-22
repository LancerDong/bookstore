package com.heima.product.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.product.domain.User;
import com.heima.product.service.UserService;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import com.sun.org.apache.regexp.internal.recompile;

public class ModifyUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//封装表单数据
				User user = new User();
				try {
					BeanUtils.populate(user, request.getParameterMap());
					UserService us = new UserService();
					us.modifyUser(user);
					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath()+"/modifyUserInfoSuccess.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write(e.getMessage());
				}
				
				//执行业务层，修改用户信息
	}

}
