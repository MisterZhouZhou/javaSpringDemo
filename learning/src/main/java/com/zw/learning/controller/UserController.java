package com.zw.learning.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.learning.constant.PageConstant;
import com.zw.learning.entity.User;
import com.zw.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description:进入登陆页面
     * @param:
     * @return:String
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return PageConstant.LOGIN;
    }

    /**
     * @Description:用户登陆
     * @param: name
     * @param:password
     * @param:session
     * @param:attributes
     * @return:String
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session, RedirectAttributes attributes, Model model){
        String username = request.getParameter("username");//获取用户名
        String password = request.getParameter("password");//获取密码
        // 判断用户名是否为空
        boolean checkName = (username == null || username.equals(""));
        // 判断密码是否为空
        boolean checkPassord = (password == null || password.equals(""));
        if(checkName || checkPassord){
            attributes.addFlashAttribute("nullNameAndPassword","用户名或密码不能为空");
            // 重定向到当前页面
            return PageConstant.REDIRECT+PageConstant.LOGIN;
        }
        // 从数据库中查找用户
        User user = userService.getUser(username, password);
        if(user == null){
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return PageConstant.REDIRECT+PageConstant.LOGIN;
        }
        session.setAttribute("user",user);
        model.addAttribute("user", user);
        return PageConstant.INDEX;
    }


    /**
     * @Description:进入注册页面
     * @param:
     * @return:String
     */
    @RequestMapping(value = "/toRegist", method = RequestMethod.GET)
    public String regist() {
        return PageConstant.REGIST;
    }


    /**
     * @Description:用户注册信息 web
     * @param: user
     * @return:String
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String save(User userPo, RedirectAttributes attributes) {
        // 获取用户名
        String username = userPo.getUsername();
        User user = userService.getName(username);

        if(null != user){
            attributes.addFlashAttribute("mess","用户名已被注册");
            return  PageConstant.REDIRECTTO;
        }

        userService.save(userPo);
        return PageConstant.SUCCESS;
    }


    /**
     * @Description:进入注册页面
     * @param:
     * @return:String
     */
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public String getAllUser(Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("userList", users);
        return "userlist";
    }



    /***
     * 获取用户信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(String id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/editUser";
    }


    /***
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/getAllUser";
    }


    /***
     * 删除用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> delUser(String id, Model model){
        userService.deleteUser(id);
        Map<String, String> map = new HashMap<String , String>();
        map.put("data", "success");
        return map;
    }




    /********************************************           api              ********************************************/
    /**
     * @Description:进入注册页面
     * @param:
     * @return:String
     */
    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.GET)
    @ResponseBody
    public Map getAllUser() {
        List<User> users = userService.getUserList();
        // 返回结果
        Map responseMap = new HashMap();
        responseMap.put("data", users);
        responseMap.put("status", 200);
        responseMap.put("msg", "查询成功");
        return responseMap;
    }


    /**
     * @Description:用户注册信息 api
     * @param: user
     * @return:String
     */
    @RequestMapping(value = "/api/regist", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody JSONObject jsonObject) {
        // 获取用户名
        String username = jsonObject.getString("username");//获取用户名
        String password = jsonObject.getString("password");//获取用户名
        String sex = jsonObject.getString("sex");//获取用户名
        int age = jsonObject.getInteger("age");//获取用户名
        // 返回结果
        Map responseMap = new HashMap();
        responseMap.put("data", null);

        User user = userService.getName(username);
        if(null != user){
            responseMap.put("status", 201);
            responseMap.put("msg", "用户名已被注册");
            return responseMap;
        }
        // 重新创建user对象
        User userPo = new User();
        userPo.setUsername(username);
        userPo.setPassword(password);
        userPo.setSex(sex);
        userPo.setAge(age);
        userService.save(userPo);

        responseMap.put("status", 200);
        responseMap.put("msg", "注册成功");
        return responseMap;
    }

}
