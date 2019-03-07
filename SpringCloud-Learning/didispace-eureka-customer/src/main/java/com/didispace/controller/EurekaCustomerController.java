package com.didispace.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
public class EurekaCustomerController {

//    @Autowired
//    private DiscoveryClient client;

//    @Value("${server.port}")
//    private String ip;

    @Autowired
    private LoadBalancerClient client;

//    @Autowired
//    private RestTemplate restTemplate;


//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }


    @GetMapping(value = "/customer")
    public String customer(@RequestParam String name) throws IOException {
       // String services = "Services: " + client.getServices()+" ip :"+ip + "customer";

        //System.out.println(services);


        // 调用其他服务
        name += "!";
        ServiceInstance instance = client.choose("eureka-client");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/client?name=" + name;

        String result = get(url);
        return result;

//        return restTemplate.getForObject(url, String.class);
    }

    public String get(String Url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpGet
        HttpGet httpGet = new HttpGet(Url);
        CloseableHttpResponse response = null;
        try{
            // 执行http请求
            response = httpClient.execute(httpGet);
            //获取http响应体
            HttpEntity entity=response.getEntity();
            if (entity !=null){
                String content= EntityUtils.toString(entity);
                return content;
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            response.close();
            httpClient.close();
        }
        return null;
    }
}
