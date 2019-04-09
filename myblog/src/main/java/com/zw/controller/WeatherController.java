package com.zw.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.response.ResultBody;
import com.zw.service.WeatherDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherDateService weatherDateService;

    /**
     * 根据城市id获取天气信息
     * @param cityId
     * @return
     */
    @GetMapping("/cityId/{cityId}")
    public ResultBody getWeatherByCityId(@PathVariable String cityId) {
        JSONObject weatherObject =  weatherDateService.getDateByCityId(cityId);
        return new ResultBody(weatherObject);
    }

    /**
     * 根据城市名称 获取天气信息
     * @param cityName
     * @return
     */
    @GetMapping("/cityName/{cityName}")
    public ResultBody getWeatherByCityName(@PathVariable String cityName) {
       JSONObject weatherObject =  weatherDateService.getDateByCityName(cityName);
       return new ResultBody(weatherObject);
    }
}
