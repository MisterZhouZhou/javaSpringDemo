package com.zw.controller;

import com.zw.model.EmailMessage;
import com.zw.response.ResultBody;
import com.zw.service.EmailService;
import com.zw.service.EmailTemplateService;
import com.zw.utils.FileUtil;
import com.zw.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailTemplateService emailTemplateService;

    // 页面请求
    @GetMapping("/love")
    public ModelAndView love(){
        ModelAndView mav = new ModelAndView("/love");
        return mav;
    }

    /**
     * 发送Freemarker邮件模版
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendLoveMail")
    public ResultBody sendLoveMail(@RequestBody EmailMessage emailMessage) {
//        Map<String, Object> model = new HashMap<>();
//        model.put("info", "您好，感谢您的注册，这是一封验证邮件，请点击下面的连接完成注册，感谢您的支持！");
//        model.put("activedUrl","http://127.0.0.1:9090/activateUser/12121");
        // 复杂页面发送失败，可能与资源是本地有关
        emailTemplateService.sendTemplateEmail("loveMarker.ftl", null, emailMessage);
        return new ResultBody();
    }

    /**
     * 发送普通邮件
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendMail")
    public ResultBody sendMail(@RequestBody EmailMessage emailMessage) {
        emailService.sendSimpleMail(emailMessage.getTo(),emailMessage.getSubject(), emailMessage.getContent());
        return new ResultBody();
    }

    /**
     * 发送html邮件
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendHtmlMail")
    public ResultBody sendHtmlMail(@RequestBody EmailMessage emailMessage) {
        emailService.sendHtmlMail(emailMessage.getTo(),emailMessage.getSubject(), emailMessage.getContent());
        return new ResultBody();
    }

    /**
     * 发送带附件的邮件
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendAttachmentsMail")
    public ResultBody sendAttachmentsMail(@RequestBody EmailMessage emailMessage) {
        String filePath = emailMessage.getFilePath();
        // 网络资源
        if (filePath.contains("http://") || filePath.contains("https://")){
            int index = filePath.lastIndexOf("/")+1;
            String fileName = filePath.substring(index);
            try {
                String base64ImageStr = ImageUtil.getURLImage(filePath).replace("\n", "");
                System.out.println(this.getClass().getResource("/").getPath());
                String fileSourcePath = "./emailImg/";
                FileUtil fileUtil = new FileUtil();
                File tmpFile = fileUtil.base64ToFile(fileSourcePath, base64ImageStr, fileName);
                if(tmpFile != null){
                    filePath = fileSourcePath + fileName;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//        String filePath = "./博客前端架构.xmind";
        emailService.sendAttachmentsMail(emailMessage.getTo(),emailMessage.getSubject(), emailMessage.getContent(), filePath);
        return new ResultBody();
    }

    /**
     * 发送带附件的邮件
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendInlineResourceMail")
    public ResultBody sendInlineResourceMail(@RequestBody EmailMessage emailMessage) {
        String filePath = emailMessage.getFilePath();
        // 网络资源
        if (filePath.contains("http://") || filePath.contains("https://")){
            int index = filePath.lastIndexOf("/")+1;
            String fileName = filePath.substring(index);
            try {
                String base64ImageStr = ImageUtil.getURLImage(filePath).replace("\n", "");
                System.out.println(this.getClass().getResource("/").getPath());
                String fileSourcePath = "./emailImg/";
                FileUtil fileUtil = new FileUtil();
                File tmpFile = fileUtil.base64ToFile(fileSourcePath, base64ImageStr, fileName);
                if(tmpFile != null){
                    filePath = fileSourcePath + fileName;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
//      String imPath = "./emailimages/3_ityouknow.jpg";
        emailService.sendInlineResourceMail(emailMessage.getTo(),emailMessage.getSubject(), content, filePath, rscId);
        return new ResultBody();
    }

    /**
     * 发送激活用户html邮件
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendActivitateHtmlMail")
    public ResultBody sendActivitateHtmlMail(@RequestBody EmailMessage emailMessage) {
        String content="<html>\n" +
                "<body>\n" +
                "您好,这是验证邮件,请点击下面的链接完成验证,<br/>" +
                "<a href=\"http://127.0.0.1:9090/activateUser/12121\">激活账号</a>"+
                "</body>\n" +
                "</html>";
        emailService.sendHtmlMail(emailMessage.getTo(),emailMessage.getSubject(), content);
        return new ResultBody();
    }


    /**
     * 发送Freemarker邮件模版
     * @param emailMessage
     * @return
     */
    @PostMapping("/sendFreemarkerMail")
    public ResultBody sendFreemarkerMail(@RequestBody EmailMessage emailMessage) {
        Map<String, Object> model = new HashMap<>();
        model.put("info", "您好，感谢您的注册，这是一封验证邮件，请点击下面的连接完成注册，感谢您的支持！");
        model.put("activedUrl","http://127.0.0.1:9090/activateUser/12121");
        emailTemplateService.sendTemplateEmail("emailFreeMarker.ftl", model, emailMessage);
        return new ResultBody();
    }




}