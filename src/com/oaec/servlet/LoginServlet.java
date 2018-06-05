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
	//private static int logflag;//��¼��־

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		LoginService ls = new LoginService();// newһ��ע����������

		
		String op=req.getParameter("op");
		//1.op==
		if(op!=null&&op.equals("��¼")) {
			String username = req.getParameter("logname");// ��ȡע���û���
			String password = req.getParameter("logpwd");
			//System.out.println("username:" + username + ",password:" + password);
			Map<String,Object> userMap = ls.userLogin(username, password);//���÷�����userLogin()����,���е�¼�ж�,�����û���Ϣ(map)
			int lognum=userMap.size();
			if (lognum > 0) {
				System.out.println("��ϲ��,��¼�ɹ�!"+userMap);
				System.out.println("logflag:"+lognum);
				req.getSession().setAttribute("logflag", lognum);
				req.getSession().setAttribute("userMap", userMap);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
	
			} else {
				System.out.println("��¼ʧ��,�����µ�¼!");
				System.out.println("logflag:"+lognum);
				req.getSession().setAttribute("logflag", lognum);
				resp.sendRedirect("pages/login_register.jsp");
			}
		}else{//�һ�����-----------------------------------------------------------------
			System.out.println("�һ�����----------");
			String username = req.getParameter("findname");// ��ȡ��Ҫ�һ�������û���
			String email = req.getParameter("findemail");//��ȡ��Ӧ����
			System.out.println("username:" + username + ",email:" + email);
			
			
			//���ҵ���Ӧ�û�����
			Map<String,Object> fuserMap =ls.findPWD(username,email);
			
			if(fuserMap.size()>0) {	
				System.out.println("LoginServlet:(��ת���ɹ��һ�����)" + fuserMap);
				/*req.setAttribute("findmap", fuserMap);
				req.getRequestDispatcher("sucfindpwd.jsp").forward(req, resp);*/
				JSONArray jarr = JSONArray.fromObject(fuserMap);
				resp.getWriter().println(jarr);			
				
			}
			else {				
				System.out.println("�˻������ڣ�");	
				
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
