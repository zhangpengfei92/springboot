package com.zpf.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/* 以前如何定义拦截器
 *<filter>
 *	<filter-name>FirstFilter</filter-name>
 * 	<filter-class>com.zpf.filter.FirstFilter</filter-class>
 * </filter>
 * <filter-mapping>
 * 	<filter-name>FirstFilter</filter-name>
 * 	<url-pattern>/first</url-pattern>
 * <filter-mapping>
 * 
 */
/*
 * springbbot整合filter方式一
 */
@WebFilter(filterName="FirstFilter",urlPatterns="/first")
public class FirstFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("被拦截了");
		System.out.println("Port"+request.getLocalPort());
		System.out.println("Encoding"+request.getCharacterEncoding());
		System.out.println("LocalAddr"+request.getLocalAddr());
		System.out.println("LocalName"+request.getLocalName());
		System.out.println("RemoteAddr"+request.getRemoteAddr());
		System.out.println("RemoteHost"+request.getRemoteHost());
		System.out.println("RemotePort"+request.getRemotePort());
		System.out.println("RealPath"+request.getRealPath("www.baidu.com"));
		System.out.println("端口"+request.getAttributeNames());
		Enumeration<String> attributeNames = request.getAttributeNames();
		while(attributeNames.hasMoreElements()){
		    String name=(String)attributeNames.nextElement();
		    String value=request.getParameter(name);
		    System.out.println("获取到的参数1"+name+":"+value);
		}
		
		Enumeration<String> parameterNames = request.getParameterNames();		
		while(parameterNames.hasMoreElements()){
		    String name=(String)parameterNames.nextElement();
		    String value=request.getParameter(name);
		    System.out.println("获取到的参数2"+name+":"+value);
		}
		
		//System.out.println("---"+request.getParameterNames().toString());
		chain.doFilter(request, response);
		System.out.println("又被放出来了");
	}

	@Override
	public void destroy() {
		
		
	}
	
}
