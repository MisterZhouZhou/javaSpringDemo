package com.zw.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/7/12 13:43
 * Describe: 文章评论点赞记录
 */
@Data
public class CommentLikesRecord {

    private long id;

    /**
     * 文章id
     */
    private long articleId;

    /**
     * 原作者
     */
    private String originalAuthor;

    /**
     * 评论的id
     */
    private long pId;

    /**
     * 点赞人
     */
    private int likerId;

    /**
     * 点赞时间
     */
    private String likeDate;

    public CommentLikesRecord() {
    }

    public CommentLikesRecord(long articleId, String originalAuthor, int pId, int likerId, String likeDate) {
        this.articleId = articleId;
        this.originalAuthor = originalAuthor;
        this.pId = pId;
        this.likerId = likerId;
        this.likeDate = likeDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public int getLikerId() {
        return likerId;
    }

    public void setLikerId(int likerId) {
        this.likerId = likerId;
    }

    public String getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(String likeDate) {
        this.likeDate = likeDate;
    }
}
