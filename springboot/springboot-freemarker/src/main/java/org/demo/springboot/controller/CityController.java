package org.demo.springboot.controller;

import org.demo.springboot.domain.City;
import demo.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Controller
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    // @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/city/{id}")
    public String findOneCity(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList",cityList);
        return "cityList";
    }

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    @ResponseBody  // 想返回json需使用这个修饰
    public String createCity(@RequestBody City city) {
        cityService.saveCity(city);
        return "success";
    }


}
