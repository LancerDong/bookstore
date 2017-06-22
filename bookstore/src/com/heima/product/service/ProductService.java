package com.heima.product.service;

import java.sql.SQLException;
import java.util.List;

import com.heima.product.dao.ProductDao;
import com.heima.product.domain.PageBean;
import com.heima.product.domain.Product;

public class ProductService {

	ProductDao pd = new ProductDao();

	public PageBean findBookPage(int currentPage, int pageSize, String category) {
		try {
			int count = pd.count(category);//获得总记录数
			int totalPage = (int) Math.ceil(count*1.0/pageSize);//获得总页数
			List<Product> products = pd.findBooks(currentPage,pageSize,category);
			//将数据封装到PageBean中
			PageBean pageBean = new PageBean();
			pageBean.setTotalCount(count);   //总记录数
			pageBean.setTotalPage(totalPage); //总页数
			pageBean.setCurrentPage(currentPage); //当前页
			pageBean.setCategory(category);  //类别
			pageBean.setPs(products); //商品类对象集合
			pageBean.setCategory(category); //类别
			return pageBean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Product findBookById(String id) {
		Product product=null;
		try {
			product = pd.findBookById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

}
