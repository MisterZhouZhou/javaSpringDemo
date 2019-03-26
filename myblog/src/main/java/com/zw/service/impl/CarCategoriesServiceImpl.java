package com.zw.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.mapper.CarCategoryMapper;
import com.zw.model.Car;
import com.zw.model.CarCategory;
import com.zw.service.CarCategoriesService;
import com.zw.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe: CarCategories数据交互
 */
@Service
public class CarCategoriesServiceImpl implements CarCategoriesService {

    @Autowired
    private CarCategoryMapper carCategoryMapper;

    @Autowired
    private CarService carService;

    @Override
    public List<CarCategory> findCarCategories() {
        return carCategoryMapper.findCarCategories();
    }

    @Override
    public JSONObject findCarCategories(int pageSize, int pageNum) {

        PageHelper.startPage(pageNum,pageSize);
        List<CarCategory> carCategories = carCategoryMapper.findCarCategories();
        PageInfo<CarCategory> pageInfo = new PageInfo<>(carCategories);
        List<JSONObject> carsInfo = new ArrayList<>();
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

        // pageInfo
        JSONObject thisPageInfo = new JSONObject();
        thisPageInfo.put("pageNum",pageInfo.getPageNum());
        thisPageInfo.put("pageSize",pageInfo.getPageSize());
        thisPageInfo.put("total",pageInfo.getTotal());
        thisPageInfo.put("pages",pageInfo.getPages());
        thisPageInfo.put("isFirstPage",pageInfo.isIsFirstPage());
        thisPageInfo.put("isLastPage",pageInfo.isIsLastPage());

        JSONObject resultJson = new JSONObject();
        resultJson.put("data", carsInfo);
        resultJson.put("pageInfo", thisPageInfo);

        return resultJson;
    }

    @Override
    public int insertCategory(CarCategory carCategory) {
        try {
            carCategoryMapper.insertCarCategory(carCategory);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public CarCategory findCarCategoriesById(int carCategoryId) {
        return carCategoryMapper.findCarCategoryById(carCategoryId);
    }
}
