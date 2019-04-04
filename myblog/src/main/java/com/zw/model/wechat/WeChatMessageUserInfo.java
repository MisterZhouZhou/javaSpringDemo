package com.zw.model.wechat;

/**
 * 微信消息model
 */
public class WeChatMessageUserInfo {

    /**
     * 接收方账号(收到的OpenID)
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
}
