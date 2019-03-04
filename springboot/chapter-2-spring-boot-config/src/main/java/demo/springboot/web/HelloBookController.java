package demo.springboot.web;

import demo.springboot.config.BookComponent;
import demo.springboot.config.BookProperites;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BookProperites bookProperites;

    @Autowired
    BookComponent bookComponent;

    @RequestMapping(value = "/book/hello",method = RequestMethod.GET)
    public String sayHello() {
        return "Hello， " + bookProperites.getWriter() + "is writing "+ bookProperites.getName() + " ! ";
    }

    @RequestMapping(value = "/book/hello2",method = RequestMethod.GET)
    public String sayHello2() {
        bookComponent.setName("dd");
        bookComponent.setWriter("zw");
        return "Hello， " + bookComponent.getWriter() + " is writing "+ bookComponent.getName() + " ! ";
    }
}
