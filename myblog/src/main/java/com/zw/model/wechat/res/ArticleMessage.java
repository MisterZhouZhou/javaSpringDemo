package com.zw.model.wechat.res;

import com.zw.constant.WeChatContant;
import com.zw.model.wechat.ArticleItem;
import java.util.Date;
import java.util.List;

public class ArticleMessage extends BaseMessage {
    //图文消息个数,限制为8条以内
    private int ArticleCount;

    //多条图文消息信息，默认第一个item为大图
    private List<ArticleItem> Articles;

    public ArticleMessage(){
        this.setMsgType(WeChatContant.REQ_MESSAGE_TYPE_NEWS);
        this.setCreateTime(new Date().getTime());
    }

    public int getArticleCount() {
        return ArticleCount;
    }
    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }
    public List<ArticleItem> getArticles() {
        return Articles;
    }
    public void setArticles(List<ArticleItem> articles) {
        Articles = articles;
    }
}
