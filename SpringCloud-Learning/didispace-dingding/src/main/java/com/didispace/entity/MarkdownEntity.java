package com.didispace.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarkdownEntity {
    private String msgType;

    // 显示标题
    private String title;

    // 显示内容
    private String content;

    // 是否at所有人
    private Boolean isAtAll;

    // 被@人的手机号(在content里添加@人的手机号)
    private List<String> atMobiles;


    public String getMsgType() {
        return "markdown";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsAtAll() {
        return isAtAll;
    }

    public void setIsAtAll(Boolean atAll) {
        isAtAll = atAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public String getJSONObjectString() {
//        {
//            "msgtype": "markdown",
//                "markdown": {
//            "title":"杭州天气",
//                    "text": "#### 杭州天气 @156xxxx8827\n" +
//                    "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//                    "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"  +
//                    "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n"
//        },
//            "at": {
//            "atMobiles": [
//            "156xxxx8827",
//                    "189xxxx8325"
//        ],
//            "isAtAll": false
//        }
//        }

        // markdown类型
        JSONObject markdownContent = new JSONObject();
        markdownContent.put("title", this.getTitle());
        markdownContent.put("text", this.getContent());

        // at some body
        JSONObject atMobile = new JSONObject();
        if(this.getAtMobiles().size() > 0){
            List<String> mobiles = new ArrayList<String>();
            for (int i=0;i<this.getAtMobiles().size();i++){
                mobiles.add(this.getAtMobiles().get(i));
            }
            if(mobiles.size()>0){
                atMobile.put("atMobiles", mobiles);
            }
            atMobile.put("isAtAll", this.getIsAtAll());
        }

        JSONObject json = new JSONObject();
        json.put("msgtype", this.getMsgType());
        json.put("markdown", markdownContent);
        json.put("at", atMobile);
        return json.toJSONString();
    }
}


//{
//   "title":"杭州天气",
//   "content": "#### 杭州天气 @16619930394\n > 9度，西北风1级，空气良89，相对温度73%\n\n > ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n > ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n",
//   "atMobiles": ["16619930394"],
//   "isAtAll": false
//}