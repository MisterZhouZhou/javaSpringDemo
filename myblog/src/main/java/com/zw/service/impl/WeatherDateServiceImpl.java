package com.zw.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zw.service.WeatherDateService;
import com.zw.utils.HttpUtil;
import org.springframework.stereotype.Service;

@Service
public class WeatherDateServiceImpl implements WeatherDateService {

    private final String WEATHER_API="http://wthrcdn.etouch.cn/weather_mini";

    @Override
    public JSONObject getDateByCityId(String cityId) {
        String uri = WEATHER_API+"?citykey="+cityId;
        return HttpUtil.httpGet(uri);
    }

    @Override
    public JSONObject getDateByCityName(String cityName) {
        String uri = WEATHER_API+"?city="+cityName;
        return HttpUtil.httpGet(uri);
    }
}
