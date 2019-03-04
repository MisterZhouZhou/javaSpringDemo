package com.didispace.web;

import com.didispace.entity.User;
import com.didispace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }
}
