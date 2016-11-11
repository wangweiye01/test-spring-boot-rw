package mytestx.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Administrator on 2016/11/2.
 */
public class BaseDao {
    @Autowired
//    @Qualifier("SqlSessionTemplate")
    protected SqlSession sqlSession;

//    @Autowired
//    @Qualifier("readSqlSessionTemplate")
//    protected SqlSession readsqlSession;

}
