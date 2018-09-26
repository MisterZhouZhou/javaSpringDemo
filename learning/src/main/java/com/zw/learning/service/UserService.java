package com.zw.learning.service;

import com.zw.learning.entity.User;

import java.util.List;

/**
 * @Author:zw
 * @Date: Created in 13:42 2018/1/17
 * @Description:用户service接口
 */
public interface UserService {
    /**
     * @Description:用户注册
     * @param: user
     * @return:
     */
    void save(User user);

    /**
     * @Description: 查找用户
     * @param: username
     * @param:password
     * @return:UserPO
     */
    User getUser(String username, String password);

    /**
     * @Description: 根据id查找用户
     * @param: id
     * @return:User
     */
    User getUserById(String id);

    /**
     * @Description:通过用户名进行查找
     * @param: username
     * @return:UserPO
     */
    User getName(String username);

    /**
     * @Description: 获取用户列表
     * @param:
     */
    List<User> getUserList();


    /**
     * @Description: 更新用户信息
     * @param: user
     * @return
     */
    void updateUser(User user);

    /**
     * @Description: 删除用户信息
     * @param: id
     * @return
     */
    void deleteUser(String id);
}
