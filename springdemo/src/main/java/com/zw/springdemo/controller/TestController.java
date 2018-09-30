package com.zw.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello test";
    }
}
