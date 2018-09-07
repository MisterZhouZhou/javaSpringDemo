package com.zw.login.service;

import com.zw.login.entity.UserPO;

/**
 * @Author:zw
 * @Date: Created in 13:42 2018/1/17
 * @Description:用户service接口
 */
public interface UserService {
    /**
     * @Description:用户注册
     * @param: userPO
     * @return:
     */
    void save(UserPO userPO);

    /**
     * @Description:用户登陆
     * @param: name
     * @param:password
     * @return:UserPO
     */
    UserPO getUser(String name, String password);

    /**
     * @Description:通过用户名进行查找
     * @param: name
     * @return:UserPO
     */
    UserPO getName(String name);
}
