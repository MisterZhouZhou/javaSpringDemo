package com.didispace.service;

import com.didispace.dao.UserDao;
import com.didispace.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUser(){
        return userDao.getAllUser();
    }

    public User getUser(Integer id) {
        List<User> list = getAllUser();
        for (int i=0;i<list.size();i++){
            User user = list.get(i);
            if(id == user.getId()){
                return user;
            }
        }
        return null;
    }
}
