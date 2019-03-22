package com.zw.model;

import lombok.Data;

/***
 * banner
 */
@Data
public class Car {

    /**
     * 车辆所属分类id
     */
    private int carCategoriesId;

    /**
     * 车辆名称
     */
    private String carName;

    /**
     * 车辆长度
     */
    private double carLength;

    /**
     * 车辆颜色
     */
    private String carColor;

    /**
     * 车辆价格范围
     */
    private String carPrice;
}
