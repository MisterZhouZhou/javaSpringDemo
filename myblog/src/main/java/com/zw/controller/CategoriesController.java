package com.zw.controller;

import com.zw.model.Categories;
import com.zw.response.ResultBody;
import com.zw.service.ArticleService;
import com.zw.service.CategoryService;
import com.zw.utils.TransCodingUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author: zhangocean
 * @Date: 2018/7/17 20:50
 * Describe:
 */
@RestController
public class CategoriesController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;

    /**
     * 获得所有分类名以及每个分类名的文章数目(供接口使用)
     * @return
     */
    @ApiOperation(value="获得所有分类名以及每个分类名的文章数目信息", notes="")
    @GetMapping("/findCategoriesNameAndArticleNum")
    public JSONObject findCategoriesNameAndArticleNum(){
        return categoryService.findCategoriesNameAndArticleNum();
    }

    /**
     * 分页获得该分类下的文章(供接口使用)
     * @return
     */
    @ApiOperation(value="分页获得该归档日期下的文章信息", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "分类名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/getCategoryArticle")
    public JSONObject getCategoryArticle(@RequestParam("category") String category,
                                         HttpServletRequest request){

        try {
            category = TransCodingUtil.unicodeToString(category);
        } catch (Exception e){
        }
        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        return articleService.findArticleByCategory(category, rows, pageNum);
    }


    /**
     * 分页获得所有的文章分类信息(供接口使用)
     * @return
     */
    @ApiOperation(value="分页获得所有的文章分类信息", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/categories/getArticleCategories")
    public JSONObject getArticleCategories( @RequestParam("rows") String rows,
                                            @RequestParam("pageNum") String pageNum){
        return categoryService.getArticleCategories(Integer.parseInt(rows), Integer.parseInt(pageNum));
    }

    /**
     * 添加文章分类(供接口使用)
     * @return
     */
    @ApiOperation(value="添加文章分类信息", notes="")
    @ApiImplicitParam(name = "category", value = "分页model", required = true, dataType = "Categories", paramType = "body")
    @PostMapping("/categories/addCategory")
    public ResultBody addCategory(@RequestBody Categories category){
        ResultBody returnJson = new ResultBody();
        int result = categoryService.insertCategory(category);
        if (result == 0){
            returnJson.setCode("500");
            returnJson.setMessage("添加文章分类失败");
        }
        return returnJson;
    }

    /**
     * 根据评论id删除文章分类(供接口使用)
     * @param id 文章分类id
     * @return 1--删除成功   0--删除失败
     */
    @ApiOperation(value="删除文章分类信息", notes="根据评论id删除文章分类信息")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "String", paramType = "query")
    @GetMapping("/categories/deleteCategory")
    public ResultBody deleteCategory(@RequestParam("id") String id){
        ResultBody returnJson = new ResultBody();
        if("".equals(id) || id == null){
            returnJson.setCode("100");
            returnJson.setMessage("参数错误，分类id不能为空");
            return returnJson;
        }
        int result = categoryService.deleteCategory(Long.parseLong(id));
        if (result == 0){
            returnJson.setCode("500");
            returnJson.setMessage("删除文章分类失败");
        }
        return returnJson;
    }

}
