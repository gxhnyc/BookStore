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
		//System.out.println("进入doFilter方法");
		//1.将 arg1,arg2强制转换
		HttpServletRequest req=(HttpServletRequest)arg1;
		HttpServletResponse resp=(HttpServletResponse)arg2;
		
		//2.设置req,resp的编码格式
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//3.调用FilterChain 对象的 doFilter()方法,放行至下一个过滤器
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//System.out.println("过滤器创建啦");
		
	}

	@Override
	public void destroy() {
		
		//System.out.println("过滤器销毁啦");
	}

}
