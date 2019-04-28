package com.jeesite.modules.businessjob.service;

import com.jeesite.common.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author SYSTEM
 */
@Slf4j
@Service
public class QuartzService {

    @Autowired
    private Scheduler scheduler;


    /**
     * 添加 job
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    public void addJob(final String jobName,
                       final String jobGroup,
                       final String cronExpression,
                       final JobDataMap jobDataMap
                       ) throws ClassNotFoundException, SchedulerException {

        //设置 触发器
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
        final CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();

        //获取job 的 子类
        final Class<? extends Job> jobClazz = Class.forName(jobName).asSubclass(Job.class);

        //设置 jobDetail
        final JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
        final JobDetail jobDetail = JobBuilder.newJob(jobClazz)
                .withIdentity(jobKey)

                //使用参数
                .usingJobData(jobDataMap)
                .build();

        //将 job 和 触发器 注册到 调度器
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 修改定时任务.
     */
    public void edit(final String jobName,
                     final String jobGroup,
                     final String cronExpression) throws SchedulerException {


            final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            final CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            final CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder).build();

            final JobKey jobKey = new JobKey(jobName, jobGroup);
            final JobBuilder jobBuilder = scheduler.getJobDetail(jobKey).getJobBuilder();

            final JobDetail jobDetail = jobBuilder.build();

            final Set<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
    }

    /**
     * 删除定时任务.
     */
    public void delete(final String jobName, final String jobGroup) {
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                //停止触发器
                scheduler.pauseTrigger(triggerKey);
                //解除job
                scheduler.unscheduleJob(triggerKey);

                //删除job
                JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
                scheduler.deleteJob(jobKey);
                log.info("===> delete, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    /**
     * 暂停定时任务.
     */
    public void pause(final String jobName,
                      final String jobGroup) {
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                log.info("===> Pause success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 重新开始任务.
     */
    public void resume(final String jobName, final String jobGroup) {
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.resumeTrigger(triggerKey);
                log.info("===> Resume success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.error("恢复任务时出现异常", e);
        }
    }

    /**
     * 验证是否存在.
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
