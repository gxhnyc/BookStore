package com.oaec.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.pojo.Accounts;
import com.oaec.pojo.CartDetails;
import com.oaec.service.CartService;
import com.oaec.service.LoginService;
import com.oaec.service.RegisterService;

import net.sf.json.JSONArray;

public class LoginServlet extends HttpServlet {
	//private static int logflag;//登录标志

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		LoginService ls = new LoginService();// new一个注册服务类对象

		
		String op=req.getParameter("op");
		//1.op==
		if(op!=null&&op.equals("登录")) {
			String username = req.getParameter("logname");// 获取注册用户名
			String password = req.getParameter("logpwd");
			//System.out.println("username:" + username + ",password:" + password);
			Map<String,Object> userMap = ls.userLogin(username, password);//调用服务层的userLogin()方法,进行登录判断,返回用户信息(map)
			int lognum=userMap.size();
			if (lognum > 0) {
				System.out.println("恭喜您,登录成功!"+userMap);
				System.out.println("logflag:"+lognum);
				req.getSession().setAttribute("logflag", lognum);
				req.getSession().setAttribute("userMap", userMap);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
	
			} else {
				System.out.println("登录失败,请重新登录!");
				System.out.println("logflag:"+lognum);
				req.getSession().setAttribute("logflag", lognum);
				resp.sendRedirect("pages/login_register.jsp");
			}
		}else{//找回密码-----------------------------------------------------------------
			System.out.println("找回密码----------");
			String username = req.getParameter("findname");// 获取需要找回密码的用户名
			String email = req.getParameter("findemail");//获取对应邮箱
			System.out.println("username:" + username + ",email:" + email);
			
			
			//若找到相应用户数据
			Map<String,Object> fuserMap =ls.findPWD(username,email);
			
			if(fuserMap.size()>0) {	
				System.out.println("LoginServlet:(跳转至成功找回密码)" + fuserMap);
				/*req.setAttribute("findmap", fuserMap);
				req.getRequestDispatcher("sucfindpwd.jsp").forward(req, resp);*/
				JSONArray jarr = JSONArray.fromObject(fuserMap);
				resp.getWriter().println(jarr);			
				
			}
			else {				
				System.out.println("账户不存在！");	
				
				JSONArray jarr = JSONArray.fromObject(1);
				resp.getWriter().println(jarr);			
			}			
		}

}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
