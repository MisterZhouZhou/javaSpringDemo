package com.zw.model.wechat.req;

import com.zw.constant.WeChatContant;
import java.util.Date;

public class VoiceMessage extends BaseMessage {
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public VoiceMessage(){
        this.setCreateTime(new Date().getTime());
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_VOICE);
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
