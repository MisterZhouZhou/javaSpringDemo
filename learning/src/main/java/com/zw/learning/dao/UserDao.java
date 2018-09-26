package com.zw.learning.dao;

import com.zw.learning.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
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
     * @return:User
     */
    User getUser(@Param(value = "username") String username, @Param(value = "password") String password);

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
     */
    void updateUser(User user);

    /**
     * @Description: 删除用户信息
     * @param: id
     * @return
     */
    void deleteUser(String id);
}
