package com.jcl.gycms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/login")
@Controller
public class LoginController {

    @RequestMapping
    public String login(){
        return "login";
    }

}
