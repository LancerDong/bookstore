package com.heima.product.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heima.product.domain.Product;
import com.heima.product.service.ProductService;



public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		
		ProductService bs = new ProductService();
		
		Product b = bs.findBookById(id);
		
		//��session�еĹ��ﳵȡ�� ��
		HttpSession session = request.getSession();
		Map<Product, String> cart = (Map<Product, String>) session.getAttribute("cart");
		int num = 1;
		//����ǵ�һ�η��ʣ�û�й��ﳵ�������Ǿʹ��� һ�����ﳵ����
		if(cart==null){
			cart = new HashMap<Product, String>();
			
		}
		//�鿴��ǰ�������Ƿ����b�Ȿ��,����оͰ����ȡ������1;
		if(cart.containsKey(b)){
			num=Integer.parseInt(cart.get(b))+1;
		}
		//��ͼ����빺�ﳵ
		cart.put(b, num+"");
		
		//��cart����Żص�session��������
		session.setAttribute("cart", cart);
		
		out.print("<a href='"+request.getContextPath()+"/PageServlet'>继续购物</a> <a href='"+request.getContextPath()+"/cart.jsp'>�查看购物车</a>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
