package com.heima.product.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.product.domain.User;
import com.heima.product.exception.UserException;
import com.heima.product.service.UserService;

public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用业务层方法
		UserService us = new UserService();
		try {
			String path = "/index.jsp";
			User user = us.longin(username,password);
			if ("admin".equals(user.getRole())) {
				path = "/admin/login/home.jsp";
			}
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher(path).forward(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			request.setAttribute("userlogin_msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
