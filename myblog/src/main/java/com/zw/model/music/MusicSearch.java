package com.zw.model.music;

public class MusicSearch {

    /**
     * 歌曲作者名
     */
    private String SingerName;

    /**
     * 歌曲名
     */
    private String SongName;

    /**
     * 歌曲hash值(获取mp3地址时使用)
     */
    private String FileHash;

    /**
     * 歌曲hash值(获取mp3地址时使用)
     */
    private String SQFileHash;

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getFileHash() {
        return FileHash;
    }

    public void setFileHash(String fileHash) {
        FileHash = fileHash;
    }

    public String getSQFileHash() {
        return SQFileHash;
    }

    public void setSQFileHash(String SQFileHash) {
        this.SQFileHash = SQFileHash;
    }
}
