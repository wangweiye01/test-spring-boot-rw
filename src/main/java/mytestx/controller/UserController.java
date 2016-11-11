package mytestx.controller;

import mytestx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mytestx.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value="/userlist",method= RequestMethod.GET)
    @ResponseBody
    public void userList(){
        List<User> list = userService.userList();
        for (User user:list
             ) {
            System.out.println(user.getName()+"============>");
        }
    }

    @RequestMapping(value = "/adduser",method = RequestMethod.GET)
    @ResponseBody
    public String addUser(){
        User user = new User();
        user.setName("da");
        user.setAge(17);
        userService.addUser(user);
        return "yes";
    }
}
