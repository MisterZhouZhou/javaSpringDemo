package com.didispace.web;

import com.didispace.service.BlogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
public class HelloController {

    @Autowired
    private BlogProperties blogProperties;

    @GetMapping("/index")
    public void index(){
        System.out.println("name="+blogProperties.getName());
        System.out.println("title="+blogProperties.getTitle());
        System.out.println("desc="+blogProperties.getDesc());

        System.out.println("随机数测试输出：");
        System.out.println("随机字符串 : " + blogProperties.getValue());
        System.out.println("随机int : " + blogProperties.getNumber());
        System.out.println("随机long : " + blogProperties.getBignumber());
        System.out.println("随机10以下 : " + blogProperties.getTest1());
        System.out.println("随机10-20 : " + blogProperties.getTest2());
    }

    @RequestMapping("/hello")
    public String hello() {

        System.out.println("name="+blogProperties.getName());
        System.out.println("title="+blogProperties.getTitle());
        System.out.println("desc="+blogProperties.getDesc());

        System.out.println("随机数测试输出：");
        System.out.println("随机字符串 : " + blogProperties.getValue());
        System.out.println("随机int : " + blogProperties.getNumber());
        System.out.println("随机long : " + blogProperties.getBignumber());
        System.out.println("随机10以下 : " + blogProperties.getTest1());
        System.out.println("随机10-20 : " + blogProperties.getTest2());


        return "Hello World";
    }

}