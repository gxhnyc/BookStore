package com.oaec.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.service.RmcartService;
/**
 * 删除购物项servlet
 * @author Administrator
 *
 */
public class RmcartitemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入remove cartitem servlet---");
		//得到当前用户id
		Map<String,Object> userMap=(Map<String, Object>) req.getSession().getAttribute("userMap");
		int account_id=Integer.parseInt(userMap.get("ID").toString());
		//得到book_id
		int book_id=Integer.parseInt(req.getParameter("rbook_id"));
		
		//调用rmcartService里面的remove()方法
		RmcartService rmcs=new RmcartService();
		rmcs.rmcartitem(account_id,book_id);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
