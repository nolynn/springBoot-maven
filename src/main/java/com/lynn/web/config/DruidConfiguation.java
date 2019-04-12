package com.lynn.web.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Description:
 * @Date: 2019/4/4 16:26
 * @Auther: lynn
 */
@Configuration
public class DruidConfiguation {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis.typeAliases}")
    private String typeAliases;

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    //配置数据库的基本链接信息
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")    //可以在application.properties中直接导入
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    // 配置事物管理器
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws NamingException, IOException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = null;
        // 实例SessionFactory
        sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // 配置数据源
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliases);

        // 加载MyBatis配置文件
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));
        // 配置mybatis的config文件
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }


}
