package com.zw.controller;

import com.zw.response.ResultBody;
import com.zw.service.ArchiveService;
import com.zw.service.ArticleService;
import com.zw.utils.TransCodingUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: 2018/7/18 12:06
 * Describe:
 */
@RestController
public class ArchivesController {

    @Autowired
    ArchiveService archiveService;
    @Autowired
    ArticleService articleService;

    /**
     * 获得所有归档日期以及每个归档日期的文章数目
     * @return
     */
    @ApiOperation(value="获得所有归档日期以及每个归档日期的文章数目信息", notes="")
    @GetMapping("/findArchiveNameAndArticleNum")
    public ResultBody findArchiveNameAndArticleNum(){
        return new ResultBody(archiveService.findArchiveNameAndArticleNum());
    }


    /**
     * 分页获得该归档日期下的文章
     * @param archive
     * @param request
     * @return
     */
    @ApiOperation(value="分页获得该归档日期下的文章信息", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "archive", value = "归档日期", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/getArchiveArticle")
    public JSONObject getArchiveArticle(@RequestParam("archive") String archive,
                                        HttpServletRequest request){
        try {
            archive = TransCodingUtil.unicodeToString(archive);
        } catch (Exception e){
        }
        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        return articleService.findArticleByArchive(archive, rows, pageNum);
    }
}
