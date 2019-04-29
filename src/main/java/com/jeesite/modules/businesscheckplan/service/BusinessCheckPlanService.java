/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.service;

import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;
import com.jeesite.modules.businesschecktemplateinfo.entity.BusinessCheckTemplateInfo;
import com.jeesite.modules.businesschecktemplateinfo.service.BusinessCheckTemplateInfoService;
import com.jeesite.modules.businessjob.entity.BusinessJob;
import com.jeesite.modules.businessjob.service.BusinessJobService;
import com.jeesite.modules.businessjob.service.QuartzService;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstarget.service.BusinessTargetService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.UserService;
import net.bytebuddy.asm.Advice;
import org.hibernate.validator.constraints.Length;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesscheckplan.dao.BusinessCheckPlanDao;

/**
 * 考核计划Service
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@Service
@Transactional(readOnly=true)
public class BusinessCheckPlanService extends CrudService<BusinessCheckPlanDao, BusinessCheckPlan> {

	@Autowired
	private UserService userService;

	@Autowired
	private BusinessJobService businessJobService;

	@Autowired
	private BusinessTargetService businessTargetService;

	@Autowired
	private BusinessCheckTemplateInfoService businessCheckTemplateInfoService;


	/**
	 * 获取单条数据
	 * @param businessCheckPlan
	 * @return
	 */
	@Override
	public BusinessCheckPlan get(BusinessCheckPlan businessCheckPlan) {
		return super.get(businessCheckPlan);
	}
	
	/**
	 * 查询分页数据
	 * @param businessCheckPlan 查询条件
	 * @param businessCheckPlan.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessCheckPlan> findPage(BusinessCheckPlan businessCheckPlan) {
		return super.findPage(businessCheckPlan);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessCheckPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessCheckPlan businessCheckPlan) {
		super.save(businessCheckPlan);
	}
	
	/**
	 * 更新状态
	 * @param businessCheckPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessCheckPlan businessCheckPlan) {
//		super.updateStatus(businessCheckPlan);
		super.dao.update(businessCheckPlan);
	}

	/**
	 * 更新状态
	 * @param businessCheckPlan
	 */
	@Transactional(readOnly=false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void start(BusinessCheckPlan businessCheckPlan) throws SchedulerException, ClassNotFoundException {
		this.updateStatus(businessCheckPlan);
		//考核模板
		BusinessCheckTemplate businessCheckTemplate = businessCheckPlan.getBusinessCheckTemplate();
		String checkTemplateId = businessCheckTemplate.getId();
		List<String> businessTargetIdList = businessCheckTemplateInfoService.findListByCheckTemplateId(checkTemplateId);
		List<BusinessTarget> businessTargetList = businessTargetService.findListIn(businessTargetIdList);
		//每个目标都生成一个job
		for (BusinessTarget item: businessTargetList){
			setJob(businessCheckPlan, item);
		}
	}

	/**
	 * 设置job
	 * @param businessCheckPlan
	 * @param businessTarget
	 */
	private void setJob(BusinessCheckPlan businessCheckPlan, BusinessTarget businessTarget) throws ClassNotFoundException, SchedulerException {
		//目标考核周期 周、半月、月、季度、半年、年 ，定时任务关联长度不能超过 255 个字符")
		String targetCheckCycle = businessTarget.getTargetCheckCycle();
		//添加定时任务
		BusinessJob businessJob = new BusinessJob();
		businessJob.setCorn(targetCheckCycle);
		businessJob.setJobName("com.jeesite.modules.businessjob.Job.SendMsgJob"+"-"+ UUID.randomUUID());
		businessJob.setJobGroup(businessCheckPlan.getId());
		JobDataMap jobDataMap = new JobDataMap();

		//设置Job 需要的参数
		jobDataMap.put("businessTarget", businessTarget);
		jobDataMap.put("businessCheckPlan",businessCheckPlan);
		businessJobService.save(businessJob,jobDataMap);
	}


	/**
	 * 删除数据
	 * @param businessCheckPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessCheckPlan businessCheckPlan) {
		super.delete(businessCheckPlan);
	}
	
}