package com.zw.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/7/18 12:07
 * Describe: 归档业务操作
 */
@Service
public interface ArchiveService {

    /**
     * 获得归档信息
     * @return
     */
    JSONArray findArchiveNameAndArticleNum();

    /**
     * 添加归档日期
     * @param archiveName
     */
    void addArchiveName(String archiveName);

}
