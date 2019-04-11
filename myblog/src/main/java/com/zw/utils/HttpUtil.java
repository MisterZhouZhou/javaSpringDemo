package com.zw.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
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
            if(response.getStatusLine().getStatusCode()==200){ // 加了状态验证
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

    /**
     * 获取网络请求数据
     * @return 网络数据内容
     */
    public static JSONObject GET(String url){
        String result = HttpUtil.doGetstr(url);
        JSONObject resultObject = JSONObject.parseObject(result);
        return resultObject;
    }


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
            if(response.getStatusLine().getStatusCode() == 200) {  // 加了状态验证
                String result = EntityUtils.toString(response.getEntity(),"UTF-8"); // 返回json格式
                respContent = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respContent;
    }

    /**
     * 处理post请求
     * @param url
     * @return
     */
    public static JSONObject POST(String url,String outStr) {
        String result = HttpUtil.doPoststr(url, outStr);
        JSONObject resultObject = JSONObject.parseObject(result);
        return resultObject;
    }

    /**
     * 处理doget请求,返回字符串
     * @param url
     * @return
     */
    public static String doGetstr(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String resultObject = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                resultObject = EntityUtils.toString(entity,"utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultObject;

    }

    /**
     * 处理post请求
     * @param url
     * @return
     */
    public static String doPoststr(String url,String outStr) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String resultObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "utf-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            resultObject = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}



