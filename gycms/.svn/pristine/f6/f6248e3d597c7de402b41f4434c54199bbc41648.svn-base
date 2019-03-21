package com.jcl.gycms;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.jcl.gycms.mapper"})
public class GycmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GycmsApplication.class, args);
    }
    
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件最大  
        factory.setMaxFileSize("10240KB"); //KB,MB  
        //设置总上传数据总大小  
        factory.setMaxRequestSize("102400KB");  
        return factory.createMultipartConfig();  
    }  
}
