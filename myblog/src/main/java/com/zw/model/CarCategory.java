package com.zw.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * banner
 */
@Data
public class CarCategory {
    /**
     * 车辆分类id
     */
    private int id;

    /**
     * 车辆分类名称
     */
    @ApiModelProperty(value = "车辆分类名称")
    private String carCategoryName;

    /**
     * 车辆分类图片
     */
    @ApiModelProperty(value = "车辆分类图标")
    private String carCategoryIcon;

    /**
     * 车辆分类价格
     */
    @ApiModelProperty(value = "车辆分类价格范围")
    private String carCategoryPrice;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarCategoryName() {
        return carCategoryName;
    }

    public void setCarCategoryName(String carCategoryName) {
        this.carCategoryName = carCategoryName;
    }

    public String getCarCategoryIcon() {
        return carCategoryIcon;
    }

    public void setCarCategoryIcon(String carCategoryIcon) {
        this.carCategoryIcon = carCategoryIcon;
    }

    public String getCarCategoryPrice() {
        return carCategoryPrice;
    }

    public void setCarCategoryPrice(String carCategoryPrice) {
        this.carCategoryPrice = carCategoryPrice;
    }
}
