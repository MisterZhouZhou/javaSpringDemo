package com.zw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.mapper.CategoryMapper;
import com.zw.model.Article;
import com.zw.model.Categories;
import com.zw.model.Comment;
import com.zw.model.CommentLikesRecord;
import com.zw.response.ResultBody;
import com.zw.service.*;
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
    public int insertCategory(Categories category) {
        try {
            categoryMapper.insertCategory(category);
        }catch (Exception e){
            //logger.error("用户 " + article.getAuthor() + " 保存文章 " + article.getArticleTitle() + " 失败");
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public JSONObject getArticleCategories(int rows, int pageNum) {
        PageHelper.startPage(pageNum, rows);
        List<Categories> categories = categoryMapper.findAllCategories();
        PageInfo<Categories> pageInfo = new PageInfo<>(categories);
        JSONArray returnJsonArray = new JSONArray();
        JSONObject returnJson = new JSONObject();
        JSONObject categoriesJson;
        for(Categories category : categories){
            categoriesJson = new JSONObject();
            categoriesJson.put("categoryName",category.getCategoryName());
            categoriesJson.put("categoryArticleNum",articleService.countArticleCategoryByCategory(category.getCategoryName()));
            categoriesJson.put("categoryId",category.getId());
            returnJsonArray.add(categoriesJson);
        }
        returnJson.put("status",200);
        returnJson.put("result",returnJsonArray);
        JSONObject pageJson = new JSONObject();
        pageJson.put("pageNum",pageInfo.getPageNum());
        pageJson.put("pageSize",pageInfo.getPageSize());
        pageJson.put("total",pageInfo.getTotal());
        pageJson.put("pages",pageInfo.getPages());
        pageJson.put("isFirstPage",pageInfo.isIsFirstPage());
        pageJson.put("isLastPage",pageInfo.isIsLastPage());

        returnJson.put("pageInfo",pageJson);

        return returnJson;
    }

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

    @Override
    public int deleteCategory(long id) {
        try {
            Categories category = categoryMapper.findCategoryById(id);
            categoryMapper.deleteByArticleId(id);
            // 删除与之相对应的文章
            List<Article> articles = articleService.findArticleByCategory(category.getCategoryName());
            for (Article deleteArticle : articles){
                // 删除文章
                articleService.deleteArticle(deleteArticle.getId());
            }
        }catch (Exception e){
            //logger.error("用户 " + article.getAuthor() + " 保存文章 " + article.getArticleTitle() + " 失败");
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
