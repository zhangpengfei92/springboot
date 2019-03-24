package com.zpf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpf.pojo.User;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/getuser")
	public String getUser(Model model) {
		System.out.println(111);
		List<User> list = new ArrayList<User>();
		list.add(new User("1", "tom", "123456"));
		list.add(new User("2", "jay", "123456"));
		list.add(new User("3", "sini", "123456"));		
		model.addAttribute("list", list);		
		return "list";
	}
}
