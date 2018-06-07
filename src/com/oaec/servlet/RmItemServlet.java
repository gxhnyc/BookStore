package com.oaec.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.service.CartService;

public class RmItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取用户ID
		Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
		int account_id = Integer.parseInt(map.get("ID").toString());
		//2.获取图书id
		int book_id = Integer.parseInt(req.getParameter("book_id"));
		//3.去数据库找到对应购物项，并删除
		CartService cs = new CartService();
		cs.rmBookItem(account_id,book_id);
		
		
		resp.getWriter().print(1);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
