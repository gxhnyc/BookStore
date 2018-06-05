package com.oaec.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.service.RegisterService;
/**
 * 处理用户名验证的servlet
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
			System.out.println("用户名已存在!");
			resp.getWriter().write("0");//用户名已存在
		}else {
			resp.getWriter().write("1");//用户名可用
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
