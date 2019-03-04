package com.didispace.web;

import com.didispace.domain.Message;
import com.didispace.domain.User;
import com.didispace.repository.p.UserRepository;
import com.didispace.repository.s.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository2 userRepository2;


    @GetMapping(value = "/index")
    public String index(){

        // 往第一个数据源中插入两条数据
        userRepository.save(new User("aaa", 10, "aaa description"));
        userRepository.save(new User("bbb", 20, "bbb description"));
        userRepository.save(new User("ccc", 30, "ccc description"));
        userRepository.save(new User("ddd", 40, "ddd description"));
        userRepository.save(new User("eee", 50, "eee description"));

        userRepository2.save(new Message("eee", "eee description"));

        return "scuess";
    }



}
