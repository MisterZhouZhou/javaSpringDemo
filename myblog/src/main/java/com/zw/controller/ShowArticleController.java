package com.zw.controller;

import com.zw.model.ArticleLikesRecord;
import com.zw.service.ArticleLikesRecordService;
import com.zw.service.ArticleService;
import com.zw.service.UserService;
import com.zw.utils.TimeUtil;
import com.zw.utils.TransCodingUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: 2018/7/5 16:21
 * Describe: 文章显示页面
 */
@Controller
public class ShowArticleController {

    private Logger logger = LoggerFactory.getLogger(ShowArticleController.class);

    @Autowired
    ArticleLikesRecordService articleLikesRecordService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    /**
     *  获取文章
     * @param articleId 文章id
     * @param originalAuthor 原作者
     * @return
     */
    @PostMapping("/getArticleByArticleIdAndOriginalAuthor")
    public @ResponseBody JSONObject getArticleByIdAndOriginalAuthor(@RequestParam("articleId") String articleId,
                                                                    @RequestParam("originalAuthor") String originalAuthor,
                                                                    @AuthenticationPrincipal Principal principal){
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            logger.info("This user is not login");
        }
        JSONObject jsonObject = articleService.getArticleByArticleIdAndOriginalAuthor(Long.parseLong(articleId), TransCodingUtil.unicodeToString(originalAuthor),username);
        return jsonObject;
    }




    /**
     * 点赞
     * @param articleId 文章号
     * @return
     */
    @GetMapping("/addArticleLike")
    public @ResponseBody int addArticleLike(@RequestParam("articleId") String articleId,
                                            @RequestParam("originalAuthor") String originalAuthor,
                                            @AuthenticationPrincipal Principal principal){

        String username="";
        try {
            username = principal.getName();
        }catch (NullPointerException e){
            logger.error("username " + username + " is not login");
            return -1;
        }

        String stringOriginalAuthor = TransCodingUtil.unicodeToString(originalAuthor);
        if(articleLikesRecordService.isLiked(Long.parseLong(articleId), stringOriginalAuthor, username)){
            logger.info("你已经点过赞了");
            return -2;
        }
        int likes = articleService.updateLikeByArticleIdAndOriginalAuthor(Long.parseLong(articleId), stringOriginalAuthor);
        ArticleLikesRecord articleLikesRecord = new ArticleLikesRecord(Long.parseLong(articleId), stringOriginalAuthor, userService.findIdByUsername(username), new TimeUtil().getFormatDateForFive());
        articleLikesRecordService.insertArticleLikesRecord(articleLikesRecord);
        logger.info("点赞成功");
        return likes;
    }


//    @GetMapping("/findArticle")
//    public String findArticle(HttpServletRequest request, HttpServletResponse response){
//        String articleId = request.getParameter("articleId");
//        String originalAuthor = request.getParameter("originalAuthor");
//        response.setHeader("articleId",articleId);
//        response.setHeader("originalAuthor",originalAuthor);
//        return "show";
//    }

    @GetMapping("/findArticle")
    public String show(@RequestParam("articleId") String articleId,
                       @RequestParam("originalAuthor") String originalAuthor,
                       HttpServletResponse response,
                       Model model,
                       HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        Map<String, String> articleMap = articleService.findArticleTitleByArticleIdAndOriginalAuthor(Long.parseLong(articleId), originalAuthor);
        model.addAttribute("articleTitle",articleMap.get("articleTitle"));
        String articleTabloid = articleMap.get("articleTabloid");
        if(articleTabloid.length() <= 110){
            model.addAttribute("articleTabloid",articleTabloid);
        } else {
            model.addAttribute("articleTabloid",articleTabloid.substring(0,110));
        }

        //将文章id和原作者存入响应头
        response.setHeader("articleId",articleId);
        response.setHeader("originalAuthor", TransCodingUtil.stringToUnicode(originalAuthor));
        return "show";
    }
}
