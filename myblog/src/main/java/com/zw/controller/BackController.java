package com.zw.controller;

import com.zw.utils.TransCodingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;


/**
 * Describe: 所有页面跳转
 */
@Controller
public class BackController {

    /**
     *  首页入口
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response,
                        @AuthenticationPrincipal Principal principal){
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            request.getSession().removeAttribute("lastUrl");
            return "index";
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
        return "index";
    }


    /**
     *  登录入口
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/toLogin")
    public @ResponseBody
    void toLogin(HttpServletRequest request){
        //保存跳转页面的url
        request.getSession().setAttribute("lastUrl", request.getHeader("Referer"));
    }

    /**
     *  注册入口
     */
    @GetMapping("/register")
    public String register(){
        // 注释带验证码带登录
        return "register_new";
    }

    /**
     *  注册入口（不带验证码）
     */
    @GetMapping("/register_new")
    public String register_new(){
        return "register_new";
    }



    @GetMapping("/mylove")
    public String myLove(){
        return "mylove";
    }

    @GetMapping("/mystory")
    public String mystory(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        return "mystory";
    }

    @GetMapping("/aboutme")
    public String aboutme(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        return "aboutme";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        return "update";
    }

    @GetMapping("/friendlylink")
    public String friendlylink(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        return "friendlylink";
    }

    @GetMapping("/ali")
    public String ali(){
        return "ali";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        return "user";
    }

    @GetMapping("/editor")
    public String editor(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        String id = request.getParameter("id");
        if(!"".equals(id)){
            request.getSession().setAttribute("id", id);
        }
        return "editor";
    }


    @GetMapping("/archives")
    public String archives(HttpServletResponse response,
                           HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");
        String archive = request.getParameter("archive");

        try {
            response.setHeader("archive", TransCodingUtil.stringToUnicode(archive));
        } catch (Exception e){
        }
        return "archives";
    }

    @GetMapping("/categories")
    public String categories(HttpServletResponse response,
                             HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");
        String category = request.getParameter("category");

        try {
            response.setHeader("category", TransCodingUtil.stringToUnicode(category));
        } catch (Exception e){
        }
        return "categories";
    }

    @GetMapping("/tags")
    public String tags(HttpServletResponse response,
                       HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        String tag = request.getParameter("tag");
        try {
            response.setHeader("tag", TransCodingUtil.stringToUnicode(tag));
        } catch (Exception e){
        }
        return "tags";
    }

    @GetMapping("/superadmin")
    public String superadmin(HttpServletRequest request){
        request.getSession().removeAttribute("lastUrl");
        return "superadmin";
    }

}
