package com.didispace.controller;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/findAllUser")
    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }

    @GetMapping(value = "/findUserByName")
    public User findUserByName(@RequestParam(value = "name") String name){
       return userMapper.findByName(name);
    }

    @PostMapping(value = "/createUser")
    public int createUser(@RequestBody User user){
        return userMapper.insert(user.getName(), user.getAge());
    }
//    public int createUser(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age){
//        return userMapper.insert(name, age);
//    }
}
