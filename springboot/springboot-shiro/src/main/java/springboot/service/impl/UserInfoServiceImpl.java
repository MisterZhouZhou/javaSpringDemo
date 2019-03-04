package springboot.service.impl;

import org.springframework.stereotype.Service;
import springboot.entity.UserInfo;
import springboot.service.UserInfoService;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoService userInfoService;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoService.findByUsername(username);
    }
}
