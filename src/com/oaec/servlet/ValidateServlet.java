package com.oaec.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.service.RegisterService;
/**
 * �����û�����֤��servlet
 * @author Yechao
 *
 */
public class ValidateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String account=req.getParameter("name");
		RegisterService rs=new RegisterService();
		Map<String,Object> map=rs.validateAccount(account);
		if(map.size()>0) {
			System.out.println("�û����Ѵ���!");
			resp.getWriter().write("0");//�û����Ѵ���
		}else {
			resp.getWriter().write("1");//�û�������
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
