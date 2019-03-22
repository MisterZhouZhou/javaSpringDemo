package com.zw.model;

import lombok.Data;

/***
 * banner
 */
@Data
public class CarCategories {
    /**
     * 车辆分类id
     */
    private int id;

    /**
     * 车辆分类名称
     */
    private String carCategoriesName;

    /**
     * 车辆分类图片
     */
    private String carCategoriesIcon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarCategoriesName() {
        return carCategoriesName;
    }

    public void setCarCategoriesName(String carCategoriesName) {
        this.carCategoriesName = carCategoriesName;
    }

    public String getCarCategoriesIcon() {
        return carCategoriesIcon;
    }

    public void setCarCategoriesIcon(String carCategoriesIcon) {
        this.carCategoriesIcon = carCategoriesIcon;
    }
}
