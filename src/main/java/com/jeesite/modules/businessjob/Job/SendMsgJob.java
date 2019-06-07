package com.jeesite.modules.businessjob.Job;

import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesscheckplanuser.entity.BusinessCheckPlanUser;
import com.jeesite.modules.businesscheckplanuser.service.BusinessCheckPlanUserService;
import com.jeesite.modules.businessjob.dao.BusinessJobJdbc;
import com.jeesite.modules.businessjob.dto.EmployeeDto;
import com.jeesite.modules.businessplanusertask.entity.BusinessPlanUserTask;
import com.jeesite.modules.businessplanusertask.service.BusinessPlanUserTaskService;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstargetdataitem.entity.BusinessTargetDataItem;
import com.jeesite.modules.businesstargetdataitem.service.BusinessTargetDataItemService;
import com.jeesite.modules.msg.entity.content.PcMsgContent;
import com.jeesite.modules.msg.utils.MsgPushUtils;
import com.jeesite.modules.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *定时推送消息job
 */
@Slf4j
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

    @Autowired
    private BusinessJobJdbc businessJobJdbc;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        BusinessTarget businessTarget = (BusinessTarget)jobDataMap.get("businessTarget");
        BusinessCheckPlan businessCheckPlan = (BusinessCheckPlan)jobDataMap.get("businessCheckPlan");

        String businessCheckPlanId = businessCheckPlan.getId();
        String businessTargetId = businessTarget.getId();
        //数据采集项
        List<BusinessTargetDataItem> businessTargetDataItemList = businessTargetDataItemService.findByBusinessTargetId(businessTargetId);
        //考核名单
        List<BusinessCheckPlanUser> businessCheckPlanUserList = businessCheckPlanUserService.findByBusinessCheckPlanId(businessCheckPlanId);

        businessCheckPlanUserList.forEach(businessCheckPlanUser -> {
            //得到被考核的部门
            //获取部门下的员工
            List<EmployeeDto> employeeDtos = businessJobJdbc.findByOfficeCode(businessCheckPlanUser.getDepartmentId());
            log.info("考核部门集合: {}",employeeDtos);
            employeeDtos.forEach(employeeDto -> {
                businessTargetDataItemList.forEach(dateItem->{
                    BusinessPlanUserTask businessPlanUserTask = new BusinessPlanUserTask();
                    User user = new User();
                    user.setUserCode(employeeDto.getEmp_code());
                    user.setUserName(employeeDto.getEmp_name());
                    businessPlanUserTask.setBusinessTarget(businessTarget);
                    businessPlanUserTask.setBusinessTargetDataItem(dateItem);
                    businessPlanUserTask.setStatus("1");//未完成
                    businessPlanUserTask.setUser(user);
                    businessPlanUserTaskService.save(businessPlanUserTask);
                });
            });
            msgPush(employeeDtos);
        });
    }

    /**
     * 推送消息
     */
    private synchronized void  msgPush(List<EmployeeDto> employeeDtos) {
        employeeDtos.forEach(employeeDto -> {
            PcMsgContent msgContent  =  new  PcMsgContent();
            msgContent.setTitle("任务提醒");
            msgContent.setContent("数据采集任务");
            msgContent.addButton("办理",  "/a/demo/demoCustomer/form?id=1120518619533426688");
            //  即时推送消息
//            MsgPushUtils.push(msgContent,  "BizKey",  "BizType",  employeeDto.getEmp_code());
            MsgPushUtils.push(msgContent,  "BizKey",  "BizType",  "system");
        });

    }
}
