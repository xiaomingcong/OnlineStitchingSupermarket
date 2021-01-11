package com.xmc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/2 10:34 下午
 * Version 1.0
 */
@Controller
public class ErrorController {

    @RequestMapping(value="/error",method= RequestMethod.GET)
    public String error(){
        return "error page";
    }
}
