package com.zw.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.constant.UrlConst;
import com.zw.model.music.HotSinger;
import com.zw.model.music.MusicResult;
import com.zw.model.music.MusicSearch;
import com.zw.model.music.Rank;
import com.zw.utils.HttpUtil;
import io.swagger.models.auth.In;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RankCollector {

    /**
     * 音乐排行榜
     * @param url
     * @return
     * @throws IOException
     */
    public Rank collect(String url) throws IOException {
        return getRank(url);
    }

    private Rank getRank(String rankUrl) throws IOException {
        Rank rank = new Rank();
        String body = HttpUtil.doGetstr(rankUrl);
        Document doc = Jsoup.parse(body);
        rank.setScope(doc.select("a[class=current]").attr("title"));
        rank.setUpdateTime(doc.select("span[class=rank_update]").text());
        List<Element> aElements = doc.select("a[data-active=playDwn]");
        for(int i = 0; i < aElements.size(); i++){
            String songUrl = aElements.get(i).attr("href");
            String[] splitArray = aElements.get(i).text().split("-");
            String name = splitArray[0].toString().trim();
            String song = "";
            if (splitArray.length > 1){
                song = splitArray[1].toString().trim();
            }
            rank.getHotSingerList().add(new HotSinger(doc.select("a[class=current]").attr("title"), name, song, songUrl));
        }
        return rank;
    }


    /**
     * 音乐搜索
     * @param url
     * @return
     * @throws IOException
     */
    public Rank searchCollect(String url) throws IOException {
        return getSearchRank(url);
    }

    private Rank getSearchRank(String rankUrl) throws IOException {
        Rank rank = new Rank();
        rank.setScope("单曲");
        JSONObject htmlBody = HttpUtil.GET(rankUrl);
        System.out.print(htmlBody.get("status"));
        if(htmlBody.get("status").toString().equals("1")){
            JSONObject dataObject = (JSONObject)htmlBody.get("data");
            List<MusicSearch> musicSearches = JSONObject.parseArray(dataObject.get("lists").toString(), MusicSearch.class);
            for (MusicSearch musicSearch : musicSearches){
                // musict result，爬取酷狗音乐mp3信息
                JSONObject musicResultObject = HttpUtil.GET(UrlConst.MUSIC_PLAY_GET_URL.replace("hashcode",musicSearch.getFileHash()));
                if(musicResultObject.get("status").toString().equals("1")){
                    JSONObject resultObject = (JSONObject)musicResultObject.get("data");
                    MusicResult musicResult = JSONObject.parseObject(resultObject.toJSONString(), MusicResult.class);
                    HotSinger hotSinger = new HotSinger(musicResult.getAlbum_name(), musicResult.getAuthor_name(), musicResult.getSong_name(), musicResult.getPlay_url());
                    rank.getHotSingerList().add(hotSinger);
                }
            }
        }

        return rank;
    }
}
