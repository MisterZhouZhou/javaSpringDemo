package demo.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Hello案例
 *
 * Created by bysocket on 26/09/2017.
 */
@RestController
public class HelloBookController {

    /**
     * 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器
     * InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容
     * **/
    @RequestMapping(value = "/book/hello",method = RequestMethod.GET)
    public String sayHello() {
        return "Hello，《Spring Boot 2.x 核心技术实战 - 上 基础篇》！";
    }
}
