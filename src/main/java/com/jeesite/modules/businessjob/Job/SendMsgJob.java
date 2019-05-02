package com.jeesite.modules.businessjob.Job;

import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesscheckplanuser.entity.BusinessCheckPlanUser;
import com.jeesite.modules.businesscheckplanuser.service.BusinessCheckPlanUserService;
import com.jeesite.modules.businessplanusertask.entity.BusinessPlanUserTask;
import com.jeesite.modules.businessplanusertask.service.BusinessPlanUserTaskService;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstargetdataitem.entity.BusinessTargetDataItem;
import com.jeesite.modules.businesstargetdataitem.service.BusinessTargetDataItemService;
import com.jeesite.modules.msg.entity.content.PcMsgContent;
import com.jeesite.modules.msg.service.MsgPushService;
import com.jeesite.modules.msg.service.support.MsgPushServiceSupport;
import com.jeesite.modules.msg.utils.MsgPushUtils;
import com.jeesite.modules.sys.entity.User;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *定时推送消息job
 */
@Component
public class SendMsgJob implements Job {

    //用户任务
    @Autowired
    private BusinessPlanUserTaskService businessPlanUserTaskService;
    //考核名单
    @Autowired
    private BusinessCheckPlanUserService businessCheckPlanUserService;
    //目标数据项
    @Autowired
    private BusinessTargetDataItemService businessTargetDataItemService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("ssssssssssssssssssss");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        BusinessTarget businessTarget = (BusinessTarget)jobDataMap.get("businessTarget");
        BusinessCheckPlan businessCheckPlan = (BusinessCheckPlan)jobDataMap.get("businessCheckPlan");

        String businessCheckPlanId = businessCheckPlan.getId();
        String businessTargetId = businessTarget.getId();
        //数据采集项
        List<BusinessTargetDataItem> businessTargetDataItemList = businessTargetDataItemService.findByBusinessTargetId(businessTargetId);
        List<BusinessCheckPlanUser> businessCheckPlanUserList = businessCheckPlanUserService.findByBusinessCheckPlanId(businessCheckPlanId);

        businessCheckPlanUserList.forEach(businessCheckPlanUser -> {
            BusinessPlanUserTask businessPlanUserTask = new BusinessPlanUserTask();
            businessPlanUserTask.setBusinessTarget(businessTarget);
            User user = new User();
            user.setId(businessCheckPlanUser.getId());
            businessPlanUserTask.setUser(user);
            businessPlanUserTask.setTaskDescription(businessTarget.getTargetName() +"数据采集任务");
            businessPlanUserTaskService.save(businessPlanUserTask);

            //消息推送
            msgPush(businessPlanUserTask);

        });
    }

    /**
     * 推送消息
     */
    private void  msgPush(BusinessPlanUserTask businessPlanUserTask) {
        PcMsgContent msgContent  =  new  PcMsgContent();
        msgContent.setTitle("任务提醒");
        msgContent.setContent(businessPlanUserTask.getTaskDescription());
        msgContent.addButton("办理",  "/a/demo/demoCustomer/form?id=1120518619533426688");
        //  即时推送消息
        MsgPushUtils.push(msgContent,  "BizKey",  "BizType",  "system");
    }
}
