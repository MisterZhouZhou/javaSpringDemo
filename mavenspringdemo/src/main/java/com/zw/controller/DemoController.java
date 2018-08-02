package com.zw.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zw")
public class DemoController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
