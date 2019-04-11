package com.zw.model.music;

public class MusicResult {
    /**
     * icon
     */
    private String img;

    /**
     * 专辑名称
     */
    private String album_name;

    /**
     * 作者名称
     */
    private String author_name;

    /**
     * 歌曲名称
     */
    private String song_name;

       /**
     * 作者ID
     */
    private String author_id;

    /**
     *  音乐地址
     */
    private String play_url;


    public String getPlay_url() {
        return play_url;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
}
