package com.zpf.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping("/upload")
	public String upload(MultipartFile file) throws Exception {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		System.out.println(file.getBytes().toString());
		file.transferTo(new File("F:\\"+file.getOriginalFilename()));
		
		return "F:\\"+file.getOriginalFilename();
	}
}
