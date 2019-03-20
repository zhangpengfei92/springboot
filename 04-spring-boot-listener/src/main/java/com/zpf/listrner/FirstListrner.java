package com.zpf.listrner;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
/*
 * <listener>
 * 	<listener-class>com.zpf.listrner.FirstListrner</listener-class>
 * </listrner>
 * 
 */

@WebListener
public class FirstListrner implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	/*	Map<String, ? extends ServletRegistration> servletRegistrations 
		= sce.getServletContext().getServletRegistrations();
		Iterator<Map> iterator*/
		System.out.println("init----------------------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Destroyed----------------------");
		
	}

}
