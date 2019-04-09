package com.zw.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface WeatherDateService {
    //根据城市id查询天气
    JSONObject getDateByCityId(String cityId);
    //根据城市名字查询天气
    JSONObject getDateByCityName(String cityName);
}
