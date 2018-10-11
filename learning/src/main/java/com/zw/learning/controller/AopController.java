package com.zw.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/aop")
public class AopController {

    @RequestMapping(value = "/testAop")
    public String testAop() {
        return "success";
    }
}
