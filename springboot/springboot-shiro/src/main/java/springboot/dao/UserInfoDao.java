package springboot.dao;

import org.springframework.data.repository.CrudRepository;
import springboot.entity.UserInfo;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
}
