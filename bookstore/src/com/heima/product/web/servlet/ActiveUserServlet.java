package com.heima.product.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.product.exception.UserException;
import com.heima.product.service.UserService;

public class ActiveUserServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		String activeCode = request.getParameter("activeCode");
		UserService us = new UserService();
		try {
			us.activeUser(activeCode);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//激活失败，提示用户失败信息
			response.getWriter().write(e.getMessage());
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		
		
	}

}
