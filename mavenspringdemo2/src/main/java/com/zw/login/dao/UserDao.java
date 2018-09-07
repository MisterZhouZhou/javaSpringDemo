package com.zw.login.dao;

import com.zw.login.entity.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author:zw
 * @Date: Created in 13:39 2018/1/17
 * @Description:用户dao接口
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * @Description:用户注册
     * @param: userPO
     * @return:
     */
    void insert(UserPO userPO);
    /**
     * @Description:用户登陆
     * @param:name
     * @param:password
     * @return:UserPO
     */
    UserPO getUser(@Param(value = "name") String name, @Param(value = "password") String password);
    /**
     * @Description:通过用户名进行查找
     * @param: name
     * @return:UserPO
     */
    UserPO getName(String name);
}
