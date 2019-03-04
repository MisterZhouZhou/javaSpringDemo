package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.service.MailService;

@RestController
public class MailController {

    @Autowired
    private MailService service;

    @PostMapping("/sendMail")
    public String sendMail() {
        service.sendSimpleMail("2861451012@qq.com","subject", "content");
        return "success";
    }
}