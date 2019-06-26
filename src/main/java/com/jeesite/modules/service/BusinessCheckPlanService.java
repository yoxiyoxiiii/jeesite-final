/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.BusinessCheckPlanDao;
import com.jeesite.modules.dao.BusinessJobJdbc;
import com.jeesite.modules.dao.DictDataJdbc;
import com.jeesite.modules.dto.EmployeeDto;
import com.jeesite.modules.entity.BusinessCheckPlan;
import com.jeesite.modules.entity.BusinessCheckPlanUser;
import com.jeesite.modules.entity.BusinessJob;
import com.jeesite.modules.entity.BusinessTarget2;
import com.jeesite.modules.sys.service.OfficeService;
import com.jeesite.modules.sys.utils.DictUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 考核计划Service
 *
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@Service
@Transactional(readOnly = false)
public class BusinessCheckPlanService extends CrudService<BusinessCheckPlanDao, BusinessCheckPlan> {


    //考核名单
    @Autowired
    private BusinessCheckPlanUserService businessCheckPlanUserService;
    @Autowired
    private BusinessJobJdbc businessJobJdbc;
    // 考核细则
    @Autowired
    private BusinessTarget2Service businessTarget2Service;
    // job
    @Autowired
    private BusinessJobService businessJobService;

    @Autowired
    private OfficeService officeService;


    /**
     * 获取单条数据
     *
     * @param businessCheckPlan
     * @return
     */
    @Override
    public BusinessCheckPlan get(BusinessCheckPlan businessCheckPlan) {
        return super.get(businessCheckPlan);
    }

