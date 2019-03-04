package com.didispace.service;

import com.didispace.domain.User;

public interface UserService {

    /**
     * 新增一个用户
     * @param user
     */
    void create(User user);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

}
