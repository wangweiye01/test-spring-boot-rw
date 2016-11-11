package mytestx.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/11.
 */
@Aspect
@Component
public class DataSourceAop {
    @Before("execution(* mytestx.controller.UserController.userList())")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        System.out.println("dataSource切换到：Read");
    }

    @Before("execution(* mytestx.controller.UserController.addUser())")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        System.out.println("dataSource切换到：write");
    }
}
