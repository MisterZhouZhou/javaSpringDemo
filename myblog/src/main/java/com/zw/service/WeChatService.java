package com.zw.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface WeChatService {
    /**
     * 公众号消息接收和发送
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request);

    public String  getAccessToken();

    /**
     * 添加微信公众号菜单
     */
    public boolean menuAdd();
}
