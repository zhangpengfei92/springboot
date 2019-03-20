package com.zpf.controller;

import java.util.HashMap;
import java.util.Map;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ShowController {
	@RequestMapping("/showhello")
	@ResponseBody
	public  Map<String, Object> showHello(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("helloworld", "Spring_Boot");
		return map;
	}
}
