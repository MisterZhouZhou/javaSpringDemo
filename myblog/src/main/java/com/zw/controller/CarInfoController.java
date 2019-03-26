package com.zw.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.model.Car;
import com.zw.model.CarCategory;
import com.zw.response.ErrorInfoInterface;
import com.zw.response.GlobalErrorInfoEnum;
import com.zw.response.ResultBody;
import com.zw.service.CarCategoriesService;
import com.zw.service.CarService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = true, dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/getCarsInfos")
    public ResultBody getCarsInfos(HttpServletRequest request){
        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        JSONObject carCategories = carCategoriesService.findCarCategories(rows, pageNum);
        return new ResultBody(carCategories);
    }


    @GetMapping(value = "/getAllCarsInfo")
    public ResultBody getAllCarsInfo(HttpServletRequest request){
        List<JSONObject> carsInfo = new ArrayList<JSONObject>();
        List<CarCategory> carCategories = carCategoriesService.findCarCategories();
        for (CarCategory carCategory : carCategories){
            JSONObject jsonObject = new JSONObject();
            List<Car> cars = carService.findCarsByCarCategoryId(carCategory.getId());
            jsonObject.put("carCategoryId",carCategory.getId());
            jsonObject.put("carCategoryName",carCategory.getCarCategoryName());
            jsonObject.put("carCategoryIcon",carCategory.getCarCategoryIcon());
            jsonObject.put("carCategoryPrice",carCategory.getCarCategoryPrice());
            jsonObject.put("cars",cars);
            carsInfo.add(jsonObject);
        }
        return new ResultBody(carsInfo);
    }

    /**
     * 添加车辆分类
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carCategory", value = "车辆分类信息", required = true, dataType = "CarCategory", paramType = "body")
    })
    @PostMapping(value = "/addCarCategory")
    public ResultBody addCarCategory(@RequestBody CarCategory carCategory){
        int result = carCategoriesService.insertCategory(carCategory);
        if(result == 0){ // 插入失败
            return new ResultBody(GlobalErrorInfoEnum.FAILED);
        }
        return new ResultBody(carCategory);
    }

    /**
     * 添加车辆信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "car", value = "车辆信息", required = true, dataType = "Car", paramType = "body")
    })
    @PostMapping(value = "/addCar")
    public ResultBody addCar(@RequestBody Car car){
        CarCategory carCategory = carCategoriesService.findCarCategoriesById(car.getCarCategoryId());
        int result = 0;
        if(carCategory != null){
            result = carService.insertCar(car);
        }
        if(result == 0){ // 插入失败
            ErrorInfoInterface infoInterface = new ErrorInfoInterface() {
                @Override
                public String getCode() {
                    return "502";
                }
                @Override
                public String getMessage() {
                    return "该车辆分类不存在";
                }
            };
            return new ResultBody(infoInterface);
        }
        return new ResultBody(car);
    }

}
