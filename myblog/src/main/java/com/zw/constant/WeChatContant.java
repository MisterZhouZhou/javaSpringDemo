package com.zw.constant;

public class WeChatContant {
    //APPID
    public static final String appID = "wxd6583fc1256a762f";
    //appsecret
    public static final String appsecret = "ce8b1f130ddade7082667de8be6757fe";
    // Token
    public static final String TOKEN = "xzw";
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String REQ_MESSAGE_TYPE_NEWS = "news";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_MUSIC = "music";
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    public static final String EVENT_TYPE_SUBSCRIBE = "SUBSCRIBE";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "UNSUBSCRIBE";
    public static final String EVENT_TYPE_SCAN = "SCAN";
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    public static final String EVENT_TYPE_CLICK = "CLICK";

    public static final String FromUserName = "FromUserName";
    public static final String ToUserName = "ToUserName";
    public static final String MsgType = "MsgType";
    public static final String Content = "Content";
    public static final String Event = "Event";

    // 添加菜单
    public static final String ADD_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 获取access_token 接口
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 上传图片
    public static final String UPLOAD_URL ="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    // 新增临时素材的接口
    public static final String ADD_FILE_TEMPORARY= "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    //生成二维码的接口
    public static final String CREATE_CODE= "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    //展示二维码的接口
    public static final String SHOW_CODE= "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
}
