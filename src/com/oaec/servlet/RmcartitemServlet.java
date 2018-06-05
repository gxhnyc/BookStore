package com.oaec.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.service.RmcartService;
/**
 * ɾ��������servlet
 * @author Administrator
 *
 */
public class RmcartitemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("����remove cartitem servlet---");
		//�õ���ǰ�û�id
		Map<String,Object> userMap=(Map<String, Object>) req.getSession().getAttribute("userMap");
		int account_id=Integer.parseInt(userMap.get("ID").toString());
		//�õ�book_id
		int book_id=Integer.parseInt(req.getParameter("rbook_id"));
		
		//����rmcartService�����remove()����
		RmcartService rmcs=new RmcartService();
		rmcs.rmcartitem(account_id,book_id);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
