package com.zw.service;

import com.zw.model.Categories;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhangocean
 * @Date: 2018/7/17 20:52
 * Describe:分类业务操作
 */
@Service
public interface CategoryService {

    /**
     * 添加文章分类
     */
    int insertCategory(Categories category);

    /**
     * 分页获得文章评论内容
     */
    JSONObject getArticleCategories(int rows, int pageNum);

    /**
     * 获得所有的分类以及该分类的文章总数
     * @return
     */
    JSONObject findCategoriesNameAndArticleNum();

    /**
     * 获得所有的分类
     * @return
     */
    JSONArray findCategoriesName();

    /**
     * 获得分类数目
     * @return
     */
    int countCategoriesNum();

    /**
     * 通过id删除文章分类
     * @param id 文章分类id
     * @return 1--删除成功  0--删除失败
     */
    @Transactional
    int deleteCategory(long id);

}
