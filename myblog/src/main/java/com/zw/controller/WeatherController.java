package com.zw.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.model.Weather;
import com.zw.response.ResultBody;
import com.zw.service.WeatherCityDataService;
import com.zw.service.WeatherDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/weather")
public class WeatherController {

    @Autowired
    private WeatherDateService weatherDateService;

    @Autowired
    private WeatherCityDataService weatherCityDataService;

    /**
     * 获取web天气
     * @return
     */
    @GetMapping("/city/{cityId}")
    public ModelAndView getWeatherCityId(@PathVariable("cityId") String cityId, Model model) throws Exception{
        JSONObject weatherObject = weatherDateService.getDateByCityId(cityId);
        if(String.valueOf(weatherObject.get("status")).equals("1000")){
            Weather weather = JSONObject.parseObject(JSONObject.toJSONString(weatherObject.get("data")), Weather.class);
            model.addAttribute("title","Lucifer的天气预报");
            model.addAttribute("cityId",cityId);
            model.addAttribute("cityList",weatherCityDataService.listCity());
            model.addAttribute("report",weather);
            return new ModelAndView("weather","reportModel",model);
        }
        return new ModelAndView("404");
    }


    /**
     * 根据城市信息
     * @return
     */
    @GetMapping("/citys")
    @ResponseBody
    public ResultBody getWeatherCity() throws Exception{
        return new ResultBody(weatherCityDataService.listCity());
    }


    /**
     * 根据城市id获取天气信息
     * @return
     */
    @GetMapping("/citys/{cityId}")
    @ResponseBody
    public JSONObject getWeatherByCitysId(@PathVariable("cityId") String cityId){
        return weatherDateService.getDateByCityId(cityId);
    }

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
