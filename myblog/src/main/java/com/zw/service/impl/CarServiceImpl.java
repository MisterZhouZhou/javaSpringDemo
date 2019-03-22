package com.zw.service.impl;

import com.zw.mapper.CarMapper;
import com.zw.model.Car;
import com.zw.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe: Car数据交互
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> findCarsByCarCategoriesId(int carCategoriesId) {
        return carMapper.findCarsByCarCategoriesId(carCategoriesId);
    }

}
