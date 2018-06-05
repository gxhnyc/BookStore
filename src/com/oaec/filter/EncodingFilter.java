package com.oaec.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Yechao
 *
 */
public class EncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg1, ServletResponse arg2, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("����doFilter����");
		//1.�� arg1,arg2ǿ��ת��
		HttpServletRequest req=(HttpServletRequest)arg1;
		HttpServletResponse resp=(HttpServletResponse)arg2;
		
		//2.����req,resp�ı����ʽ
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//3.����FilterChain ����� doFilter()����,��������һ��������
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//System.out.println("������������");
		
	}

	@Override
	public void destroy() {
		
		//System.out.println("������������");
	}

}
