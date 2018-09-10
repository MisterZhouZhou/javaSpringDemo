package com.winner.winnertest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello World! zw";
    }

    @RequestMapping("/result")
    public String result(Model model){
        model.addAttribute("message", "Hello from the controller");
        return "resultPage";
    }

    @RequestMapping("/sayname")
    public String sayname(@RequestParam("name") String userName, Model model){
        model.addAttribute("message", "Hello,"+userName);
        return "resultPage";
    }


    @RequestMapping("/defaultParams")
    public String defaultParams(@RequestParam(defaultValue = "world") String name, Model model){
        model.addAttribute("message", "Hello,"+name);
        return "resultPage";
    }

}
