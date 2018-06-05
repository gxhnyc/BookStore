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
 * ���ύ����ҳ�棬�޸�ͼ������������servlet
 * 
 * @author Administrator
 *
 */
public class ModQuantityServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.�õ�book_id;
		int book_id = Integer.parseInt(req.getParameter("bid"));
		System.out.println("ModQuantityServlet:book_id="+book_id);
		// 2.�õ��û�id
		Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
		// System.out.println("--userMap:" + map);
		//3.�õ��޸ĺ������
		int num=Integer.parseInt(req.getParameter("num"));
		if (map != null || map.size() > 0) {
			int account_id = Integer.parseInt(map.get("ID").toString());
		//4.ͨ���û�id��ȡ������ţ�cart_id��
			CartService cs = new CartService();
			Map<String,Object> cartmap=cs.findCartID(account_id);
			int cid=Integer.parseInt(cartmap.get("CID").toString());
		//5.����ҵ���,�޸Ĺ��ﳵ��ͼ�������ķ���
			//CartService cs = new CartService();
			int row = cs.modQuantity(num,cid, book_id);

			System.out.println("--ModQuantityServlet:�ɹ��޸�" + row+"--������");

			JSONArray jarr = JSONArray.fromObject(row);
			// System.out.println(jarr);
			resp.getWriter().print(jarr);
		} else {// ��ת���û���¼����
			System.out.println("��ת���û���¼����:login_register.jsp");
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