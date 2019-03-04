package com.didispace.web;

import com.didispace.domain.User;
import com.didispace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/create")
    public String create(@RequestBody User user) {
        userService.create(user);
        return "scuess";
    }

    @PostMapping(value = "/delete/{name}")
    public String deleteByName(@PathVariable String name){
        userService.deleteByName(name);
        return "scuess";
    }

    /**
     * 获取用户总量
     */
    @GetMapping(value = "/getUserCount")
    public Integer getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(value = "/deleteAllUser")
    public String deleteAllUsers(){
        userService.deleteAllUsers();
        return "scuess";
    }



}
