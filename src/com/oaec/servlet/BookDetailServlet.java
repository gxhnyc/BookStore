package com.oaec.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oaec.service.BookDetailService;

/**
 * 
 * @author Yechao
 *
 */
public class BookDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("----bookdetailservlet-----");
		String id=req.getParameter("id");
		BookDetailService bdt=new BookDetailService();
		
		Map<String,Object> map=bdt.findBookByID(id);
		req.setAttribute("book", map);
		
		req.getRequestDispatcher("pages/book/detail.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
