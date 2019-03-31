package com.zw.service;

import com.zw.model.EmailMessage;
import com.zw.response.ResultBody;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmailTemplateService {

    /**
     *  填充模版数据，并获取html
     * @param templateName
     * @param model
     * @return
     */
    public String getTemplateHtml(String templateName, Map model);

    /**
     * 根据模版和数据直接发送邮件
     * @param templateName
     * @param model
     * @return
     */
    public ResultBody sendTemplateEmail(String templateName, Map model, EmailMessage emailMessage);
}
