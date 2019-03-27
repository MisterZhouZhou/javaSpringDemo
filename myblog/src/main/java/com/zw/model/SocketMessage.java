package com.zw.model;

public class SocketMessage {

    /**
     * 消息来源(id)
     */
    private String from;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 消息
     */
    private String message;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
