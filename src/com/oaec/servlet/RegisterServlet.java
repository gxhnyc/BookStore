package com.oaec.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.pojo.Accounts;
import com.oaec.service.RegisterService;

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RegisterService rs = new RegisterService();// newһ��ע����������

		String username = req.getParameter("username");// ��ȡע���û���

		String password = req.getParameter("password");
		String email = req.getParameter("email");
		System.out.println("username:" + username + ",password:" + password + ",email:" + email);

		Accounts account = new Accounts();

		account.setAccount(username);
		account.setPassword(password);
		account.setEmail(email);
		
		int num = rs.addUserDao(account);
		if (num != 1) {
			System.out.println("ע��ʧ��,������ע��!");
		} else {
			System.out.println("��ϲ��,ע��ɹ�!");
			//1.ע��ɹ����Զ���¼
			req.getSession().setAttribute("account", account.getAccount());
			req.getSession().setAttribute("password", account.getPassword());
			// req.getRequestDispatcher("index.jsp").forward(req, resp);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			//2.ע��ɹ���������ת��ע���¼����,��Ҫ���µ�¼
			//resp.sendRedirect("./pages/login_register.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
