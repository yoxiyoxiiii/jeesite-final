package com.jeesite.modules.businessjob;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * springboot 默认整合quartz
 * jdbc 持久化job 时 需要单配置数据源，
 * 公用数据源会产生数据表被锁，事务异常回滚，导致job 信息无法从数据库中获取
 * springboot2.0 + 官方文档说明
 */
@Configuration
public class QuartzDataSourceConfig {

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql:///jeesite?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        return druidDataSource;
    }
}
