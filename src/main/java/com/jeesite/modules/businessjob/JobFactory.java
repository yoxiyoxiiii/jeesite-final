//package com.jeesite.modules.businessjob;
// springboot 2.0 以后自动集成了quartz
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.AdaptableJobFactory;
//
///**
// * 解决 bean 无法注入到 job中
// * @author SYSTEM
// */
//@Configuration
//public class JobFactory extends AdaptableJobFactory {
//
//    @Autowired
//    private AutowireCapableBeanFactory capableBeanFactory;
//
//    @Override
//    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//        Object jobInstance = super.createJobInstance(bundle);
//        capableBeanFactory.autowireBean(jobInstance);
//        return jobInstance;
//    }
//}
