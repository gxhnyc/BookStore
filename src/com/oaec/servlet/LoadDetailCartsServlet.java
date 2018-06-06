package com.oaec.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.pojo.CartDetails;
import com.oaec.service.CartService;

import net.sf.json.JSONArray;

public class LoadDetailCartsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
				// 1.得到用户id
				Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
				//System.out.println("--userMap:" + map);
				if(map!=null) {
					int account_id = Integer.parseInt(map.get("ID").toString());
					// 2.调用业务层,查询购物车方法
					CartService cs = new CartService();
					List<CartDetails> list = cs.findCartsByAccountID(account_id);					
					
					req.getSession().setAttribute("cartList", list);
					System.out.println("LoadDetailCartsServlet:" + list);
			
								
					JSONArray jarr = JSONArray.fromObject(list);
					//System.out.println(jarr);
					resp.getWriter().print(jarr);
				}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
