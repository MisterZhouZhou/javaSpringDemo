package com.zw.service;

import com.zw.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:banner业务操作
 */
@Service
public interface CarService {

    /**
     * 根据车辆分类id获得所有的车辆信息
     * @return
     */
    List<Car> findCarsByCarCategoriesId(int carCategoriesId);

}
