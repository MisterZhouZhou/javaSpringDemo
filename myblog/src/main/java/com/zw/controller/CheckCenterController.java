package com.zw.controller;

import com.alibaba.fastjson.JSON;
import com.zw.component.WebSocketServer;
import com.zw.model.SocketMessage;
import com.zw.response.ErrorInfoInterface;
import com.zw.response.ResultBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(value = "/checkcenter")
public class CheckCenterController {

    // 页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid){
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
    @PostMapping("/socket/push/{cid}")
    @ResponseBody
    public ResultBody pushToWeb(@PathVariable String cid, @RequestBody SocketMessage socketMessage) {
        try {
            String message = JSON.toJSONString(socketMessage);
            WebSocketServer.sendInfo(cid,message);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorInfoInterface infoInterface = new ErrorInfoInterface() {
                @Override
                public String getCode() {
                    return "502";
                }
                @Override
                public String getMessage() {
                    return "报错了"+cid+"#"+e.getMessage();
                }
            };
            return new ResultBody(infoInterface);
        }
        return new ResultBody(cid);
    }

}
