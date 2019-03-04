package demo.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Boot Hello案例
 *
 * Created by bysocket on 26/09/2017.
 */
@Controller
public class HelloController {

  /**
   * 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
   * 如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。 *
   */
  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  @ResponseBody
  public String sayHello() {
        return "Hello，Spring Boot！";
    }
}
