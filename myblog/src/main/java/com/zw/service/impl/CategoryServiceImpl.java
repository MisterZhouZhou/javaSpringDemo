package com.zw.service.impl;

import com.zw.mapper.CategoryMapper;
import com.zw.service.ArticleService;
import com.zw.service.CategoryService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/7/17 20:54
 * Describe:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ArticleService articleService;

    @Override
    public JSONObject findCategoriesNameAndArticleNum() {
        List<String> categoryNames = categoryMapper.findCategoriesName();
        JSONObject categoryJson;
        JSONArray categoryJsonArray = new JSONArray();
        JSONObject returnJson = new JSONObject();
        for(String categoryName : categoryNames){
            categoryJson = new JSONObject();
            categoryJson.put("categoryName",categoryName);
            categoryJson.put("categoryArticleNum",articleService.countArticleCategoryByCategory(categoryName));
            categoryJsonArray.add(categoryJson);
        }
        returnJson.put("status",200);
        returnJson.put("result",categoryJsonArray);
        return returnJson;
    }

    @Override
    public JSONArray findCategoriesName() {
        List<String> categoryNames = categoryMapper.findCategoriesName();
        return JSONArray.fromObject(categoryNames);
    }

    @Override
    public int countCategoriesNum() {
        return categoryMapper.countCategoriesNum();
    }

}
