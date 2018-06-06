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
		List<CartDetails> list=(List<CartDetails>) req.getSession().getAttribute("cartList");
		System.out.println("���ﳵ���ݣ�"+list);		
		
		// 1.�õ�book_id;
		int book_id = Integer.parseInt(req.getParameter("pid"));//����cart.jsp��ajax�������
		// 2.�õ��û�id
		Map<String, Object> map = (Map<String, Object>) req.getSession().getAttribute("userMap");
		// System.out.println("--userMap:" + map);
		//3.�õ��޸ĺ������
		int num=Integer.parseInt(req.getParameter("num"));
		if (map != null || map.size() > 0) {
			int account_id = Integer.parseInt(map.get("ID").toString());
			// 3.����ҵ���,�޸Ĺ��ﳵ��ͼ�������ķ���
			CartService cs = new CartService();
			//��ͨ��account_id�õ����ﳵ��cats�����id(c_id)
			
			Map<String,Object> cartMap=cs.findCartID(account_id);
			int cart_id=Integer.parseInt(cartMap.get("CID").toString());
			int row = cs.modQuantity(num,cart_id, book_id);
			List<CartDetails> cartlist = cs.findCartsByAccountID(account_id);
			
			req.getSession().setAttribute("cartList", cartlist);
			
			System.out.println("--ModQuantityServlet:�ɹ��޸�" + row+"--������");
			System.out.println("��ʱ���ﳵ����Ϊ��"+cartlist);
			//��ȡ��ǰ�鼮�Ĺ�����cart_item
			Map<String,Object> itemMap=cs.getSubtotal(account_id,book_id);
			String subtotal=itemMap.get("SUBTOTAL").toString();
			String bk_id=itemMap.get("BOOK_ID").toString();
			String arr[]={subtotal,bk_id};
			JSONArray jarr = JSONArray.fromObject(arr);
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
