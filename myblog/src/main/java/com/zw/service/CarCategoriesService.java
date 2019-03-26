package com.zw.service;

import com.alibaba.fastjson.JSONObject;
import com.zw.model.CarCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:banner业务操作
 */
@Service
public interface CarCategoriesService {
    /**
     * 获得所有的车辆分类信息
     * @return
     */
    List<CarCategory> findCarCategories();

    /**
     * 分页获得所有的车辆分类信息
     * @return
     */
    JSONObject findCarCategories(int rows, int pageNum);

    /**
     * 添加车辆分类
     * @param carCategory 车辆分类
     * @return  status: 1--成功   0--失败
     */
    int insertCategory(CarCategory carCategory);

    /**
     * 通过车辆分类id车辆分类信息
     * @return
     */
    CarCategory findCarCategoriesById(int carCategoryId);

}
