package com.zw.learning.service.impl;

import com.zw.learning.dao.UserDao;
import com.zw.learning.entity.User;
import com.zw.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    @Override
    public void save(User user) {
        userdao.save(user);
    }

    @Override
    public User getUser(String username, String password) {
        return userdao.getUser(username, password);
    }

    @Override
    public User getUserById(String id) {
        return userdao.getUserById(id);
    }

    @Override
    public User getName(String username) {
        return userdao.getName(username);
    }

    @Override
    public List<User> getUserList() {
        return userdao.getUserList();
    }

    @Override
    public void updateUser(User user) {
        userdao.updateUser(user);
    }


    @Override
    public void deleteUser(String id) {
        userdao.deleteUser(id);
    }

}
