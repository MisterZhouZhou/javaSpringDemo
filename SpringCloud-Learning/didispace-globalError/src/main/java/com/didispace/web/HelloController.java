package com.didispace.web;

import com.didispace.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(value = "users")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @RequestMapping("")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

    @RequestMapping("/")
    public String index2(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }


}