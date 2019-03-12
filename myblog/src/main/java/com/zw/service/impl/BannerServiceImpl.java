package com.zw.service.impl;

import com.zw.mapper.BannerMapper;
import com.zw.model.Banner;
import com.zw.service.BannerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    public JSONObject addBanner(Banner banner) {
        bannerMapper.insertBanner(banner);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        jsonObject.put("result",JSONArray.fromObject(banner));
        return jsonObject;
    }

    @Override
    public JSONObject findBanners() {
        List<Banner> banners = bannerMapper.findBanners();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        jsonObject.put("result",JSONArray.fromObject(banners));
        return jsonObject;
    }

    @Override
    public int countBannersNum() {
        return bannerMapper.countBannersNum();
    }
}
