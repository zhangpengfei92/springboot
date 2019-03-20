package com.zpf;

import javax.servlet.annotation.WebServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*
 *spingboot进行servlet开发方式一的启动类 
 *
 */
@SpringBootApplication
//该注解在Springboot启动时会去扫描启动类同级一包下和下一级包下的@WebServlet,并且实例化
@ServletComponentScan
public class App1 {
		
	public static void main(String[] args) {
		SpringApplication.run(App1.class, args);
	}
}
