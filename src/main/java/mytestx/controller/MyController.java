package mytestx.controller;

import mytestx.dao.UserDao;
import mytestx.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by shixiaoqi on 2016/11/14.
 */
@Controller
public class MyController implements InitializingBean{
    @Autowired
    UserDao userDao;
//    @PostConstruct
//    public void init(){
//        List<User> users =userDao.userList();
//        for (User user:users
//             ) {
//            System.out.println(user.getName());
//        }
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<User> users =userDao.userList();
        for (User user:users
                ) {
            System.out.println(user.getName());
        }
    }
}
