package com.zw.learning.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.learning.entity.User;
import com.zw.learning.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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





}
