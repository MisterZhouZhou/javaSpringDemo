package com.zw.model;

import lombok.Data;

/***
 * banner
 */
@Data
public class Banner {
    private int id;
    /**
     * 归档日期
     */
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
