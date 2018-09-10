package com.winner.winnertest.controller;

import com.winner.winnertest.model.Person;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestParam("username") String username, @RequestParam("password") String password){
        Map<String, Object> map = new HashMap<String, Object>();
        if(username.equals("zw") && password.equals("123")){
            // 返回数据
            Person person = new Person();
            person.setName(username);
            person.setPassword(password);
            person.setAge(26);

            map.put("status", "ok");
            map.put("err", "");
            map.put("msg", "scuess");
            map.put("data", person);

            return map;
        }
        map.put("status", "false");
        map.put("err", "用户名或密码错误");
        map.put("msg", "fail");
        return map;
    }


    /**
     * 获取网络请求数据
     * @return 网络数据内容
     */
    @RequestMapping(value = "/getHttp", method = RequestMethod.GET)
    @ResponseBody
    public String getHttp(){
        //实例化httpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //实例化post方法
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //请求结果
        CloseableHttpResponse response = null;
        String content ="";
        try{
            //执行get方法
            response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(content);
                return content;
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
