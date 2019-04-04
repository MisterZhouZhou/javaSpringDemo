package com.zw.model.wechat.req;

import com.zw.constant.WeChatContant;
import com.zw.model.wechat.Video;

import java.util.Date;

public class VideoMessage extends BaseMessage {
    private Video video;

    public VideoMessage(){
        this.setCreateTime(new Date().getTime());
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_VIDEO);
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
