package com.jeesite.modules.businessjob.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 *定时推送消息job
 */
@Component
public class SendMsgJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("ssssssssssssssssssss");
    }
}
