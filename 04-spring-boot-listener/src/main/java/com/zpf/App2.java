package com.zpf;

import java.util.EventListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.zpf.listrner.SecondListener;

@SpringBootApplication
public class App2 {
	
	public static void main(String[] args) {
		SpringApplication.run(App2.class, args);
	}
	
	@Bean
	public ServletListenerRegistrationBean<EventListener> getServletListenerRegistrationBean(){
		ServletListenerRegistrationBean<EventListener> bean=
				new ServletListenerRegistrationBean<EventListener>(new SecondListener());
		return bean;		
	}
}
