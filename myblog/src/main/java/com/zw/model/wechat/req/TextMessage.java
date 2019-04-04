package com.zw.model.wechat.req;

import com.zw.constant.WeChatContant;
import java.util.Date;

public class TextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public TextMessage(){
        this.setCreateTime(new Date().getTime());
        this.setMsgType(WeChatContant.RESP_MESSAGE_TYPE_TEXT);
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
