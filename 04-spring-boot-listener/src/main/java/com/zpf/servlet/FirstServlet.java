package com.zpf.servlet;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 如何使用servlet开发
 * 1.配置servlet节点
 *<servlet> 
 *	<servlet-name>FirstServlet</servlet-name>
 *	<servlet-class>com.zpf.servlet.FirstServlet</servlet-calss> 
 *</servlet>
 *2.配置servlet的mapping映射
 *<servlet-mapping>
 *	<servlet-name>FirstServlet</servlet-name>
 *	<url-pattern>first</url-pattern>
 *</servlet-mapping>
 */

/*
 * springbootservlet开发方式一
 */
/*
 * destroy与dodelete
 */
@WebServlet(name="FirstServlet",urlPatterns="/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是post方法");
		super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getParameter("str");
		if(str!=null&&str.equals("1")) {
			doDelete(req, resp);
		}else {
			destroy();
		}
		
		System.out.println("我是get方法");
		super.doPost(req, resp);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是put方法");
		super.doPut(req, resp);
	}
	@Override
	public void destroy() {
		System.out.println("我是destroy方法");
		super.destroy();
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是delete方法,你传的数字是"+req.getParameter("str")+",我这个servlet即将删除");
		super.doDelete(req, resp);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("我是init方法");
		super.init(config);
	}
}
