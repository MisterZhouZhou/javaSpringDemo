package com.zw.model.wechat.res;
import com.zw.constant.WeChatContant;
import java.util.Date;

public class LinkMessage extends BaseMessage {
    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;

    public LinkMessage(){
        this.setCreateTime(new Date().getTime());
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_LINK);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
