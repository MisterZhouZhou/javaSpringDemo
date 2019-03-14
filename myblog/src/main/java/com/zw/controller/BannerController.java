package com.zw.controller;

import com.zw.model.Banner;
import com.zw.response.ResultBody;
import com.zw.service.BannerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @ApiOperation(value="获得所有banner信息", notes="")
    @GetMapping("/getBanners")
    public ResultBody getBanners(){
         return new ResultBody(bannerService.findBanners());
    }


    /**
     * 获取所有的banner数量
     * @return
     */
    @ApiIgnore
    @GetMapping("/getBannersCount")
    public int getBannersCount(){
        return bannerService.countBannersNum();
    }

    /**
     * 新增banner
     * params
     * @return
     */
    @ApiOperation(value="提交banner信息", notes="")
    @ApiImplicitParam(name = "banner", value = "轮播图banner", required = true, dataType = "Banner")
    @PostMapping("/addBanner")
    public JSONObject addBanner(@RequestBody Banner banner){
        return bannerService.addBanner(banner);
    }


}
