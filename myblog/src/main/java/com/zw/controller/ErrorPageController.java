package com.zw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: 2018/6/7 17:09
 * Describe: 错误页面跳转
 */
@Controller
public class ErrorPageController {

    /**
     * 404页面
     * @param request
     * @param model
     * @return
     */
    @ApiIgnore
    @GetMapping("/404")
    public String error404(HttpServletRequest request,
                           Model model){
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        return "404";
    }

    /**
     * 403页面
     * @param request
     * @param model
     * @return
     */
    @ApiIgnore
    @GetMapping("/403")
    public String error403(HttpServletRequest request,
                           Model model){
        String username = (String) request.getSession().getAttribute("username");
        model.addAttribute("username",username);
        return "403";
    }

}
