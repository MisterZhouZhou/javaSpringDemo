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
    public List<Car> findCarsByCarCategoryId(int carCategoryId) {
        return carMapper.findCarsByCarCategoryId(carCategoryId);
    }

    @Override
    public int insertCar(Car car) {
        try {
            carMapper.insertCar(car);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
