package com.jeesite.modules.service;

import com.jeesite.modules.entity.BusinessJob;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessJobWrapper {

    @Autowired
    private BusinessJobService businessJobService;

    /**
     * job 添加
     * @param corn 定时任务表达式
     * @param jobGroup
     * @param jobName businessJob.setJobName("com.jeesite.modules.businessjob.Job.SendMsgJob"+"-"+ UUID.randomUUID());
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    public void  addJob(
            final String corn,
            final String jobGroup,
            final String jobName
    ) throws ClassNotFoundException, SchedulerException {
        BusinessJob businessJob = new BusinessJob();
        businessJob.setCorn(corn);
        businessJob.setJobGroup(jobGroup);
        businessJob.setJobName(jobName);
        businessJob.setJobStatus("5");//运行状态
        businessJobService.save(businessJob,new JobDataMap());
    }

    /**
     *
     * @param corn
     * @param jobGroup
     * @param jobName
     * @param jobDataMap 传递上下文参数
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    public void  addJob(
            final String corn,
            final String jobGroup,
            final String jobName,
            final JobDataMap jobDataMap
    ) throws ClassNotFoundException, SchedulerException {
        BusinessJob businessJob = new BusinessJob();
        businessJob.setCorn(corn);
        businessJob.setJobGroup(jobGroup);
        businessJob.setJobName(jobName);
        businessJob.setJobStatus("5");//运行状态
        businessJobService.save(businessJob,jobDataMap);
    }

}
