package com.zw.springdemo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    /**
     * 发送POST请求
     *
     * @param url 目的地址
     * @param json 请求参数，JSONObject类型
     * @return 响应结果
     */
    public static JSONObject httpPost(String url, JSONObject json) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        JSONObject respContent = null;
        // 设置参数
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");//发送json数据需要设置contentType
        httpPost.setEntity(entity);
        try{
            CloseableHttpResponse response = client.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity(),"UTF-8"); // 返回json格式
                respContent = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }

    /**
     * 获取网络请求数据
     * @return 网络数据内容
     */
    public static JSONObject httpGet(String url){
        //实例化httpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //实例化post方法
        HttpGet httpGet = new HttpGet(url);
        JSONObject contentObject = null;
        try{
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                String result = EntityUtils.toString(response.getEntity(),"utf-8");
                contentObject = JSONObject.parseObject(result);
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return contentObject;
    }
}



