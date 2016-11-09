package mytestx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Administrator on 2016/11/2.
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfig {
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driverClass}")
    private String driverClass;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
    @Bean(name = "myDataSource")
    @Primary
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setInitialPoolSize(5);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(10);
        dataSource.setIdleConnectionTestPeriod(3000);
        return dataSource;
    }
}
