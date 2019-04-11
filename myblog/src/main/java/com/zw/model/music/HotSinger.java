package com.zw.model.music;

/**
 * 热门歌手
 */
public class HotSinger {
    private String scope;
    private String name;
    private String hotSong;
    private String songUrl;

    public HotSinger(String scope, String name, String hotSong, String songUrl) {
        this.scope = scope;
        this.name = name;
        this.hotSong = hotSong;
        this.songUrl = songUrl;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotSong() {
        return hotSong;
    }

    public void setHotSong(String hotSong) {
        this.hotSong = hotSong;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotSinger singer = (HotSinger) o;

        if (name != null ? !name.equals(singer.name) : singer.name != null) return false;
        return hotSong != null ? hotSong.equals(singer.hotSong) : singer.hotSong == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (hotSong != null ? hotSong.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HotSinger{" +
                "scope='" + scope + '\'' +
                ", name='" + name + '\'' +
                ", hotSong='" + hotSong + '\'' +
                ", songUrl='" + songUrl + '\'' +
                '}';
    }
}
