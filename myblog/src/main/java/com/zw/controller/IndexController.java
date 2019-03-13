package com.zw.controller;

import com.zw.model.FeedBack;
import com.zw.service.*;
import com.zw.utils.TransCodingUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;


//@ApiImplicitParam：
//   paramType：参数放在哪个地方
//   header 请求参数的获取：@RequestHeader
//　　query 请求参数的获取：@RequestParam
//　　path（用于restful接口） 请求参数的获取：@PathVariable
//　　body（不常用）
//        　　form（不常用）
//        　　name：参数名
//        　　dataType：参数类型
//        　　required：参数是否必须传
//        　　value：参数的意思
//        　　defaultValue：参数的默认值

/**
 * @author: zhangocean
 * @Date: 2018/6/16 16:01
 * Describe: 首页数据获取
 */
@Controller
public class IndexController {

    @Autowired
    VisitorService visitorService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @Autowired
    TagService tagService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    FeedBackService feedBackService;
    @Autowired
    UserService userService;
    @Autowired
    LeaveMessageService leaveMessageService;

    /**
     * 增加访客量
     * @return  网站总访问量以及访客量
     */
    @ApiIgnore
    @GetMapping("/getVisitorNumByPageName")
    public @ResponseBody JSONObject getVisitorNumByPageName(HttpServletRequest request,
                                                            @RequestParam("pageName") String pageName) throws UnsupportedEncodingException {

        int index = pageName.indexOf("?");
        if(index == -1){
            pageName = "visitorVolume";
        } else {
            String subPageName = pageName.substring(0, index);
            if("archives".equals(subPageName) || "categories".equals(subPageName) || "tags".equals(subPageName) || "login".equals(subPageName) || "register".equals(subPageName)){
                pageName = "visitorVolume";
            } else {
                //接收到文章的url将url中utf8的16进制数转换成汉字
                int originalAuthorIndex = pageName.indexOf("originalAuthor");
                String originalAuthorUtf18 = pageName.substring(originalAuthorIndex + 15);
                pageName = pageName.substring(0, originalAuthorIndex + 15) + TransCodingUtil.utf16ToUtf8(originalAuthorUtf18);
            }
        }
        visitorService.addVisitorNumByPageName(pageName, request);
        return visitorService.getVisitorNumByPageName(pageName);
    }

    /**
     * 分页获得当前页文章
     */
    @ApiOperation(value="分页获取文章信息", notes="根据rows和pageNum和来获取用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/myArticles")
    @ResponseBody
    public JSONArray myArticles(@RequestParam("rows") String rows,
                                @RequestParam("pageNum") String pageNum){
        return articleService.findAllArticles(rows, pageNum);
    }

    /**
     * 获得最新评论
     */
    @ApiOperation(value="获得最新评论信息", notes="根据rows和pageNum和来获取最新评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/newComment")
    @ResponseBody
    public JSONObject newComment(@RequestParam("rows") String rows,
                                 @RequestParam("pageNum") String pageNum){

        return commentService.findFiveNewComment(Integer.parseInt(rows),Integer.parseInt(pageNum));
    }

    /**
     * 获得最新留言
     */
    @ApiOperation(value="获得最新评论信息", notes="根据rows和pageNum和来获取最新评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/newLeaveWord")
    @ResponseBody
    public JSONObject newLeaveWord(@RequestParam("rows") String rows,
                                   @RequestParam("pageNum") String pageNum){
        return leaveMessageService.findFiveNewComment(Integer.parseInt(rows),Integer.parseInt(pageNum));
    }



    /**
     * 获得标签云
     */
    @ApiIgnore
    @GetMapping("/findTagsCloud")
    @ResponseBody
    public JSONObject findTagsCloud(){
        return tagService.findTagsCloud();
    }


    /**
     * 获得右侧栏日志数、分类数、标签数
     */
    @ApiIgnore
    @GetMapping("/findArchivesCategoriesTagsNum")
    @ResponseBody
    public JSONObject findArchivesCategoriesTagsNum(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tagsNum", tagService.countTagsNum());
        jsonObject.put("categoriesNum", categoryService.countCategoriesNum());
        jsonObject.put("archivesNum", articleService.countArticle());
        return jsonObject;
    }

    /**
     * 获取站点信息
     */
    @ApiIgnore
    @GetMapping("/getSiteInfo")
    @ResponseBody
    public JSONObject getSiteInfo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articleNum", articleService.countArticle());
        jsonObject.put("tagsNum", tagService.countTagsNum());
        jsonObject.put("leaveWordNum", leaveMessageService.countLeaveMessageNum());
        jsonObject.put("commentNum", commentService.commentNum());
        return jsonObject;
    }

    /**
     * 反馈
     * @param feedBack
     * @param principal
     * @return
     */
    @ApiIgnore
    @PostMapping("/submitFeedback")
    @ResponseBody
    public JSONObject submitFeedback(FeedBack feedBack,
                                     @AuthenticationPrincipal Principal principal){
        String username;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",403);
            return jsonObject;
        }
        feedBack.setPersonId(userService.findIdByUsername(username));
        return feedBackService.submitFeedback(feedBack);

    }
}
