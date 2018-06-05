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

		RegisterService rs = new RegisterService();// new一个注册服务类对象

		String username = req.getParameter("username");// 获取注册用户名

		String password = req.getParameter("password");
		String email = req.getParameter("email");
		System.out.println("username:" + username + ",password:" + password + ",email:" + email);

		Accounts account = new Accounts();

		account.setAccount(username);
		account.setPassword(password);
		account.setEmail(email);
		
		int num = rs.addUserDao(account);
		if (num != 1) {
			System.out.println("注册失败,请重新注册!");
		} else {
			System.out.println("恭喜您,注册成功!");
			//1.注册成功后自动登录
			req.getSession().setAttribute("account", account.getAccount());
			req.getSession().setAttribute("password", account.getPassword());
			// req.getRequestDispatcher("index.jsp").forward(req, resp);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			//2.注册成功后重新跳转至注册登录界面,需要重新登录
			//resp.sendRedirect("./pages/login_register.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
