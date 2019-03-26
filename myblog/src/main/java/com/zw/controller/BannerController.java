package com.zw.controller;

import com.zw.model.Banner;
import com.zw.response.GlobalErrorInfoEnum;
import com.zw.response.ResultBody;
import com.zw.service.BannerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    public ResultBody addBanner(@RequestBody Banner banner){
        int result = bannerService.addBanner(banner);
        if(result == 0){ // 插入失败
            return new ResultBody(GlobalErrorInfoEnum.FAILED);
        }
        return new ResultBody(banner);
    }


    /**
     * 更新banner
     * params
     * @return
     */
    @ApiOperation(value="banner更新信息", notes="")
    @ApiImplicitParam(name = "banner", value = "轮播图banner信息", required = true, dataType = "Banner")
    @PostMapping("/updateBanner")
    public int updateBanner(@RequestBody Banner banner){
        Banner tmpBanner = bannerService.findBannerById(banner.getId());
        if(tmpBanner != null){
           tmpBanner.setShow(banner.isShow());
           int result = bannerService.updateBannerByBanner(tmpBanner);
           return result;
        }
        return 0;
    }

}
