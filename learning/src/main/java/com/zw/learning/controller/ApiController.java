package com.zw.learning.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zw.learning.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUserList(){
        JSONObject ojb = HttpUtil.httpGet("http://127.0.0.1:8088/user/api/getAllUser");
        return ojb;
    }
}
