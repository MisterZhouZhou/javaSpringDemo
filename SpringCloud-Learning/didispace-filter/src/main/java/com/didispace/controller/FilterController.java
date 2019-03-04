package com.didispace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping(value = "/filter")
public class FilterController {

    @GetMapping(value = "/failed")
    public Map<String, String > requestFailed(){
        Map<String ,String> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "请求错误");
        return map;
    }

    @GetMapping(value = "/success/info")
    public Map<String, String > requestSuccess(){
        Map<String ,String> map = new HashMap<>();
        map.put("code", "1");
        map.put("msg", "请求成功");
        return map;
    }

}
