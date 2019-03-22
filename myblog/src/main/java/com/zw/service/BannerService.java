package com.zw.service;

import com.zw.model.Banner;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Describe:banner业务操作
 */
@Service
public interface BannerService {
    /**
     * 添加banner
     * @param banner
     */
    JSONObject addBanner(Banner banner);

    /**
     * 获得所有的banner
     * @return
     */
    List<Banner> findBanners();

    /**
     * 获得banner数目
     * @return
     */
    int countBannersNum();
}
