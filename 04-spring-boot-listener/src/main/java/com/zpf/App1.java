package com.zpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
/*
 * Springboot整合listener方式一
 */
@SpringBootApplication
@ServletComponentScan
public class App1 {

	public static void main(String[] args) {
		SpringApplication.run(App1.class, args);
	}
}
