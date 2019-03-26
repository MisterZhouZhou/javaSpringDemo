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
    private int carCategoryId;

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

    public int getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(int carCategoryId) {
        this.carCategoryId = carCategoryId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getCarLength() {
        return carLength;
    }

    public void setCarLength(double carLength) {
        this.carLength = carLength;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }
}
