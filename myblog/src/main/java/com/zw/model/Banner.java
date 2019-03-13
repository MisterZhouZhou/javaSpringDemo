package com.zw.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * banner
 */
@Data
public class Banner {
    @ApiModelProperty(value = "banner id(默认不传)")
    private int id;

    @ApiModelProperty(value = "banner标题")
    private String bannerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }
}
