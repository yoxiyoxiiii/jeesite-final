package com.jeesite.modules.service;

import com.jeesite.modules.dto.EmployeeDto;
import com.jeesite.modules.dao.BusinessJobJdbc;
import com.jeesite.modules.entity.*;
import com.jeesite.modules.msg.entity.content.PcMsgContent;
import com.jeesite.modules.msg.utils.MsgPushUtils;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成部门上报数据任务
 */
@Slf4j
@Service
@Transactional(readOnly=true)
public class SendMsgJobService {

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
    private BusinessTarget2Service businessTarget2Service;

    @Autowired
    private BusinessJobJdbc businessJobJdbc;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 任务监控
     */
    @Autowired
    private BusinessTargetTaskMonitorService businessTargetTaskMonitorService;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void createTaskJob(BusinessTarget2 businessTarget, BusinessCheckPlan businessCheckPlan) {
        String businessCheckPlanId = businessCheckPlan.getId();
        String businessTargetId = businessTarget.getId();
        BusinessTarget2 businessTarget21 = businessTarget2Service.get(businessTargetId);
        //数据采集项
        List<BusinessTargetDataItem> businessTargetDataItemList = businessTargetDataItemService.findByBusinessTargetId(businessTargetId);
        //考核名单
        List<BusinessCheckPlanUser> businessCheckPlanUserList = businessCheckPlanUserService.findByBusinessCheckPlanId(businessCheckPlanId);

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        businessCheckPlanUserList.forEach(businessCheckPlanUser -> {
            //得到被考核的部门
            //获取每个部门下的一个数据上报人员
            List<EmployeeDto> employeeDtoList = businessJobJdbc.findByOfficeCode(businessCheckPlanUser.getDepartmentId(), "dataReporter");
            for (EmployeeDto employeeDto : employeeDtoList) {
                if (employeeDto.getOfficeCode() == null) {
                    log.error("该部门没有数据上报员! {}", businessCheckPlanUser.getDepartmentId());
                    new RuntimeException(businessCheckPlanUser.getDepartmentId() + "该部门没有数据上报员!");
                    continue;
                }

                BusinessTargetTaskMonitor businessTargetTaskMonitor = new BusinessTargetTaskMonitor();
                businessTargetTaskMonitor.setDataItemCount(businessTarget21.getBusinessTargetDataItem2List().size());
                BusinessTarget2 businessTarget2 = new BusinessTarget2();
                businessTarget2.setId(businessTarget.getId());
                businessTargetTaskMonitor.setBusinessTarget2(businessTarget2);
                Office o = new Office();
                o.setOfficeCode(employeeDto.getOfficeCode());
                businessTargetTaskMonitor.setOffice(o);
                businessTargetTaskMonitor.setBusinessCheckPlan(businessCheckPlan);
                BusinessTargetTaskMonitor taskMonitor = businessTargetTaskMonitorService.findByIds(businessTarget.getId(), employeeDto.getOfficeCode(), businessCheckPlanId);
                if (taskMonitor != null) {
                    log.info("考核部门任务数据存在: {}", employeeDto);
                    continue;
                }
                log.info("考核部门集合: {}", employeeDto);
                businessTargetTaskMonitor.setBusinessCheckPlan(businessCheckPlan);
                businessTargetTaskMonitor.setStatus("2");
                //保存监控主表
                businessTargetTaskMonitorService.save(businessTargetTaskMonitor);
                BusinessTargetTaskMonitor monitor = businessTargetTaskMonitorService.get(businessTargetTaskMonitor);

                for (BusinessTargetDataItem dateItem : businessTargetDataItemList) {

                    BusinessPlanUserTask businessPlanUserTask = new BusinessPlanUserTask();
                    businessPlanUserTask.setMonitorId(monitor.getId());//设置监控ID
                    User user = new User();
                    user.setUserCode(employeeDto.getEmp_code());
                    user.setUserName(employeeDto.getEmp_name());
                    businessPlanUserTask.setBusinessTarget(businessTarget);
                    businessPlanUserTask.setBusinessTargetDataItem(dateItem);
                    businessPlanUserTask.setTaskStatus(1);//未完成
                    businessPlanUserTask.setTaskDescription(dateItem.getItemDescription());
                    businessPlanUserTask.setUser(user);

                    businessPlanUserTask.setBusinessCheckPlanId(businessCheckPlanId);//设置考核计划
                    //设计计划开始时间和结束时间
                    businessPlanUserTask.setTaskStartTime(businessCheckPlan.getPlanStartTime());
                    businessPlanUserTask.setTaskEndTime(businessCheckPlan.getPlanEndTime());
                    businessPlanUserTask.setOffice(o);
                    businessPlanUserTaskService.save(businessPlanUserTask);
                    employeeDtos.add(employeeDto);
                }
                msgPush(employeeDtos);
            }
        });
    }

    /**
     * 推送消息
     */
    private synchronized void msgPush(List<EmployeeDto> employeeDtos) {
        employeeDtos.forEach(employeeDto -> {
            PcMsgContent msgContent = new PcMsgContent();
            msgContent.setTitle("任务提醒");
            msgContent.setContent("数据采集任务");
            msgContent.addButton("办理", "/a/demo/demoCustomer/form?id=1120518619533426688");
            //  即时推送消息
//            MsgPushUtils.push(msgContent,  "BizKey",  "BizType",  employeeDto.getEmp_code());
            MsgPushUtils.push(msgContent, "BizKey", "BizType", "system");
        });

    }
}
