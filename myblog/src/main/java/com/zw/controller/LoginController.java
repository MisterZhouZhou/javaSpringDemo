package com.zw.controller;

import com.zw.model.User;
import com.zw.service.UserService;
import com.zw.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: 2018/6/8 9:24
 * Describe: 登录控制
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @ApiIgnore
    @ResponseBody
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("phone") String phone,
                                 @RequestParam("authCode") String authCode,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpServletRequest request){

        String trueMsgCode = (String) request.getSession().getAttribute("trueMsgCode");

        if(!authCode.equals(trueMsgCode)){
            return "0";
        }
        User user = userService.findUserByPhone(phone);
        if(user == null){
            return "2";
        }
        MD5Util md5Util = new MD5Util();
        String MD5Password = md5Util.encode(newPassword);
        userService.updatePasswordByPhone(phone, MD5Password);

        return "1";
    }

}