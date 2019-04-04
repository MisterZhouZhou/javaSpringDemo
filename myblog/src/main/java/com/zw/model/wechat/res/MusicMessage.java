package com.zw.model.wechat.res;

import com.zw.model.wechat.Music;

public class MusicMessage extends BaseMessage {
    /**
     * 音乐
     */
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
