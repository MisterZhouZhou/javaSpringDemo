package com.zw.service;

import com.zw.model.WeatherCity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherCityDataService {
    /**
     * 获取xml城市数据
     * @return
     */
    List<WeatherCity> listCity() throws Exception;
}
