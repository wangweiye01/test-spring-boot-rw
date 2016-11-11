package mytestx.dao;


import mytestx.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
@Component
public class UserDao extends BaseDao {
    public List<User> userList(){
        return sqlSession.selectList("getusers");
    }

    public void addUser(User user) {
        sqlSession.insert("addUser",user);
    }
}

