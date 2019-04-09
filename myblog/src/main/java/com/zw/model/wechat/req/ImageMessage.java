package com.zw.model.wechat.req;

import com.zw.constant.WeChatContant;
import com.zw.model.wechat.Image;

import java.util.Date;

public class ImageMessage extends BaseMessage {
    private Image Image;

    public ImageMessage(){
        this.setCreateTime(new Date().getTime());
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_IMAGE);
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
