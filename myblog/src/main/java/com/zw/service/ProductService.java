package com.zw.service;

import com.zw.model.Product;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Describe: 产品业务服务
 */
@Service
public interface ProductService {

    /**
     *  添加产品
     */
    @Transactional
    void insert(Product product);

    /**
     * 分页获得该分类下的所有文章
     * @param rows 一页大小
     * @param pageNum 页数
     * @return
     */
    JSONObject findProducts(int rows, int pageNum);

    /**
     * 获取所有产品信息
     * @return
     */
    List<Product> findProducts();
}
