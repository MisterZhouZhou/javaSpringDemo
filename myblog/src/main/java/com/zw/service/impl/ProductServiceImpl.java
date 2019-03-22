package com.zw.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.component.StringAndArray;
import com.zw.mapper.ProductMapper;
import com.zw.model.Article;
import com.zw.model.Product;
import com.zw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/20 21:42
 * Describe:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Override
    public JSONObject findProducts(int rows, int pageNum) {
        PageHelper.startPage(pageNum, rows);
        List<Product> products = productMapper.findAllProducts();
        PageInfo<Product> pageInfo = new PageInfo<>(products);

        // page info
        JSONObject pageJson = new JSONObject();
        pageJson.put("pageNum",pageInfo.getPageNum());
        pageJson.put("pageSize",pageInfo.getPageSize());
        pageJson.put("total",pageInfo.getTotal());
        pageJson.put("pages",pageInfo.getPages());
        pageJson.put("isFirstPage",pageInfo.isIsFirstPage());
        pageJson.put("isLastPage",pageInfo.isIsLastPage());

        // result info
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSONArray.parseArray(JSON.toJSONString(products)));
        jsonObject.put("pageInfo",pageJson);
        return jsonObject;
    }

    @Override
    public List<Product> findProducts() {
        return productMapper.findAllProducts();
    }
}
