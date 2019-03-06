package com.didispace.controller;

import com.didispace.entity.*;
import com.didispace.utils.DingdingUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/dingding")
public class DingdingController {

    // 发送钉钉消息
    @PostMapping(value = "/sendText")
    public boolean sendTextMessage(@RequestBody TextEntity text) {
       return DingdingUtils.sendToDingding(text.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=958a6c259ed00bf6b911ebcf3a78b6f4960dbcf1ab7b2e94eca72867348aab3a");
    }

    @PostMapping(value = "/sendLink")
    public boolean sendLinkMessage(@RequestBody LinkEntity linkEntity) {
        return DingdingUtils.sendToDingding(linkEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=958a6c259ed00bf6b911ebcf3a78b6f4960dbcf1ab7b2e94eca72867348aab3a");
    }


    @PostMapping(value = "/sendMarkdown")
    public boolean sendMarkdownMessage(@RequestBody MarkdownEntity markdownEntity) {
        return DingdingUtils.sendToDingding(markdownEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=958a6c259ed00bf6b911ebcf3a78b6f4960dbcf1ab7b2e94eca72867348aab3a");
    }

    @PostMapping(value = "/sendActionCard")
    public boolean sendActionCardMessage(@RequestBody ActionCardEntity actionCardEntity) {
        return DingdingUtils.sendToDingding(actionCardEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=958a6c259ed00bf6b911ebcf3a78b6f4960dbcf1ab7b2e94eca72867348aab3a");
    }

    @PostMapping(value = "/sendFeedCard")
    public boolean sendFeedCardMessage(@RequestBody FeedCardEntity feedCardEntity) {
        return DingdingUtils.sendToDingding(feedCardEntity.getJSONObjectString(), "https://oapi.dingtalk.com/robot/send?access_token=958a6c259ed00bf6b911ebcf3a78b6f4960dbcf1ab7b2e94eca72867348aab3a");
    }

}
