package com.winner.winnertest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

public class OCRTest {
    @RequestMapping(value = "getBaseCode", method = RequestMethod.POST, headers = "Accept=application/json")
    public String getBaseCode(@RequestParam("file")MultipartFile file){
        // 获取图片64位字符码
        try{
            InputStream inputStream = file.getInputStream();
            // 流转字节数组
            byte[] data = new byte[inputStream.available()];
            // 读取
            inputStream.read(data);
            // 关闭流
            inputStream.close();
            // 转码
            BASE64Encoder base = new BASE64Encoder();
            String encode = base.encode(data);
            return encode;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
