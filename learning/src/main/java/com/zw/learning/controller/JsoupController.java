package com.zw.learning.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class JsoupController {
    public static void main(String[] args){
        try{
            Document document = Jsoup.connect("http://news.qq.com/world_index.shtml").get();
            Elements lists = document.select(".linkto");
            for (int i=0;i<lists.size();i++) {
                Element list = lists.get(i);
                String title = list.text();
                System.out.println(title);
                System.out.println("========================");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
