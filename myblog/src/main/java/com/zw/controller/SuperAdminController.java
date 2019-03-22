package com.zw.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.model.Categories;
import com.zw.response.ResultBody;
import com.zw.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

/**
 * @author: zhangocean
 * @Date: 2018/7/25 16:14
 * Describe: 超级管理页面
 */
@RestController
public class SuperAdminController {

    @Autowired
    PrivateWordService privateWordService;
    @Autowired
    FeedBackService feedBackService;
    @Autowired
    VisitorService visitorService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 获得所有悄悄话(供web使用)
     * @return
     */
    @ApiIgnore
    @PostMapping("/getAllPrivateWord")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public JSONObject getAllPrivateWord(){
        return privateWordService.getAllPrivateWord();
    }

    /**
     * 回复悄悄话(供web使用)
     * @return
     */
    @ApiIgnore
    @PostMapping("/replyPrivateWord")
    public JSONObject replyPrivateWord(@AuthenticationPrincipal Principal principal,
                                       @RequestParam("replyContent") String replyContent,
                                       @RequestParam("replyId") String id){
        String username;
        JSONObject jsonObject;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            jsonObject = new JSONObject();
            jsonObject.put("status",403);
            return jsonObject;
        }

        return privateWordService.replyPrivateWord(replyContent, username, Integer.parseInt(id));
    }

    /**
     * 分页获得所有反馈信息(供接口使用)
     * @param rows 一页大小
     * @param pageNum 当前页
     */
    @ApiOperation(value="分页获得所有反馈信息", notes="根据分页获得所有反馈信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/getAllFeedback")
    public JSONObject getAllFeedback(@RequestParam("rows") String rows,
                                     @RequestParam("pageNum") String pageNum){
        return feedBackService.getAllFeedback(Integer.parseInt(rows),Integer.parseInt(pageNum));
    }

    /**
     * 获得统计信息(供接口使用)
     * @return
     */
    @ApiOperation(value="获得统计信息", notes="")
    @GetMapping("/getStatisticsInfo")
    public JSONObject getStatisticsInfo(){
        JSONObject returnJson = new JSONObject();
        long num = visitorService.getAllVisitor();
        returnJson.put("allVisitor", num);
        returnJson.put("allUser", userService.countUserNum());
        returnJson.put("yesterdayVisitor", num);
        returnJson.put("articleNum", articleService.countArticle());
        returnJson.put("commentNum", commentService.commentNum());
        return returnJson;
    }

    /**
     * 获得文章管理(供web使用)
     * @return
     */
    @ApiIgnore
    @GetMapping("/getArticleManagement")
    public JSONObject getArticleManagement(@AuthenticationPrincipal Principal principal,
                                           @RequestParam("rows") String rows,
                                           @RequestParam("pageNum") String pageNum){
        String username = null;
        JSONObject returnJson = new JSONObject();
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            returnJson.put("status",403);
            return  returnJson;
        }
        return articleService.getArticleManagement(Integer.parseInt(rows), Integer.parseInt(pageNum));
    }

    /**
     * 删除文章(供web使用)
     * @param id 文章id
     * @return 1--删除成功   0--删除失败
     */
    @ApiIgnore
    @GetMapping("/deleteArticle")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public int deleteArticle(@RequestParam("id") String id){
        if("".equals(id) || id == null){
            return 0;
        }
        return articleService.deleteArticle(Long.parseLong(id));
    }


    /**
     * 根据评论id删除评论(供web使用)
     * @param id 评论id
     * @return 1--删除成功   0--删除失败
     */
    @ApiIgnore
    @GetMapping("/deleteComment")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public int deleteComment(@RequestParam("id") String id){
        if("".equals(id) || id == null){
            return 0;
        }
        return commentService.deleteCommentByCommentId(Long.parseLong(id));
    }



    /**
     * 获得所有的文章及其评论内容(供web使用)
     * @return
     */
    @ApiIgnore
    @GetMapping("/getArticleComment")
    public JSONObject getArticleComment(@AuthenticationPrincipal Principal principal,
                                           @RequestParam("rows") String rows,
                                           @RequestParam("pageNum") String pageNum){
        String username = null;
        JSONObject returnJson = new JSONObject();
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            returnJson.put("status",403);
            return  returnJson;
        }
        return articleService.getArticlesComment(Integer.parseInt(rows), Integer.parseInt(pageNum));
    }


    /**
     * 获得所有的文章分类及文章数内容(供web使用)
     * @return
     */
    @ApiIgnore
    @GetMapping("/getArticleCategories")
    public JSONObject getArticleCategories(@AuthenticationPrincipal Principal principal,
                                        @RequestParam("rows") String rows,
                                        @RequestParam("pageNum") String pageNum){
        String username = null;
        JSONObject returnJson = new JSONObject();
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            returnJson.put("status",403);
            return  returnJson;
        }
        return categoryService.getArticleCategories(Integer.parseInt(rows), Integer.parseInt(pageNum));
    }


    /**
     * 添加文章分类(供web使用)
     * @return
     */
    @ApiIgnore
    @PostMapping("/addCategory")
    public ResultBody addCategory(@AuthenticationPrincipal Principal principal,
                                  @RequestBody Categories category){
        String username = null;
        ResultBody returnJson = new ResultBody();
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            returnJson.setCode("403");
            returnJson.setMessage("权限被拒绝");
            return  returnJson;
        }
        int result = categoryService.insertCategory(category);
        if (result == 0){
            returnJson.setCode("500");
            returnJson.setMessage("添加文章分类失败");
        }
        return returnJson;
    }

    /**
     * 根据评论id删除文章分类(供web使用)
     * @param id 文章分类id
     * @return 1--删除成功   0--删除失败
     */
    @ApiIgnore
    @GetMapping("/deleteCategory")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public int deleteCategory(@RequestParam("id") String id){
        if("".equals(id) || id == null){
            return 0;
        }
        return categoryService.deleteCategory(Long.parseLong(id));
    }

}
