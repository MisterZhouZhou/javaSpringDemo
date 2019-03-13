package com.zw.controller;

import com.zw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HelloController {

    @Autowired
    UserMapper userMapper;

    @ApiIgnore
    @GetMapping("/index")
    public String index(){

        userMapper.insertRole(16, 3);
        return "success";
    }


}
