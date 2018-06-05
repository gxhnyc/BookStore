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

/**
 * 在提交订单页面，修改图书数量的请求servlet
 * 
 * @author Administrator
 *
 */
public class ModQuantityServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.得到book_id;
		int book_id = Integer.parseInt(req.getParameter("bid"));
		System.out.println("ModQuantityServlet:book_id="+book_id);
		// 2.得到用户id
		Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
		// System.out.println("--userMap:" + map);
		//3.得到修改后的数量
		int num=Integer.parseInt(req.getParameter("num"));
		if (map != null || map.size() > 0) {
			int account_id = Integer.parseInt(map.get("ID").toString());
		//4.通过用户id获取订单编号（cart_id）
			CartService cs = new CartService();
			Map<String,Object> cartmap=cs.findCartID(account_id);
			int cid=Integer.parseInt(cartmap.get("CID").toString());
		//5.调用业务层,修改购物车内图书数量的方法
			//CartService cs = new CartService();
			int row = cs.modQuantity(num,cid, book_id);
			double price=cs.findBookPrice(book_id);
			double subtotal=price*num;
			System.out.println("--ModQuantityServlet:成功修改" + row+"--条数据:￥"+subtotal);

			JSONArray jarr = JSONArray.fromObject(subtotal);
			// System.out.println(jarr);
			resp.getWriter().print(jarr);
		} else {// 跳转至用户登录界面
			System.out.println("跳转至用户登录界面:login_register.jsp");
			int flag = 1;
			JSONArray jarr = JSONArray.fromObject(flag);
			resp.getWriter().print(jarr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
