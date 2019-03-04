package com.didispace.controller;

import com.didispace.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/OrderUser")
    public String getOrderUserId(Integer id) throws IOException {
        String result = get("http://localhost:9090/memeber/getUser?id="+id);
        return result;
    }

    public String get(String Url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpGet
        HttpGet httpGet = new HttpGet(Url);
        CloseableHttpResponse response = null;
        try{
            // 执行http请求
            response = httpClient.execute(httpGet);
            //获取http响应体
            HttpEntity entity=response.getEntity();
            if (entity !=null){
                String content= EntityUtils.toString(entity);
                return content;
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            response.close();
            httpClient.close();
        }
        return null;
    }

}
