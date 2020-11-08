package com.xmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(name = "/login" ,method = RequestMethod.POST)
    public String login(){
        return "success";
    }
}
