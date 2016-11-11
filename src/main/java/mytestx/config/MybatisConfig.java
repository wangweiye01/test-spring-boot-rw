package mytestx.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2.
 */
@Configuration
@AutoConfigureAfter({ DataBaseConfig.class })
public class MybatisConfig {
    @Value("${mybatis.typeAliasesPackage}")
    protected String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    protected String mapperLocations;
    @Value("${mybatis.configLocation}")
    protected String configLocation;

    @Autowired
    @Qualifier("myDataSource")
    protected DataSource myDataSource;

    @Autowired
    @Qualifier("readDataSource")
    protected DataSource readDataSource;

    @Autowired
    @Qualifier("readDataSource2")
    protected DataSource readDataSource2;

    @Bean(name = "routingDataSource")
    public AbstractRoutingDataSource routingDataSource(){
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(2);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
//        DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
        // 写
//        targetDataSources.put(DataSourceType.write.getType(), SpringContextHolder.getBean("writeDataSource"));
        targetDataSources.put(DataSourceType.write.getType(), myDataSource);

//        for (int i = 0; i < 2; i++) {
//            targetDataSources.put(i, SpringContextHolder.getBean("readDataSource" + (i + 1)));
            targetDataSources.put(0,readDataSource);
            targetDataSources.put(1,readDataSource2);
//        }
//        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    @Bean(name="userSqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory() {
        try {
            //logger.info("userSqlSessionFactory: "+userDataSource.getConnection().getSchema());
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//            factoryBean.setDataSource(myDataSource);
            factoryBean.setTypeAliasesPackage(typeAliasesPackage);
            factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
            factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            factoryBean.setDataSource(routingDataSource());
            SqlSessionFactory sqlSessionFactory = null;
            try {
                sqlSessionFactory = factoryBean.getObject();
            }catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory
                    .getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);

            return sqlSessionFactory;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   /* @Bean(name="readSqlSessionFactory")
    public SqlSessionFactory ReadSqlSessionFactory() {
        try {
            //logger.info("userSqlSessionFactory: "+userDataSource.getConnection().getSchema());
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(readDataSource);
//            factoryBean.setTypeAliasesPackage(propertyResolver
//                    .getProperty("typeAliasesPackage"));
//            factoryBean
//                    .setMapperLocations(new PathMatchingResourcePatternResolver()
//                            .getResources(propertyResolver
//                                    .getProperty("mapperLocations")));
//            factoryBean
//                    .setConfigLocation(new DefaultResourceLoader()
//                            .getResource(propertyResolver
//                                    .getProperty("configLocation")));

            SqlSessionFactory sqlSessionFactory = null;
            try {
                sqlSessionFactory = factoryBean.getObject();
            }catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory
                    .getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);

            return sqlSessionFactory;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/



    @Bean(name = "SqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(){
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(SqlSessionFactory());
        return sessionTemplate;
    }

//    @Bean(name = "readSqlSessionTemplate")
//    public SqlSessionTemplate getReadSqlSessionTemplate(){
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(ReadSqlSessionFactory());
//        return sessionTemplate;
//    }

}
