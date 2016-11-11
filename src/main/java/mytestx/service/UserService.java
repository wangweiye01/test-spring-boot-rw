package mytestx.service;

import mytestx.dao.UserDao;
import mytestx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public List<User> userList(){
        return userDao.userList();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }
}
