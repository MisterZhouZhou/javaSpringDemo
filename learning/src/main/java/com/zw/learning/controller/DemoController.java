package com.zw.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @RequestMapping(value = "/{tmp}.jsp", method = RequestMethod.GET)
    public String helloJsp(){
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }


    @RequestMapping(value = "/getHello", method = RequestMethod.GET)
    @ResponseBody
    public String getHello(){
        return "hello world";
    }
}
