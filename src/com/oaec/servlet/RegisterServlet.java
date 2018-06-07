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

		RegisterService rs = new RegisterService();// newһ一个RegisterService

		String username = req.getParameter("username");// 得到用户名

		String password = req.getParameter("password");
		String email = req.getParameter("email");
		System.out.println("username:" + username + ",password:" + password + ",email:" + email);

		Accounts account = new Accounts();

		account.setAccount(username);
		account.setPassword(password);
		account.setEmail(email);
		
		int num = rs.addUserDao(account);
		if (num != 1) {
			System.out.println("注册失败!");
		} else {
			System.out.println("注册成功!");
			//1.设值到session
			req.getSession().setAttribute("account", account.getAccount());
			req.getSession().setAttribute("password", account.getPassword());
			// req.getRequestDispatcher("index.jsp").forward(req, resp);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			//2.重定向
			//resp.sendRedirect("./pages/login_register.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
