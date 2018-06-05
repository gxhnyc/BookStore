package com.oaec.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.pojo.Books;
import com.oaec.service.IndexService;
/**
 * 处理index.jsp请求过来的数据
 * @author Yechao
 *
 */
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("IndexServlet-----doPost()");
		//1.调用业务逻辑模块,service
		IndexService is=new IndexService();
		
		//2.判断req.getParameter("pagenum");是否为空,为空表示是第一次请求
		String num=req.getParameter("pagenum");//获取jsp页面的pagenum参数
		
		List<Books> list=null;//用来接收查询到的数据
		
		if(num!=null) {
			System.err.println(num+"-------------");
			int pagenum = Integer.parseInt(num);
			list=is.loadBooks(pagenum);
			req.getSession().setAttribute("pagenum", pagenum);
		}else {
			//num为空,表示第一次加载
			list =is.loadBooks(1);
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
