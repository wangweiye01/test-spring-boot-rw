package mytestx.controller;

import mytestx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mytestx.service.UserService;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value="/userlist",method= RequestMethod.GET)
    public List<User> userList(){
        return userService.userList();
    }
}
