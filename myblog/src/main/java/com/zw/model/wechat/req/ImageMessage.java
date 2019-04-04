package com.zw.model.wechat.req;

import com.zw.constant.WeChatContant;
import java.util.Date;

public class ImageMessage extends BaseMessage {
    /**
     * 图片链接
     */
    private String PicUrl;

    public ImageMessage(){
        this.setCreateTime(new Date().getTime());
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_IMAGE);
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
