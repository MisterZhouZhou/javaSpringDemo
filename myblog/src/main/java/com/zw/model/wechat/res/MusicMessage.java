package com.zw.model.wechat.res;

import com.zw.constant.WeChatContant;
import com.zw.model.wechat.Music;

import java.util.Date;

public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private Music music;

    public MusicMessage(){
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_MUSIC);
        this.setCreateTime(new Date().getTime());
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
