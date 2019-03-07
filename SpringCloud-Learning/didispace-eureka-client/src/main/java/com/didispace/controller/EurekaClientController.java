package com.didispace.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EurekaClientController {

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private String ip;

    @GetMapping(value = "/client")
    public String client(@RequestParam String name) {
        String services = "Services: " + client.getServices()+" ip :"+ip + "client";

        System.out.println(services);
        return services;
    }
}
