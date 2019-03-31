package com.zw.service.impl;

import com.zw.model.EmailMessage;
import com.zw.response.ResultBody;
import com.zw.service.EmailService;
import com.zw.service.EmailTemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private Configuration configuration;

    @Override
    public String getTemplateHtml(String templateName, Map model) {
        // 设置模版
        String emailContent = null;
        try {
            Template template = configuration.getTemplate(templateName);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return emailContent;
    }

    @Override
    public ResultBody sendTemplateEmail(String templateName, Map model, EmailMessage emailMessage) {
        // 设置模版
        String emailContent = null;
        try {
            Template template = configuration.getTemplate(templateName);
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        emailService.sendHtmlMail(emailMessage.getTo(),emailMessage.getSubject(), emailContent);
        return new ResultBody();
    }

}
