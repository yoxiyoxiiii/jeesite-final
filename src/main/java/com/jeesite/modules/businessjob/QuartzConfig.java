//package com.jeesite.modules.businessjob;
//
//import org.quartz.Scheduler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.Properties;
//
//@Configuration
//public class QuartzConfig {
//
//    @Autowired
//    private JobFactory jobFactory;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
//        final SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setJobFactory(jobFactory);
//
////        schedulerFactoryBean.setQuartzProperties(quartzProperties());
////        schedulerFactoryBean.setDataSource(dataSource);
//
//        //延迟10s 启动
//        schedulerFactoryBean.setStartupDelay(10);
//        return schedulerFactoryBean;
//    }
//
////    //从quartz.properties文件中读取Quartz配置属性
////    @Bean
////    public Properties quartzProperties() throws IOException {
////        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
////        propertiesFactoryBean.setLocation(new ClassPathResource("/config/quartz.properties"));
////        propertiesFactoryBean.afterPropertiesSet();
////        return propertiesFactoryBean.getObject();
////    }
//
//    @Bean
//    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean(dataSource).getScheduler();
//    }
//}
