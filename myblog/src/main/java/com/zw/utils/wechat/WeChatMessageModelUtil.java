package com.zw.utils.wechat;

import com.alibaba.fastjson.JSONObject;
import com.zw.constant.UrlConst;
import com.zw.constant.WeChatContant;
import com.zw.controller.ExpresssController;
import com.zw.controller.QiuShiController;
import com.zw.controller.RankCollector;
import com.zw.model.Forecast;
import com.zw.model.QiuShiModel;
import com.zw.model.Weather;
import com.zw.model.music.HotSinger;
import com.zw.model.music.Rank;
import com.zw.model.wechat.ArticleItem;
import com.zw.model.wechat.Image;
import com.zw.model.wechat.res.*;
import com.zw.model.wechat.WeChatMessageUserInfo;
import com.zw.response.ResultBody;
import com.zw.service.WeatherDateService;
import com.zw.service.impl.WeatherDateServiceImpl;
import com.zw.utils.TodayInHistoryServiceUtil;

import java.io.IOException;
import java.util.*;

public class WeChatMessageModelUtil {

    /**
     * 发送文本消息
     * @param weChatMessageUserInfo
     * @return
     */
    public static String sendTextMessage(WeChatMessageUserInfo weChatMessageUserInfo, String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        textMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        textMessage.setFuncFlag(0);
        textMessage.setContent(content);
        return WeChatUtil.textMessageToXml(textMessage);
    }

