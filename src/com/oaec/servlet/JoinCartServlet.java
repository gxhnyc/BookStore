package com.oaec.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.pojo.CartDetails;
import com.oaec.service.CartService;

import net.sf.json.JSONArray;

public class JoinCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.得到book_id;
		int book_id = Integer.parseInt(req.getParameter("bid"));
		// 2.得到用户id
		Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
		//System.out.println("--userMap:" + map);
		if(map!=null||map.size()>0) {
			int account_id = Integer.parseInt(map.get("ID").toString());
			// 3.调用业务层,加入购物车方法
			CartService cs = new CartService();
			List<CartDetails> list = cs.joincart(account_id, book_id);
	
			System.out.println("JoinCartServlet:" + list);
	
			JSONArray jarr = JSONArray.fromObject(list);
			//System.out.println(jarr);
			resp.getWriter().print(jarr);
		}else {//跳转至用户登录界面
			System.out.println("跳转至用户登录界面:login_register.jsp");
			int flag=1;
			JSONArray jarr = JSONArray.fromObject(flag);
			resp.getWriter().print(jarr);			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
