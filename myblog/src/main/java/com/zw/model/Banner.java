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

    @ApiModelProperty(value = "banner是否显示")
    private boolean show;

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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