    /**
     * 查询分页数据
     *
     * @param businessCheckPlan 查询条件
     * @return
     */
    @Override
    public Page<BusinessCheckPlan> findPage(BusinessCheckPlan businessCheckPlan) {
        return super.findPage(businessCheckPlan);
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param businessCheckPlan
     */
    @Override
    @Transactional(readOnly = false)
    public void save(BusinessCheckPlan businessCheckPlan) {
        super.save(businessCheckPlan);
    }

    /**
     * 更新状态
     *
     * @param businessCheckPlan
     */
    @Override
    @Transactional(readOnly = false)
    public void updateStatus(BusinessCheckPlan businessCheckPlan) {

        //日志: 谁,什么时间,什么操作?
        BusinessCheckPlan temp = get(businessCheckPlan.getId());
        String logs = temp.getRemarks();
        //操作状态
        String option = DictUtils.getDictLabel("sys_status", businessCheckPlan.getStatus(), "处理");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒  EE", Locale.CHINA);
        String log = "用户 %s 在 %s 时间,对该计划进行 %s 操作\r\n";
        logs += String.format(log,
                UserUtils.getUser().getUserName(),
                df.format(date),
                option
        );
        businessCheckPlan.setRemarks(logs);
        businessCheckPlan.setIsNewRecord(false);
        super.save(businessCheckPlan);
        super.updateStatus(businessCheckPlan);
    }

    /**
     * 更新状态,关联定时任务
     *
     * @param businessCheckPlan
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> start(BusinessCheckPlan businessCheckPlan) throws SchedulerException, ClassNotFoundException {
        Map<String, Object> addJob = addJob(businessCheckPlan);//一个考核计划一个job
        this.updateStatus(businessCheckPlan);
        return addJob;
    }

    private Map<String, Object> addJob(BusinessCheckPlan businessCheckPlan) {
        Map<String, Object> result = new HashMap<>();
        //考核计划ID
        String id = businessCheckPlan.getId();
        String checkPlanId = businessCheckPlan.getId();
        //考核名单
        List<BusinessCheckPlanUser> planUsers = businessCheckPlanUserService.findByBusinessCheckPlanId(checkPlanId);
        if (planUsers.size() == 0) {
            result.put("result", Global.FALSE);
            result.put("msg", "未设置考核名单!");
            return result;
        }
        BusinessCheckPlanUser businessCheckPlanUser = planUsers.stream().findFirst().get();
        businessCheckPlanUser.setPlanUserStatus("2");//考核中
        businessCheckPlanUser.setIsNewRecord(false);
        businessCheckPlanUserService.save(businessCheckPlanUser);
        //得到考核的部门
        String departmentId = businessCheckPlanUser.getDepartmentId();
        String[] split = departmentId.split(",");
        for (String deptId : split) {
            if(officeService.get(deptId).getTreeLevel()!=0) {
                EmployeeDto employeeDto = businessJobJdbc.findOfficeCode(deptId, "dataReporter");
                if (StringUtils.isEmpty(employeeDto)) {
                    result.put("result", Global.FALSE);
                    result.put("msg", "存在未设置数据上报员的部门！请检查!");
                    return result;
                }
            }
        }
        //得到该考核计划下的所有考核细则
        List<BusinessTarget2> businessTarget2List = businessTarget2Service.findListByPlanId(id);
        if (businessTarget2List.size() == 0) {
            result.put("result", Global.FALSE);
            result.put("msg", "该计划未设置考核细则!");
            return result;
        }
        //根据考核模板获取 考核细则
        businessTarget2List.forEach(item -> {
            try {
                setJob(businessCheckPlan, item);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        });
        result.put("result", Global.FALSE);
        result.put("msg", "操作成功!");
        return result;
    }

    /**
     * 停止任务
     *
     * @param businessCheckPlan
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void stop(BusinessCheckPlan businessCheckPlan) {
        //this.updateStatus(businessCheckPlan);
        List<BusinessJob> businessJobs = businessJobService.findByBusinessCheckPlanId(businessCheckPlan.getId());
        businessJobs.forEach(businessJob -> {
            businessJobService.delete(businessJob);
        });
    }

    /**
     * 设置job
     *
     * @param businessCheckPlan
     * @param businessTarget
     */
    @Autowired
    private DictDataJdbc dictDataJdbc;

    private void setJob(BusinessCheckPlan businessCheckPlan, BusinessTarget2 businessTarget) throws ClassNotFoundException, SchedulerException {
        //目标考核周期 周、半月、月、季度、半年、年 ，定时任务关联长度不能超过 255 个字符")
        String targetCheckCycle = businessTarget.getTargetCheckCycle();
        DictDataJdbc.DictDataDto dictDataDto = dictDataJdbc.getDictDataDto("target_check_cycle", targetCheckCycle);
        //得到定时任务表单式
        String extendS1 = dictDataDto.getExtend_s1();
        //添加定时任务
        BusinessJob businessJob = new BusinessJob();
        businessJob.setCorn(extendS1);
        businessJob.setJobName("com.jeesite.modules.job.SendMsgJob" + "-" + UUID.randomUUID());
        businessJob.setJobGroup(businessCheckPlan.getId());
        businessJob.setBusinessTarget(businessTarget);
        businessJob.setBusinessCheckPlan(businessCheckPlan);
        JobDataMap jobDataMap = new JobDataMap();

        //设置Job 需要的参数
        jobDataMap.put("businessTarget", businessTarget);
        jobDataMap.put("businessCheckPlan", businessCheckPlan);
        businessJob.setJobStatus("5");//运行
        businessJobService.save(businessJob, jobDataMap);
    }


    /**
     * 删除数据
     *
     * @param businessCheckPlan
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(BusinessCheckPlan businessCheckPlan) {
        super.delete(businessCheckPlan);

    }

    /**
     * 绩效报告
     *
     * @param checkPlanId 绩效计划ID
     * @param deptId      参评部门ID
     * @return
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> findReport(String checkPlanId, String createBy, String deptId) {
        Map<String, Object> ps = MapUtils.newHashMap();
        ps.put("checkPlanId", checkPlanId);
        ps.put("createBy", createBy);
        ps.put("deptId", deptId);
        List<Map<String, Object>> result = dao.findReport(ps);
        return result;
    }

}