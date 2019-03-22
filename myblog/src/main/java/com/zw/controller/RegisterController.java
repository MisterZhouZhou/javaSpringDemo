package com.zw.controller;

import com.zw.model.User;
import com.zw.service.UserService;
import com.zw.utils.MD5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: 2018/6/4 11:48
 * Describe: 注册方法
 */
@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @ApiIgnore
    @PostMapping("/register")
    @ResponseBody
    public String register(User user, HttpServletRequest request){

        String authCode = request.getParameter("authCode");

        String trueMsgCode = (String) request.getSession().getAttribute("trueMsgCode");

        //检测手机验证码
        if(!authCode.equals(trueMsgCode)){
            return "0";
        }
        if(userService.usernameIsExit(user.getUsername())){
            return "3";
        }
        //注册时对密码进行MD5加密
        MD5Util md5Util = new MD5Util();
        user.setPassword(md5Util.encode(user.getPassword()));
        return userService.insert(user);
    }


    /**
     * web,移动端统一以json形式请求
     * @param user
     * @return
     */
    @ApiOperation(value="注册用户信息", notes="")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "body")
    @PostMapping("/register_new")
    @ResponseBody
    public String register_new(@RequestBody User user) {
        if(userService.usernameIsExit(user.getUsername())){
            return "3";
        }
        //注册时对密码进行MD5加密
        MD5Util md5Util = new MD5Util();
        user.setPassword(md5Util.encode(user.getPassword()));
        return userService.insert(user);
    }

}
