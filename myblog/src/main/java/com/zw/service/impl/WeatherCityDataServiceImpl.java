package com.zw.service.impl;

import com.zw.model.WeatherCity;
import com.zw.model.WeatherCityList;
import com.zw.model.WeatherCityRoot;
import com.zw.service.WeatherCityDataService;
import com.zw.utils.XmlBuilder;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Service
public class WeatherCityDataServiceImpl implements WeatherCityDataService {
    @Override
    public List<WeatherCity> listCity() throws Exception {
        // 文件加载第一种方式
        // resources/static目录下使用
        // ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // InputStream inputStream = this.getClass().getResourceAsStream("/xml/citylist.xml");
        //读取XML文件
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        // 文件加载第二种方式
        BufferedReader bufferedReader = new BufferedReader(new FileReader(ResourceUtils.getURL("classpath:").getPath()+"/xml/citylist.xml"));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }
        bufferedReader.close();
        // xml转java对象
        WeatherCityRoot weatherCityRoot = (WeatherCityRoot)XmlBuilder.xmlStrToObject(WeatherCityRoot.class, stringBuffer.toString());
        List<WeatherCityList> cityList = weatherCityRoot.getWeatherCityList();

        return cityList.get(0).getWeatherCityList();
    }
}
