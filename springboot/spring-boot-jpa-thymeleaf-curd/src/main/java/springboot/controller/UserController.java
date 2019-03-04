package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.entity.User;
import springboot.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Controller
//@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping()
    public String index(){
        return "user/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @PostMapping("/add")
    public String add(User user) {
        userService.save(user);
        return "redirect:user/list";
    }

    @GetMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @PostMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:user/list";
    }


    @PostMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:user/list";
    }

}
