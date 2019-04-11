package com.zw.model.music;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌曲排名
 */
public class Rank {
    private String scope;
    private String updateTime;
    private List<HotSinger> hotSingerList = new ArrayList<>();

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<HotSinger> getHotSingerList() {
        return hotSingerList;
    }

    public void setHotSingerList(List<HotSinger> hotSingerList) {
        this.hotSingerList = hotSingerList;
    }
}
