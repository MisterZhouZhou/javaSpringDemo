package com.zw.service.impl;

import com.zw.mapper.CarCategoriesMapper;
import com.zw.mapper.CarMapper;
import com.zw.mapper.CategoryMapper;
import com.zw.model.Car;
import com.zw.model.CarCategories;
import com.zw.service.CarCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe: CarCategories数据交互
 */
@Service
public class CarCategoriesServiceImpl implements CarCategoriesService {

    @Autowired
    private CarCategoriesMapper carCategoriesMapper;


    @Override
    public List<CarCategories> findCarCategories() {
        return carCategoriesMapper.findCarCategories();
    }
}
