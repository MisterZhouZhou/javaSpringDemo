package com.zw.service;

import com.zw.model.CarCategories;
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
    List<CarCategories> findCarCategories();
}
