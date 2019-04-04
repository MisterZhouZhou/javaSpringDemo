package com.zw.service.impl;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zw.constant.WeChatContant;
import com.zw.model.wechat.*;
import com.zw.service.WeChatService;
import com.zw.utils.HttpUtil;
import com.zw.utils.wechat.WeChatMessageModelUtil;
import com.zw.utils.wechat.WeChatUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class WeChatServiceImpl implements WeChatService {

    // 添加菜单
    public static final String ADD_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //获取access_token 接口
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
            + "grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Override
    public String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent;
        try {
            // 调用parseXml方法解析请求消息
            Map<String,String> requestMap = WeChatUtil.parseXml(request);
            // 构建消息model
            WeChatMessageUserInfo weChatMessageUserInfo = new WeChatMessageUserInfo();
            weChatMessageUserInfo.setFromUserName(requestMap.get(WeChatContant.ToUserName));
            weChatMessageUserInfo.setToUserName(requestMap.get(WeChatContant.FromUserName));
            // 消息类型
            String msgType = (String) requestMap.get(WeChatContant.MsgType);
            String mes = null;

            // 文本消息
            if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_TEXT)) {

                mes =requestMap.get(WeChatContant.Content).toString();
                if(mes!=null && WeChatUtil.isInteger(mes) && Integer.parseInt(mes) < 10){
                    respXml = WeChatMessageModelUtil.sendMenuNewsMessage(weChatMessageUserInfo, mes);
                }else if(WeChatUtil.isQqFace(mes)){
                    // 如果为表情，回复表情
                    respXml = WeChatMessageModelUtil.sendTextMessage(weChatMessageUserInfo,mes);
                } else if("模版".equals(mes)){
                    respXml = "<xml><ToUserName><![CDATA[oygJk1g3GMXHtbrL5ORIH5GWCb08]]></ToUserName><FromUserName><![CDATA[gh_58845e266a8c]]></FromUserName><CreateTime>1554294661205</CreateTime><MsgType><![CDATA[link]]></MsgType><Title><![CDATA[公众平台官网链接]]></Title><Description><![CDATA[公众平台官网链接]]></Description><Url><![CDATA[http://xxx.xx/test/index.html]]></Url></xml>";
                    System.out.print("==="+respXml);
//                    respXml = WeChatMessageModelUtil.sendLinkMessage(weChatMessageUserInfo, "模版标题", "模版描述", "https://baidu.com");
//                    System.out.print(respXml);
                }else if("定位".equals(mes)){
                    respXml = WeChatMessageModelUtil.sendLocationMessage(weChatMessageUserInfo);
                    System.out.println(respXml);
                    respXml="<xml>\n" +
                            "  <ToUserName><![CDATA[oygJk1g3GMXHtbrL5ORIH5GWCb08]]></ToUserName>\n" +
                            "  <FromUserName><![CDATA[gh_58845e266a8c]]></FromUserName>\n" +
                            "  <CreateTime>1554293977566</CreateTime>\n" +
                            "  <MsgType><![CDATA[location]]></MsgType>\n" +
                            "  <Location_X>23.134521</Location_X>\n" +
                            "  <Location_Y>113.358803</Location_Y>\n" +
                            "  <Scale>20</Scale>\n" +
                            "  <Label><![CDATA[位置信息]]></Label>\n" +
                            "</xml>";
                    System.out.println("==="+respXml);
                    respXml="<xml>\n" +
                            "  <ToUserName><![CDATA[oygJk1g3GMXHtbrL5ORIH5GWCb08]]></ToUserName>\n" +
                            "  <FromUserName><![CDATA[gh_58845e266a8c]]></FromUserName>\n" +
                            "  <CreateTime>1554293977566</CreateTime>\n" +
                            "  <MsgType><![CDATA[text]]></MsgType>\n" +
                            "  <Content><![CDATA[dddd]]></Content>\n" +
                            "</xml>";

                }

            }
            // 图片消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 语音消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 视频消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 地理位置消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 链接消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
                respContent += "<a href=\"https://blog.csdn.net/zww1984774346\">MisterZhou的博客哦</a>";
                respXml = WeChatMessageModelUtil.sendLinkMessage(weChatMessageUserInfo, "链接标题", "链接描述", respContent);
            }
            // 事件推送
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = (String) requestMap.get(WeChatContant.Event);
                // 关注
                if (eventType.equals(WeChatContant.EVENT_TYPE_SUBSCRIBE)) {
                    respXml = WeChatMessageModelUtil.followResponseMessageModel(weChatMessageUserInfo);
                }
                // 取消关注
                else if (eventType.equals(WeChatContant.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(WeChatContant.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(WeChatContant.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(WeChatContant.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
            mes = mes == null ? "不知道你在干嘛" : mes;
            if(respXml == null){
                respXml = WeChatMessageModelUtil.systemErrorResponseMessageModel(weChatMessageUserInfo);
               // System.out.println(respXml);
            }
            return respXml;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("=========XXXX");
        }
        return "";
    }

    @Override
    public String getAccessToken() {
        AccessToken accessToken = null;
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", WeChatContant.appID).replace("APPSECRET", WeChatContant.appsecret);
        JSONObject jsonObject = HttpUtil.httpGet(requestUrl);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInteger("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
            }
        }
        return accessToken.getAccess_token();
    }

    @Override
    public boolean menuAdd() {
        String access_token = getAccessToken();
        if (access_token == null){
            return false;
        }
        String url = ADD_MENU_URL.replace("ACCESS_TOKEN", access_token);

        Menu menu = new Menu();
        ViewButton viewButton = new ViewButton();
        viewButton.setName("搜索");
        viewButton.setType("view");
        viewButton.setUrl("https://www.baidu.com");

        SendPicButton button21 = new SendPicButton();
        button21.setName("发图");
        button21.setType("pic_photo_or_album");
        button21.setKey("pic");

        SendLocalButton button32 = new SendLocalButton();
        button32.setName("发位置");
        button32.setType("location_select");
        button32.setKey("local");

        ClickButton button31 = new ClickButton();
        button31.setName("点赞");
        button31.setType("click");
        button31.setKey("strtest");//事件key

        Button button = new Button();
        button.setName("click2");
        button.setSub_button(new Button[]{button31,button32});
        button.setSub_button(new Button[]{button31,button32});

        menu.setButton(new Button[]{viewButton,button21,button});

        String menuString = JSONObject.toJSONString(menu);
        JSONObject result = HttpUtil.httpPost(url, JSONObject.parseObject(menuString));
        System.out.println(result+"====");

        return true;
    }
}
