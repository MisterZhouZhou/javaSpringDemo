package com.zw.login.service.impl;

import com.zw.login.dao.UserDao;
import com.zw.login.entity.UserPO;
import com.zw.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:zw
 * @Date: Created in 13:43 2018/1/17
 * @Description:实现用户service
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(UserPO userPO) {
        userDao.insert(userPO);
    }

    @Override
    public UserPO getUser(String name, String password) {
        return null;
    }

    @Override
    public UserPO getName(String name) {
        return null;
    }
}
