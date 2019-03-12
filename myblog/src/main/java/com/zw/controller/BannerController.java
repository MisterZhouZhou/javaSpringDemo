package com.zw.controller;

import com.zw.model.Banner;
import com.zw.service.BannerService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Describe: banner
 */
@RestController
public class BannerController {

    @Autowired
    BannerService bannerService;

    /**
     * 获取所有的banner
     * @return
     */
    @GetMapping("/getBanners")
    public JSONObject getBanners(){
        return bannerService.findBanners();
    }


    /**
     * 获取所有的banner数量
     * @return
     */
    @GetMapping("/getBannersCount")
    public int getBannersCount(){
        return bannerService.countBannersNum();
    }

    /**
     * 新增banner
     * params
     * @return
     */
    @PostMapping("/addBanner")
    public JSONObject addBanner(@RequestBody Banner banner){
        return bannerService.addBanner(banner);
    }


}
