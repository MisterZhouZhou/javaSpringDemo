package com.didispace.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

public class DingdingUtils {

    private static OkHttpClient client = new OkHttpClient();
    /**
     * 发送钉钉消息
     * @param jsonString 消息内容
     * @param webhook 钉钉自定义机器人webhook
     * @return
     */
    public static boolean sendToDingding(String jsonString, String webhook) {
        try{
            String type = "application/json; charset=utf-8";
            RequestBody body = RequestBody.create(MediaType.parse(type), jsonString);
            Request.Builder builder = new Request.Builder().url(webhook);
            builder.addHeader("Content-Type", type).post(body);

            Request request = builder.build();
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(String.format("send ding message:%s", string));
            JSONObject res = JSONObject.parseObject(string);
            return res.getIntValue("errcode") == 0;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
