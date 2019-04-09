package com.zw.utils.wechat;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zw.weChatCode.*;
import com.zw.constant.WeChatContant;
import com.zw.model.wechat.*;
import com.zw.model.wechat.res.*;
import com.zw.utils.FileUtil;
import com.zw.utils.HttpUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class WeChatUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { WeChatContant.TOKEN, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        // Arrays.sort(arr);
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    private static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 解析微信发来的请求(xml)
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked"})
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String,String> map = new HashMap<String,String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * @Description: 文本消息对象转换成xml
     * @param  textMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String textMessageToXml(TextMessage textMessage) {

        // 第二种方式
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * @Description: 图片消息对象转换成xml
     * @param  imageMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * @Description: 链接消息对象转换成xml
     * @param  linkMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String linkMessageToXml(LinkMessage linkMessage) {
        xstream.alias("xml", linkMessage.getClass());
        return xstream.toXML(linkMessage);
    }

    /**
     * @Description: 定位消息对象转换成xml
     * @param  locationMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String locationMessageToXml(LocationMessage locationMessage) {
        xstream.alias("xml", locationMessage.getClass());
        return xstream.toXML(locationMessage);
    }

    /**
     * @Description: 图文消息对象转换成xml
     * @param  newsMessage
     * @date   2016-12-01
     * @return  xml
     */

    public static String newsMessageToXml(ArticleMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new ArticleItem().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * @Description: 语音消息对象转换成xml
     * @param  voiceMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * @Description: 视频消息对象转换成xml
     * @param  videoMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String videoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * @Description: 音乐消息对象转换成xml
     * @param  musicMessage
     * @date   2016-12-01
     * @return  xml
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 对象到xml的处理
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * emoji表情转换(hex -> utf-16)
     *
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否是QQ表情
     *
     * @param content
     * @return
     */
    public static boolean isQqFace(String content) {
        boolean result = false;

        // 判断QQ表情的正则表达式
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            result = true;
        }
        return result;
    }


    /**
     * 获取access_token
     * access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
     //* @param APPID 凭证
     //* @param APPSECRET 密钥
     * @return
     */
    public static String  getAccessToken() {
        AccessToken accessToken = null;
        String requestUrl = WeChatContant.ACCESS_TOKEN_URL.replace("APPID", WeChatContant.appID).replace("APPSECRET", WeChatContant.appsecret);
        JSONObject jsonObject = HttpUtil.httpGet(requestUrl);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInteger("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken.getAccess_token();
    }


    /**
     * 生成带参数的二维码
     * @return
     */
    public static String cerateCode() {
        String url  = WeChatContant.CREATE_CODE.replace("ACCESS_TOKEN", WeChatUtil.getAccessToken());
        String code = JSONObject.toJSONString(WeChatUtil.initStrCode());
        JSONObject result = HttpUtil.httpPost(url,JSONObject.parseObject(code));
        if("ok".equals(result.getString("ticket"))){
            System.out.println("result"+result);
        }
        return result.getString("ticket");
    }

    /**
     * 生成临时的以字符串为参数的二维码
     * @return
     */
    public static TemporaryCode<?> initStrCode() {
        TemporaryCode<Object> code = new TemporaryCode<>();
        ActionInfo<Object> actionInfo = new ActionInfo<>();
        SceneStr scene = new SceneStr();
        scene.setScene_str("test");
        actionInfo.setScene(scene);
        code.setAction_info(actionInfo);
        code.setAction_name("QR_STR_SCENE");
        code.setExpire_seconds(604800);//过期时间
        return code;
    }
    /**
     * 生成临时的以字符串为参数的二维码
     * @return
     */
    public static TemporaryCode<?> initIdCode() {
        TemporaryCode<Object> code = new TemporaryCode<>();
        ActionInfo<Object> actionInfo = new ActionInfo<>();
        SceneId scene = new SceneId();
        scene.setScene_id(123);
        actionInfo.setScene(scene);
        code.setAction_info(actionInfo);
        code.setAction_name("QR_STR_SCENE");
        code.setExpire_seconds(604800);//过期时间
        return code;
    }
    /**
     * 生成永久二维码 scene_id
     */
    public static PermanentCode<?> initIdCodePer() {
        PermanentCode<Object> code = new PermanentCode<>();
        ActionInfo<Object> actionInfo = new ActionInfo<>();
        SceneId scene = new SceneId();
        scene.setScene_id(123);
        actionInfo.setScene(scene);
        code.setAction_info(actionInfo);
        code.setAction_name("QR_LIMIT_STR_SCENE");
        return code;
    }
    /**
     * 生成永久二维码 scene_str
     * @return
     */
    public static PermanentCode<?> initStrCodePer() {
        PermanentCode<Object> code = new PermanentCode<>();
        ActionInfo<Object> actionInfo = new ActionInfo<>();
        SceneStr scene = new SceneStr();
        scene.setScene_str("test");
        actionInfo.setScene(scene);
        code.setAction_info(actionInfo);
        code.setAction_name("QR_LIMIT_STR_SCENE");
        return code;
    }

}
