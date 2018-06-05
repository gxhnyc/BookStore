package com.oaec.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.pojo.Books;
import com.oaec.service.IndexService;
import com.oaec.service.NextService;

/**
 * �����ҳ��servlet
 * 
 * @author Yechao
 *
 */
public class NextServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("---nextservlet----");
		// 1.����ҵ���߼�ģ��,service
		NextService is = new NextService();

		// 2.�ж�req.getParameter("pagenum");�Ƿ�Ϊ��,Ϊ�ձ�ʾ�ǵ�һ������
		String num = req.getParameter("pagenum");// ��ȡjspҳ���pagenum����

		List<Books> list = null;// �������ղ�ѯ��������

		if (num != null) {
			System.err.println(num + "-------------");
			int pagenum = Integer.parseInt(num);
			list = is.loadBooks(pagenum);
			req.getSession().setAttribute("pagenum", pagenum);
		} else {
			// numΪ��,��ʾ��һ�μ���
			list = is.loadBooks(1);
			req.getSession().setAttribute("pagenum", 1);
		}

		req.setAttribute("bookList", list);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
