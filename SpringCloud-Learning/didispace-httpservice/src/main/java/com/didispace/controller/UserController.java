package com.didispace.controller;

import com.didispace.entity.User;
import com.didispace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/memeber")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUser")
    public User getUser(Integer id){
        return userService.getUser(id);
    }
}
