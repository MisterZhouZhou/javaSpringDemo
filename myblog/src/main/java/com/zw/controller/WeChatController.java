package com.zw.controller;

import com.zw.service.WeChatService;
import com.zw.utils.wechat.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    /**
     * 1.接入公众号:公众号服务器配置的接口地址为这个
     * 微信授权接口，在微信公众平台配置服务器时，微信会调用这个接口
     * 验证签名
     * @param request
     * @param response
     */
    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     *
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping("/access")
    @ResponseBody
    public String access(@RequestParam(value = "signature") String signature,
                            @RequestParam(value = "timestamp") String timestamp,
                            @RequestParam(value = "nonce") String nonce,
                            @RequestParam(value = "echostr") String echostr){
        // 方式一
//        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
//            return echostr;
//        }
//        return null;
        // 方式二
       return WeChatUtil.checkSignature(signature, timestamp, nonce) ? echostr : null;
    }


//    /**
//     * 消息的接收和回复
//     */
//    @PostMapping("/message")
//    @ResponseBody
//    public void sendWxMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println("————————微信消息接收和发送——————————");
//        //获取消息流
//        WxMpXmlMessage message=WxMpXmlMessage.fromXml(request.getInputStream());
//        //消息的处理：文本 text
//        String msgType = message.getMsgType();//消息的类型
//        String fromUser = message.getFromUser();//发送消息用户的账号
//        String toUser = message.getToUser();//开发者账号
//        String content = message.getContent();//文本内容
//        String msg = message.getMsg();
//
//        //回复文本消息
//        if("text".equals(msgType)) {
//            //创建文本消息内容
//            WxMpXmlOutTextMessage text=WxMpXmlOutTextMessage.TEXT().
//                    toUser(message.getFromUser()).
//                    fromUser(message.getToUser()).
//                    content("你好，很高心认识你").build();
//            //转化为xml格式
//            String xml=text.toXml();
//            System.out.println(xml);
//            //返回消息
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter out=response.getWriter();
//            out.print(xml);
//            out.close();
//        }
//    }


    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "/access")
    public void processMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用核心服务类接收处理请求
        String xmlMessage = weChatService.processRequest(request);
        PrintWriter out = response.getWriter();
        out.print(xmlMessage);
        out.close();
    }


    /**
     * 添加公众号菜单(个人账号没有授权，无法正常调用)
     */
    @PostMapping(value = "/addMenu")
    public void addMenu() {
       weChatService.menuAdd();
    }


    /**
     * 首页访问
     * @return
     */
    @GetMapping(value= "/")
    @ResponseBody
    public String index() {
        return "403";
    }



}
