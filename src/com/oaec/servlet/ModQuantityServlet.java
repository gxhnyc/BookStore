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
		List<CartDetails> list=(List<CartDetails>) req.getSession().getAttribute("cartList");
		System.out.println("购物车内容："+list);		
		
		// 1.得到book_id;
		int book_id = Integer.parseInt(req.getParameter("pid"));//来自cart.jsp的ajax请求参数
		// 2.得到用户id
		Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
		// System.out.println("--userMap:" + map);
		//3.得到修改后的数量
		int num=Integer.parseInt(req.getParameter("num"));
		if (map != null || map.size() > 0) {
			int account_id = Integer.parseInt(map.get("ID").toString());
			// 3.调用业务层,修改购物车内图书数量的方法
			CartService cs = new CartService();
			//先通过account_id得到购物车（cats）表的id(c_id)
			
			Map<String,Object> cartMap=cs.findCartID(account_id);
			int cart_id=Integer.parseInt(cartMap.get("CID").toString());
			int row = cs.modQuantity(num,cart_id, book_id);
			List<CartDetails> cartlist = cs.findCartsByAccountID(account_id);
			
			req.getSession().setAttribute("cartList", cartlist);
			
			System.out.println("--ModQuantityServlet:成功修改" + row+"--条数据");
			System.out.println("此时购物车内容为："+cartlist);
			//获取当前书籍的购物项cart_item
			Map<String,Object> itemMap=cs.getSubtotal(account_id,book_id);
			String subtotal=itemMap.get("SUBTOTAL").toString();
			String bk_id=itemMap.get("BOOK_ID").toString();
			String arr[]={subtotal,bk_id};
			JSONArray jarr = JSONArray.fromObject(arr);
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
