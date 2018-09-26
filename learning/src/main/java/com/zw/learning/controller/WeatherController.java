package com.zw.learning.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zw.learning.entity.User;
import com.zw.learning.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/weather")
public class WeatherController {

    @RequestMapping(value = "/getCityWeather", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCityWeather(String city){
        String url = "http://wthrcdn.etouch.cn/weather_mini?city="+city;
        // 字符串转json
        JSONObject jsonObject = HttpUtil.httpGet(url);
        return jsonObject;
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(@RequestBody JSONObject jsonObject){
        User user = jsonObject.toJavaObject(User.class);
        JSONObject ojb = HttpUtil.httpPost("http://127.0.0.1:8088/user/api/regist", jsonObject);
        return ojb;
    }


}
