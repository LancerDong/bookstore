package com.heima.product.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;


import com.heima.product.domain.User;
import com.heima.product.exception.UserException;
import com.heima.product.service.UserService;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//处理验证码
		String ckCode = request.getParameter("checkCode");
		String ckCode_session = (String) request.getSession().getAttribute("checkcode_session");
		if (!ckCode_session.equals(ckCode)) {
			request.setAttribute("ckCode_msg", "验证码错误！");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		System.out.println(request.getParameterMap().toString());
		//获取表单数据
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());

			//调用业务逻辑
			UserService us = new UserService();
			us.regist(user);
			//分发转向
			//激活后才能向session中保存User信息
			//request.getSession().setAttribute("User", user);
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
			
		} catch (UserException e) {
			request.setAttribute("user_msg", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		} catch (Exception e) {
//			UserException ue = (UserException)e;
//			request.setAttribute("user_msg", ue.getMessage());
//			request.getRequestDispatcher("/register.jsp").forward(request, response);
//			return;
			e.printStackTrace();
		}
	}
	

}
