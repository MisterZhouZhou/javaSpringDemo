package com.zw.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zw.mapper.BannerMapper;
import com.zw.model.Banner;
import com.zw.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:banner数据交互
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Override
    public int addBanner(Banner banner) {
        try {
            bannerMapper.insertBanner(banner);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Banner> findBanners() {
        return bannerMapper.findBanners();
    }

    @Override
    public Banner findBannerById(int bannerId) {
        return bannerMapper.findBannerById(bannerId);
    }

    @Override
    public int countBannersNum() {
        return bannerMapper.countBannersNum();
    }

    @Override
    public int updateBannerByBanner(Banner banner) {
        try {
            bannerMapper.updateBannerByBanner(banner);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
