package com.jeesite.modules.job;

import com.jeesite.modules.entity.BusinessCheckPlan;
import com.jeesite.modules.service.SendMsgJobService;
import com.jeesite.modules.entity.BusinessTarget2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *定时推送消息job
 */
@Slf4j
@Component
public class SendMsgJob implements Job {

    @Autowired
    private SendMsgJobService sendMsgJobService;



    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        BusinessTarget2 businessTarget = (BusinessTarget2) jobDataMap.get("businessTarget");
        BusinessCheckPlan businessCheckPlan = (BusinessCheckPlan) jobDataMap.get("businessCheckPlan");
        sendMsgJobService.createTaskJob(businessTarget, businessCheckPlan);
    }


}
