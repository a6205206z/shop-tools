package com.xingmima.dpfx.rest.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version MybatisConfig, v 0.1
 * @date 2016 /9/13 19:20
 */
@EnableAutoConfiguration
@Configuration
@MapperScan({"com.xingmima.dpfx.rest"})
@EnableTransactionManagement
@PropertySource(value = {"classpath:jdbc.properties"})
public class MybatisConfig {
    //static Logger logger = Logger.getLogger(MybatisConfig.class);

    @Value("${jdbc.driverClassName:com.mysql.jdbc.Driver}")
    private String driverClassName;

    @Value("${jdbc.url:jdbc}")
    private String url;

    @Value("${jdbc.username:root}")
    private String username;

    @Value("${jdbc.password:root}")
    private String password;

    @Value("${jdbc.maxActive:50}")
    private String maxActive;

    @Value("${jdbc.initialSize:3}")
    private String initialSize;

    @Value("${jdbc.minIdle:10}")
    private String minIdle;

    @Value("${jdbc.removeAbandoned:true}")
    private String removeAbandoned;

    @Value("${jdbc.removeAbandonedTimeout:180}")
    private String removeAbandonedTimeout;

    @Value("${jdbc.maxWait:60000}")
    private String maxWait;

    /**
     * The constant MAPPERS_PACKAGE_NAME.
     */
    public static final String MAPPERS_PACKAGE_NAME = "com.xingmima.dpfx.rest.entity";

    /**
     * Property sources placeholder configurer property sources placeholder configurer.
     *
     * @return the property sources placeholder configurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setConnectionProperties(connectionProperties());
        return dataSource;
    }

    /**
     * Connection properties properties.
     *
     * @return the properties
     */
    @Bean
    public Properties connectionProperties() {
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("maxActive", maxActive);
        connectionProperties.setProperty("initialSize", initialSize);
        connectionProperties.setProperty("minIdle", minIdle);
        connectionProperties.setProperty("removeAbandonedTimeout", removeAbandonedTimeout);
        connectionProperties.setProperty("removeAbandoned", removeAbandoned);
        connectionProperties.setProperty("maxWait", maxWait);
        return connectionProperties;
    }

    /**
     * Transaction manager data source transaction manager.
     *
     * @return the data source transaction manager
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * Sql session factory sql session factory.
     *
     * @return the sql session factory
     * @throws Exception the exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage(MAPPERS_PACKAGE_NAME);
        return sessionFactory.getObject();
    }
}
