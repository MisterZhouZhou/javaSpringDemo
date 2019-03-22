package com.zw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zw.model.Car;
import com.zw.model.CarCategories;
import com.zw.response.ResultBody;
import com.zw.service.CarCategoriesService;
import com.zw.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 车辆信息
 */
@RestController
@RequestMapping(value = "/carInfo")
public class CarInfoController {

    @Autowired
    private CarCategoriesService carCategoriesService;

    @Autowired
    private CarService carService;


    @GetMapping(value = "/getAllCarsInfo")
    public ResultBody getAllCarsInfo(){
        List<JSONObject> carsInfo = new ArrayList<JSONObject>();
        List<CarCategories> carCategories = carCategoriesService.findCarCategories();
        for (CarCategories carCategory : carCategories){
            JSONObject jsonObject = new JSONObject();
            List<Car> cars = carService.findCarsByCarCategoriesId(carCategory.getId());
            jsonObject.put("carCategoryId",carCategory.getId());
            jsonObject.put("carCategoryName",carCategory.getCarCategoriesName());
            jsonObject.put("carCategoryIcon",carCategory.getCarCategoriesIcon());
            jsonObject.put("cars",cars);
            carsInfo.add(jsonObject);
        }
        return new ResultBody(carsInfo);
    }
}
