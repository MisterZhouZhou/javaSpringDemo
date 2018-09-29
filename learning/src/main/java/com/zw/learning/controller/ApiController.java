package com.zw.learning.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.learning.entity.User;
import com.zw.learning.service.StudentService;
import com.zw.learning.utils.CusAccessObjectUitl;
import com.zw.learning.utils.HttpUtil;
import com.zw.learning.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    StudentService studentService;

    /***
     * @Description 获取用户列表
     * @return json对象
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUserList(){
        JSONObject ojb = HttpUtil.httpGet("http://127.0.0.1:8088/user/api/getAllUser");
        return ojb;
    }


    /**
     * @Description 注册用户
     * @param: user
     * @return json
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(@RequestBody JSONObject jsonObject){
        User user = jsonObject.toJavaObject(User.class);
        JSONObject ojb = HttpUtil.httpPost("http://127.0.0.1:8088/user/api/regist", jsonObject);
        return ojb;
    }



    /**
     * @Description 根据快递code和订单号查询快递信息
     * @param: info
     *          logisticsCode  快递类型
     *          logisticsNo    订单编号 12位
     * @return string
     */
    @RequestMapping(value = "/getExpressInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getExpressInfo(HttpServletRequest request){
        // 快递类型
        String logisticsCode = request.getParameter("logisticsCode");
        // 订单编号 12位
        String logisticsNo =  request.getParameter("logisticsNo");
        String temp = String.valueOf(Math.random());
        String url = "http://www.kuaidi100.com/query?type=" + logisticsCode + "&postid=" + logisticsNo + "&temp=" + temp + "";
        JSONObject ojb = HttpUtil.httpGet(url);
        return ojb;
    }


    /**
     * @Description 获取请求IP
     * @return string
     */
    @RequestMapping(value = "/getRequestIp", method = RequestMethod.GET)
    @ResponseBody
    public String getRequestIp(HttpServletRequest request){
        return CusAccessObjectUitl.getIpAddress(request);
    }


    /**
     * @Description 获取请求IP
     * @return string
     */
    @RequestMapping(value = "/getStudentList", method = RequestMethod.GET)
    @ResponseBody
    public R getStudentList(HttpServletRequest request){
        try{
            return R.isOK().data(studentService.list());
        }catch (Exception e){
            return R.isFail(e);
        }
    }


    /**
     * @Description 获取请求IP
     * @return string
     */
    // HttpServletRequest 适用于get请求方式
    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    @ResponseBody
    public R getStudent(HttpServletRequest request){
        String name = request.getParameter("name");
        Integer num = Integer.valueOf(request.getParameter("num"));
        try{
            return R.isOK().data(studentService.findStudentWithNumAndName(name, num));
        }catch (Exception e){
            return R.isFail(e);
        }
    }

    /**
     * @Description 获取请求IP
     * @return string
     */
    // @RequestBody JSONObject jsonObject 这种方式适用于post请求方式
    @RequestMapping(value = "/getStudent2", method = RequestMethod.POST)
    @ResponseBody
    public R getStudent2(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getString("name");
        Integer num = Integer.valueOf(jsonObject.getString("num"));
        try{
            return R.isOK().data(studentService.findStudentWithNumAndName(name, num));
        }catch (Exception e){
            return R.isFail(e);
        }
    }



}
