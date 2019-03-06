package com.didispace.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeedCardEntity {

    private String msgType;

    // links
    private List<FeedCardLinkEntity> links;

    public String getMsgType() {
        return "feedCard";
    }

    public List<FeedCardLinkEntity> getLinks() {
        return links;
    }

    public void setLinks(List<FeedCardLinkEntity> links) {
        this.links = links;
    }

    public String getJSONObjectString() {

//        {
//            "feedCard": {
//            "links": [
//            {
//                "title": "时代的火车向前开",
//                    "messageURL": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
//                    "picURL": "https://www.dingtalk.com/"
//            },
//            {
//                "title": "时代的火车向前开2",
//                    "messageURL": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
//                    "picURL": "https://www.dingtalk.com/"
//            }
//        ]
//        },
//            "msgtype": "feedCard"
//        }

        // text类型
        JSONObject feedCardContent = new JSONObject();
        List<FeedCardLinkEntity> links = new ArrayList<FeedCardLinkEntity>();
        for (int i=0;i<this.getLinks().size();i++){
            links.add(this.getLinks().get(i));
        }
        if(this.getLinks().size()>0){
            feedCardContent.put("links", links);
        }

        JSONObject json = new JSONObject();
        json.put("msgtype", this.getMsgType());
        json.put("feedCard", feedCardContent);

        return json.toJSONString();
    }
}

//  example
//{
//        "links": [
//        {
//        "title": "时代的火车向前开",
//        "messageURL": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
//        "picURL": "https://img-blog.csdnimg.cn/20181129152228878.png?imageView2/5/w/120/h/120"
//        },
//        {
//        "title": "时代的火车向前开2",
//        "messageURL": "https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI",
//        "picURL": "https://img-blog.csdnimg.cn/20181129152228878.png?imageView2/5/w/120/h/120"
//        }
//        ]
// }