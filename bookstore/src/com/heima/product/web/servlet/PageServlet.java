package com.heima.product.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.product.domain.PageBean;
import com.heima.product.service.ProductService;

public class PageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String category = request.getParameter("category");
		if (category == null) {
			category="";
		}
		//初始化每页显示的记录数
		int pageSize = 4;
		//当前页
		int currentPage = 1;
		String currPage = request.getParameter("currentPage");//从上一页或下一页获得的数据
		if (currPage != null && !"".equals(currPage)) {
			currentPage = Integer.parseInt(currPage);
		}
		ProductService ps = new ProductService();
		//分页查询，并返回PageBean对象
		PageBean pb = ps.findBookPage(currentPage,pageSize,category);
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
