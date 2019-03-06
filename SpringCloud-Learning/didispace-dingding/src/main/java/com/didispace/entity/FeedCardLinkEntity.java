package com.didispace.entity;

public class FeedCardLinkEntity {

    // 显示标题
    private String title;

    // icon url
    private String picURL;

    // 内容对链接
    private String messageURL;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getMessageURL() {
        return messageURL;
    }

    public void setMessageURL(String messageURL) {
        this.messageURL = messageURL;
    }
}
