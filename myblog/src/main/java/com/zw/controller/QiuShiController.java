package com.zw.controller;

import com.zw.model.QiuShiModel;
import com.zw.model.music.HotSinger;
import com.zw.model.music.Rank;
import com.zw.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class QiuShiController {

    /**
     * 音乐排行榜
     * @param url
     * @return
     * @throws IOException
     */
    public QiuShiModel collect(String url) throws IOException {
        return getQiushi(url);
    }

    private QiuShiModel getQiushi(String qiushiUrl) throws IOException {
        QiuShiModel qiuShiModel = new QiuShiModel();
        String body = HttpUtil.doGetstr(qiushiUrl);
        Document doc = Jsoup.parse(body);
        List<Element> aElements = doc.select("a[class=text]");
        int randomIndex = (int)Math.random()*aElements.size();
        qiuShiModel.setContent(aElements.get(randomIndex).text());
        return qiuShiModel;
    }

}
