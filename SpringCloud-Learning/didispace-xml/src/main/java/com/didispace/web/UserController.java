package com.didispace.web;

import com.didispace.domain.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //  注意使用xml形式进行提交
    //  <User>
    //	  <name>zw</name>
    //	  <age>10</age>
    //  </User>
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public User create(@RequestBody User user){
        user.setName("didispace.com : "+user.getName());
        user.setAge(user.getAge() + 100);
        return user;
    }
}
