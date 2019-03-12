package com.zw.repository.mybatis;

import com.zw.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangocean
 * @Date: 2018/6/5 19:37
 * Describe:
 */
@Repository
public interface UserRepository {

    /**
     *  通过手机号查找用户
     * @param phone 手机号
     * @return 用户
     */
    User findByPhone(String phone);

}