    /**
     * 发送图片消息
     * @param weChatMessageUserInfo
     * @param mediaId
     */
    public static String sendImageMessage(WeChatMessageUserInfo weChatMessageUserInfo, String mediaId) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        imageMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        imageMessage.setFuncFlag(0);
        Image image = new Image();
        image.setMediaId(mediaId);
        imageMessage.setImage(image);
        return WeChatUtil.imageMessageToXml(imageMessage);
    }

    /**
     * 发送图片消息url
     * @param weChatMessageUserInfo
     * @param mediaUrl
     */
    public static String sendUrlImageMessage(WeChatMessageUserInfo weChatMessageUserInfo, String mediaUrl) {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        imageMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        imageMessage.setFuncFlag(0);
        Image image = new Image();
        image.setPicUrl(mediaUrl);
        imageMessage.setImage(image);
        return WeChatUtil.imageMessageToXml(imageMessage);
    }

    /**
     * 发送链接消息
     * @param weChatMessageUserInfo
     * @return
     */
    public static String sendLinkMessage(WeChatMessageUserInfo weChatMessageUserInfo, String title, String desription, String url){
        LinkMessage linkMessage = new LinkMessage();
        linkMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        linkMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        linkMessage.setFuncFlag(0);
        linkMessage.setTitle(title);
        linkMessage.setDescription(desription);
        linkMessage.setUrl(url);
        return WeChatUtil.linkMessageToXml(linkMessage);
    }


    /**
     * 发送链接消息
     * @param weChatMessageUserInfo
     * @return
     */
    public static String sendLocationMessage(WeChatMessageUserInfo weChatMessageUserInfo){
        LocationMessage locationMessage = new LocationMessage();
        locationMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        locationMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        locationMessage.setFuncFlag(0);
        locationMessage.setLabel("dd");
        locationMessage.setLocation_X("12.21");
        locationMessage.setLocation_Y("33.2");
        locationMessage.setScale("1");
        return WeChatUtil.locationMessageToXml(locationMessage);
    }

    /**
     * 发送不同类型的图文消息
     * @param weChatMessageUserInfo
     * @return
     */
    public static String sendMenuNewsMessage(WeChatMessageUserInfo weChatMessageUserInfo, String content){
        String respMessage = null;

        ArticleMessage articleMessage = new ArticleMessage();
        articleMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        articleMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        articleMessage.setFuncFlag(1);
        List<ArticleItem> articleList = new ArrayList<ArticleItem>();
        // 菜单消息
        if ("0".equals(content)){
            respMessage = WeChatMessageModelUtil.sendMenuMessage(weChatMessageUserInfo);
        }
        else if ("1".equals(content)){
            ArticleItem item = new ArticleItem();
            item.setTitle("微信公众帐号开发教程Java版");
            item.setDescription("zw，90后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
            item.setPicUrl("https://pics1.baidu.com/feed/d043ad4bd11373f094f94baa89fbd1fffaed043c.jpeg?token=26a3e47223df80ad3cf4990e9c7f96da&s=001A1DD01C21028E60295D750300C0E2");
            item.setUrl("https://blog.csdn.net/zww1984774346");
            articleList.add(item);
            // 设置图文消息个数
            articleMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }
        else if ("2".equals(content)) {
            ArticleItem article = new ArticleItem();
            article.setTitle("微信公众帐号开发教程Java版");
            // 图文消息中可以使用QQ表情、符号表情
            article.setDescription("zw，90后，" + WeChatUtil.emoji(0x1F6B9)
                    + "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
            // 将图片置为空
            article.setPicUrl("");
            article.setUrl("http://blog.csdn.net/lyq8479");
            articleList.add(article);
            articleMessage.setArticleCount(articleList.size());
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }// 多图文消息
        else if ("3".equals(content)) {
            ArticleItem article1 = new ArticleItem();
            article1.setTitle("微信公众帐号开发教程\n引言");
            article1.setDescription("");
            article1.setPicUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3581523617,2199930107&fm=26&gp=0.jpg");
            article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

            ArticleItem article2 = new ArticleItem();
            article2.setTitle("第2篇\n微信公众帐号的类型");
            article2.setDescription("");
            article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
            article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

            ArticleItem article3 = new ArticleItem();
            article3.setTitle("第3篇\n开发模式启用及接口配置");
            article3.setDescription("");
            article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
            article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            articleMessage.setArticleCount(articleList.size());
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }
        // 多图文消息---首条消息不含图片
        else if ("4".equals(content)) {
            ArticleItem article1 = new ArticleItem();
            article1.setTitle("微信公众帐号开发教程Java版");
            article1.setDescription("");
            // 将图片置为空
            article1.setPicUrl("");
            article1.setUrl("http://blog.csdn.net/lyq8479");

            ArticleItem article2 = new ArticleItem();
            article2.setTitle("第4篇\n消息及消息处理工具的封装");
            article2.setDescription("");
            article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
            article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

            ArticleItem article3 = new ArticleItem();
            article3.setTitle("第5篇\n各种消息的接收与响应");
            article3.setDescription("");
            article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
            article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

            ArticleItem article4 = new ArticleItem();
            article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
            article4.setDescription("");
            article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
            article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            articleList.add(article4);
            articleMessage.setArticleCount(articleList.size());
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }
        // 多图文消息---最后一条消息不含图片
        else if ("5".equals(content)) {
            ArticleItem article1 = new ArticleItem();
            article1.setTitle("第7篇\n文本消息中换行符的使用");
            article1.setDescription("");
            article1.setPicUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3581523617,2199930107&fm=26&gp=0.jpg");
            article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

            ArticleItem article2 = new ArticleItem();
            article2.setTitle("第8篇\n文本消息中使用网页超链接");
            article2.setDescription("");
            article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
            article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

            ArticleItem article3 = new ArticleItem();
            article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
            article3.setDescription("");
            // 将图片置为空
            article3.setPicUrl("");
            article3.setUrl("http://blog.csdn.net/lyq8479");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            articleMessage.setArticleCount(articleList.size());
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }else if ("6".equals(content)){
            //测试单图文回复
            ArticleItem article = new ArticleItem();
            article.setTitle("微信公众帐号开发教程Java版");
            // 图文消息中可以使用QQ表情、符号表情
            article.setDescription("这是测试有没有换行\n\n如果有空行就代表换行成功\n\n点击图文可以跳转到百度首页");
            // 将图片置为空
            article.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
            article.setUrl("http://www.baidu.com");
            articleList.add(article);
            articleMessage.setArticleCount(articleList.size());
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }else if ("7".equals(content)){
            //多图文发送
            ArticleItem article1 = new ArticleItem();
            article1.setTitle("紧急通知，不要捡这种钱！湛江都已经传疯了！\n");
            article1.setDescription("");
            article1.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
            article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=1&sn=8bb6ae54d6396c1faa9182a96f30b225&chksm=bd117e7f8a66f769dc886d38ca2d4e4e675c55e6a5e01e768b383f5859e09384e485da7bed98&scene=4#wechat_redirect");

            ArticleItem article2 = new ArticleItem();
            article2.setTitle("湛江谁有这种女儿，请给我来一打！");
            article2.setDescription("");
            article2.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
            article2.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=2&sn=d7ffc840c7e6d91b0a1c886b16797ee9&chksm=bd117e7f8a66f7698d094c2771a1114853b97dab9c172897c3f9f982eacb6619fba5e6675ea3&scene=4#wechat_redirect");

            ArticleItem article3 = new ArticleItem();
            article3.setTitle("以上图片我就随意放了");
            article3.setDescription("");
            article3.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
            article3.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=3&sn=63e13fe558ff0d564c0da313b7bdfce0&chksm=bd117e7f8a66f7693a26853dc65c3e9ef9495235ef6ed6c7796f1b63abf1df599aaf9b33aafa&scene=4#wechat_redirect");

            articleList.add(article1);
            articleList.add(article2);
            articleList.add(article3);
            articleMessage.setArticleCount(articleList.size());
            articleMessage.setArticles(articleList);
            respMessage = WeChatUtil.newsMessageToXml(articleMessage);
        }
        return respMessage;
    }

    /**
     * 处理关键字信息
     * @param mes
     * @return
     */
    public static String sendKeyWordMessageWithContent(WeChatMessageUserInfo weChatMessageUserInfo, String mes){
        if(WeChatUtil.isQqFace(mes)){
            // 如果为表情，回复表情
            return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo,mes);
        } else if("模版".equals(mes)){
            // 类型发送失败
            return WeChatMessageModelUtil.sendLinkMessage(weChatMessageUserInfo, "模版标题", "模版描述", "https://baidu.com");
        }else if("定位".equals(mes)){
            String  respXml = WeChatMessageModelUtil.sendLocationMessage(weChatMessageUserInfo);
            respXml = respXml.replace("Location__X", "Location_X").replace("Location__Y","Location_Y");
            System.out.print("==="+respXml);
            return respXml;
        }else if("接收图片信息".equals(mes)){
            return WeChatMessageModelUtil.doImageMessage(weChatMessageUserInfo);
        }else  if("二维码".equals(mes)){
            return WeChatMessageModelUtil.doCode(weChatMessageUserInfo);
        }else  if("历史上的今天".equals(mes)){
            return WeChatMessageModelUtil.doTodyOfHistory(weChatMessageUserInfo);
        }// 如果以“歌曲榜”开头
        else if (mes.startsWith("歌曲榜")) {
            // 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉
            String keyWord = mes.replaceAll("^歌曲榜[\\+ ~!@#%^-_=]?", "");
            // 如果歌曲榜关键词名称为空
            if (!("".equals(keyWord))) {
                String[] kwArr = keyWord.split("@");
                // 歌曲榜名称
                String musicPopTitle = kwArr[0];
                try {
                    return WeChatMessageModelUtil.doMusicPop(weChatMessageUserInfo, musicPopTitle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (mes.startsWith("歌曲")) {
            // 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉
            String keyWord = mes.replaceAll("^歌曲[\\+ ~!@#%^-_=]?", "");
            // 如果歌曲名称为空
            if ("".equals(keyWord)) {
                return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo,"歌曲名不能为空哦");
            } else {
                String[] kwArr = keyWord.split("@");
                // 歌曲名称
                String musicTitle = kwArr[0];
                // 搜索音乐
                try {
                    return WeChatMessageModelUtil.doMusicSearch(weChatMessageUserInfo, musicTitle);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }else if(mes.startsWith("天气")){
            // 将天气2个字及天气后面的+、空格、-等特殊符号去掉
            String keyWord = mes.replaceAll("^天气[\\+ ~!@#%^-_=]?", "");
            if ("".equals(keyWord)) {
                return "城市信息为空哦";
            } else {
                String[] kwArr = keyWord.split("@");
                // 歌曲名称
                String weatherTitle = kwArr[0];
                if(WeChatUtil.isInteger(mes)){ // 城市id
                    return WeChatMessageModelUtil.doWeatherById(weChatMessageUserInfo,weatherTitle);
                }else{
                    return WeChatMessageModelUtil.doWeatherByName(weChatMessageUserInfo,weatherTitle);
                }
            }
        }else if(mes.startsWith("快递")){
            // 将快递2个字及天气后面的+、空格、-等特殊符号去掉
            String keyWord = mes.replaceAll("^快递[\\+ ~!@#%^-_=]?", "");
            if ("".equals(keyWord)) {
                return "快递信息不能为空哦";
            } else {
                String[] kwArr = keyWord.split("@");
                // 快递名称
                String expressTitle = kwArr[0];
                String expressOrderId = kwArr[1];
                return WeChatMessageModelUtil.doExpress(weChatMessageUserInfo,expressTitle, expressOrderId);
            }
        }else if(mes.startsWith("笑话")){
            return WeChatMessageModelUtil.collectQiuShi(weChatMessageUserInfo);
        }
        return "";
    }


    /**
     * 发送消息图文
     * @param weChatMessageUserInfo
     * @return
     */
    public String sendNewsMessage(WeChatMessageUserInfo weChatMessageUserInfo){
        ArticleMessage articleMessage = new ArticleMessage();
        articleMessage.setToUserName(weChatMessageUserInfo.getToUserName());
        articleMessage.setFromUserName(weChatMessageUserInfo.getFromUserName());
//        articleMessage.setFuncFlag(0);

        // 图文消息
        List<ArticleItem> items = new ArrayList<>();
        ArticleItem item = new ArticleItem();
        item.setTitle("猫和老鼠");
        item.setDescription("《猫和老鼠》以闹剧为特色，描绘了一对水火不容的冤家：汤姆和杰瑞猫鼠之间的战争，片中的汤姆经常使用狡诈的诡计来对付杰瑞，而杰瑞则时常利用汤姆诡计中的漏洞逃脱他的迫害并给予报复");
        item.setPicUrl("https://pics1.baidu.com/feed/d043ad4bd11373f094f94baa89fbd1fffaed043c.jpeg?token=26a3e47223df80ad3cf4990e9c7f96da&s=001A1DD01C21028E60295D750300C0E2");
        item.setUrl("https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=jerry&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111");
        items.add(item);
        articleMessage.setArticleCount(items.size());
        articleMessage.setArticles(items);

        return WeChatUtil.newsMessageToXml(articleMessage);
    }

    /**
     * @Description: 当系统出错时，默认回复的文本消息
     * @Parameters: WeixinMessageModelUtil
     * @Return: 系统出错回复的消息
     * @Create Date: 2017年10月23日上午11:55:17
     * @Version: V1.00
     * @author:来日可期
     */
    public static String systemErrorResponseMessageModel(WeChatMessageUserInfo weixinMessageInfo ){
        // 回复文本消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(weixinMessageInfo.getToUserName());
        textMessage.setFromUserName(weixinMessageInfo.getFromUserName());
//        textMessage.setFuncFlag(0);
        textMessage.setContent("未能识别的指令，"+"可以访问<a href=\"https://blog.csdn.net/zww1984774346\">MisterZhou的博客哦</a>!^ ~ ^");
        return WeChatUtil.textMessageToXml(textMessage);
    }

    /**
     * @Description: 用户关注时发送的图文消息
     * @Parameters: WeixinMessageModelUtil
     * @Return: 用户关注后发送的提示绑定用户的图文消息
     * @Create Date: 2017年10月23日下午2:01:34
     * @Version: V1.00
     * @author:来日可期
     */
    public static String followResponseMessageModel(WeChatMessageUserInfo weixinMessageInfo){

        // 关注时发送图文消息
        ArticleMessage newsMessage = new ArticleMessage();
        newsMessage.setToUserName(weixinMessageInfo.getToUserName());
        newsMessage.setFromUserName(weixinMessageInfo.getFromUserName());
//        newsMessage.setFuncFlag(0);

        // 图文消息
        List<ArticleItem> articleList=new ArrayList<ArticleItem>();
        ArticleItem article = new ArticleItem();
        // 设置图文消息的标题
        String string = "谢谢您的关注!";
        article.setTitle(string);
        article.setPicUrl("https://avatar.csdn.net/5/F/0/3_zww1984774346.jpg");
        article.setUrl("https://blog.csdn.net/zww1984774346");
        articleList.add(article);

        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);

        return WeChatUtil.newsMessageToXml(newsMessage);
    }

    /**
     * @Description: 用户取消关注，先判断用户是否绑定，如果已经绑定则解除绑定
     * @Parameters: WeixinMessageModelUtil
     * @Return: void
     * @Create Date: 2017年11月20日上午10:54:26
     * @Version: V1.00
     * @author:来日可期
     */
    public void cancelAttention(String fromUserName){

//        if (userService.isAlreadyBinding(fromUserName)) {
//            userService.userUnbinding(fromUserName);
//        }else {
//            System.out.println("取消关注用户{}"+fromUserName+"还未绑定");
//        }
    }

    /**
     * 接收定位
     * @return
     */
    public static String doLocation(WeChatMessageUserInfo weChatMessageUserInfo, String locationX, String locationY, String label){
        String content = "x坐标: "+locationX + "\n"+" y坐标: " + "\n" + locationY+" 地址："+label;
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, content);
    }


    public static String doImageMessage(WeChatMessageUserInfo weChatMessageUserInfo){
        //上传到微信服务器获取mediaId
        String mediaId = UploadUtil.upload("./emailImg/jerry.jpg", "image", WeChatUtil.getAccessToken());
        return WeChatMessageModelUtil.sendImageMessage( weChatMessageUserInfo,mediaId);
    }

    public static String doCode(WeChatMessageUserInfo weChatMessageUserInfo){
        //生成带参数的二维码，并将二维码的链接发送给用户
        String ticket= WeChatUtil.cerateCode();//生成票据
        String url = WeChatContant.SHOW_CODE.replace("TICKET", ticket);
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, url);
    }

    /**
     * 历史上的今天
     * @param weChatMessageUserInfo
     * @return
     */
    public static String doTodyOfHistory(WeChatMessageUserInfo weChatMessageUserInfo){
        // 历史上的今天
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, TodayInHistoryServiceUtil.getTodayInHistoryInfo());
    }


    /**
     * 查询天气 id
     * @param weChatMessageUserInfo
     * @return
     */
    public static String doWeatherById(WeChatMessageUserInfo weChatMessageUserInfo, String cityId){
        WeatherDateService weatherDateService = new WeatherDateServiceImpl();
        JSONObject weatherObject =  weatherDateService.getDateByCityId(cityId);
        String  content = null;
        if(weatherObject.get("data") != null){
            Weather weather = JSONObject.parseObject(JSONObject.toJSONString(weatherObject.get("data")), Weather.class);
            Forecast forecast = weather.getForecast().get(0);
            content = weather.getCity()+"\n"+
                    "今天"+weather.getGanmao()+"\n"+
                    "温度：" + weather.getWendu()+"°C"+ "\n\n"+
                    "明天\n"+
                    "天气："+forecast.getType()+"\n"+
                    "最高温度："+forecast.getHigh()+"\n"+
                    "最低温度："+forecast.getLow()+"\n"+
                    "风向："+forecast.getFengxiang()+"\n"+
                    "风力："+forecast.getFengli().replace("<![CDATA[","");
        }
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, content);
    }

    /**
     * 查询天气 name
     * @param weChatMessageUserInfo
     * @return
     */
    public static String doWeatherByName(WeChatMessageUserInfo weChatMessageUserInfo, String cityName){
        WeatherDateService weatherDateService = new WeatherDateServiceImpl();
        JSONObject weatherObject =  weatherDateService.getDateByCityName(cityName);
        String  content = null;
        if(weatherObject.get("data") != null){
            Weather weather = JSONObject.parseObject(JSONObject.toJSONString(weatherObject.get("data")), Weather.class);
            Forecast forecast = weather.getForecast().get(0);
            content = weather.getCity()+"\n"+
                    "今天"+weather.getGanmao()+"\n"+
                    "温度：" + weather.getWendu()+"°C"+ "\n\n"+
                    "明天\n"+
                    "天气："+forecast.getType()+"\n"+
                    "最高温度："+forecast.getHigh()+"\n"+
                    "最低温度："+forecast.getLow()+"\n"+
                    "风向："+forecast.getFengxiang()+"\n"+
                    "风力："+forecast.getFengli().replace("<![CDATA[","");
        }
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, content);
    }

    /**
     * 查询快递信息
     * @param weChatMessageUserInfo
     * @param expressName
     * @param expressOrderId
     * @return
     */
    public static String doExpress(WeChatMessageUserInfo weChatMessageUserInfo, String expressName, String expressOrderId){
        ExpresssController expresssController = new ExpresssController();
        ResultBody resultBody = expresssController.getExpressInfo(expressName, expressOrderId);
        String  content = "";
        if (resultBody.getCode().equals("200")){
            List<Map<String,String>> datalist = (List<Map<String, String>>) resultBody.getResult();
            for (Map<String,String> model : datalist){
                content += model.get("time") + "\n"+
                          model.get("context")+"\n";
            }
        }
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, content);

    }

    /**
     * 功能菜单
     * @return
     */
    public static String sendMenuMessage(WeChatMessageUserInfo weChatMessageUserInfo) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是小艾，请回复数字选择服务：").append("\n\n");
        buffer.append("1  公众号文章推荐").append("\n");
        buffer.append("2  天气查询,例如天气@北京").append("\n");
        buffer.append("3  快递查询,例如快递@ZT@75133091840496").append("\n");
        buffer.append("4  歌曲排行榜,例如歌曲榜@飙升榜,歌曲榜@TOP500,歌曲榜@网络红歌").append("\n");
        buffer.append("5  歌曲搜索,例如歌曲@断点").append("\n");
        buffer.append("6  笑话,例如笑话").append("\n");
        buffer.append("回复“0”显示此帮助菜单");
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo, buffer.toString());
    }

    /***
     * 音乐排行榜搜索
     * @param musicPopTitle
     * @return
     * @throws Exception
     */
    public static String doMusicPop(WeChatMessageUserInfo weChatMessageUserInfo, String musicPopTitle) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        switch (musicPopTitle) {
            case "飙升榜":
                WeChatMessageModelUtil.collectSongRank(stringBuilder, UrlConst.HOT_RANK_URL);
                break;
            case "TOP500":
                WeChatMessageModelUtil.collectSongRank(stringBuilder, UrlConst.TOP_500_RANK_URL);
                break;
            case "网络红歌":
                WeChatMessageModelUtil.collectSongRank(stringBuilder, UrlConst.NETWORK_HOT_RANK_URL);
                break;
            case "华语新歌":
                WeChatMessageModelUtil.collectSongRank(stringBuilder, UrlConst.HUAYU_NEW_SONG_RANK_URL);
                break;
            case "洗脑神曲":
                WeChatMessageModelUtil.collectSongRank(stringBuilder, UrlConst.XINAO_SONG_RANK_URL);
                break;
            default:
                stringBuilder.append("暂时无此分类噢！");
                break;
        }
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo,stringBuilder.toString());
    }

    /**
     *  歌曲排行搜索
     */
    private static void collectSongRank(StringBuilder stringBuilder, String url) throws IOException {
        RankCollector collector = new RankCollector();
        Rank rank = collector.collect(url);
        stringBuilder.append("\uD83D\uDD25" + rank.getScope() + "[" + rank.getUpdateTime() + "]\n\n");
        for (HotSinger hotSinger : rank.getHotSingerList()) {
            stringBuilder.append(hotSinger.getName() + "-" + hotSinger.getHotSong() + "-" + hotSinger.getSongUrl() + "\n");
        }
    }


    /***
     * 歌曲搜索
     * @param weChatMessageUserInfo
     * @param musicPTitle
     * @return
     * @throws Exception
     */
    public static String doMusicSearch(WeChatMessageUserInfo weChatMessageUserInfo, String musicPTitle) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if ("".equals(musicPTitle)){
            stringBuilder.append("没有查到该歌曲");
        }else{
            WeChatMessageModelUtil.searchCollectSongRank(stringBuilder, UrlConst.MUSIC_SEARCH_URL.replace("kw", musicPTitle));
        }
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo,stringBuilder.toString());
    }

    /**
     *  歌曲搜索
     */
    private static void searchCollectSongRank(StringBuilder stringBuilder, String url) throws IOException {
        RankCollector collector = new RankCollector();
        Rank rank = collector.searchCollect(url);
        stringBuilder.append(rank.getScope()+"\n\n");
        for (HotSinger hotSinger : rank.getHotSingerList()) {
            stringBuilder.append(hotSinger.getName() + "-" + hotSinger.getHotSong() + "-" + hotSinger.getSongUrl() + "\n");
        }
    }


    /**
     *  笑话
     */
    private static String collectQiuShi(WeChatMessageUserInfo weChatMessageUserInfo) {
        QiuShiController collector = new QiuShiController();
        QiuShiModel rank = null;
        try {
            rank = collector.collect(UrlConst.QIUSHI_TEXT_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo,rank.getContent());
    }

}
