/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesscheckplan.dao.BusinessCheckPlanDao;
import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesschecktemplateinfo.service.BusinessCheckTemplateInfoService;
import com.jeesite.modules.businessjob.entity.BusinessJob;
import com.jeesite.modules.businessjob.service.BusinessJobService;
import com.jeesite.modules.businesstarget.service.BusinessTargetService;
import com.jeesite.modules.businesstarget2.entity.BusinessTarget2;
import com.jeesite.modules.businesstarget2.service.BusinessTarget2Service;
import com.jeesite.modules.businesstargettypetree.entity.BusinessTargetTypeTree;
import com.jeesite.modules.sys.service.UserService;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
	 * 更新状态,关联定时任务
	 * @param businessCheckPlan
	 */
	@Transactional(readOnly=false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void start(BusinessCheckPlan businessCheckPlan) throws SchedulerException, ClassNotFoundException {
		this.updateStatus(businessCheckPlan);
		addJob(businessCheckPlan);//一个考核计划一个job
	}


	@Autowired
	private BusinessTarget2Service businessTarget2Service;
	private void addJob(BusinessCheckPlan businessCheckPlan) {
		//考核模板
		BusinessTargetTypeTree businessTargetTypeTree = businessCheckPlan.getBusinessTargetTypeTree();
		String targetTypeCode = businessTargetTypeTree.getTargetTypeCode();
		//根据考核模板获取 考核细则
		List<BusinessTarget2> businessTarget2List = businessTarget2Service.findByTypeCode(targetTypeCode);
		businessTarget2List.forEach(item->{
			try {
				setJob(businessCheckPlan, item);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 停止任务
	 * @param businessCheckPlan
	 */
	@Transactional(readOnly=false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void stop(BusinessCheckPlan businessCheckPlan)  {
		this.updateStatus(businessCheckPlan);
		List<BusinessJob> businessJobs = businessJobService.findByBusinessCheckPlanId(businessCheckPlan.getId());
		businessJobs.forEach(businessJob -> {
			businessJobService.delete(businessJob);
		});
	}

	/**
	 * 设置job
	 * @param businessCheckPlan
	 * @param businessTarget
	 */
	private void setJob(BusinessCheckPlan businessCheckPlan, BusinessTarget2 businessTarget) throws ClassNotFoundException, SchedulerException {
		//目标考核周期 周、半月、月、季度、半年、年 ，定时任务关联长度不能超过 255 个字符")
		String targetCheckCycle = businessTarget.getTargetCheckCycle();
		//添加定时任务
		BusinessJob businessJob = new BusinessJob();
		businessJob.setCorn(targetCheckCycle);
		businessJob.setJobName("com.jeesite.modules.businessjob.Job.SendMsgJob"+"-"+ UUID.randomUUID());
		businessJob.setJobGroup(businessCheckPlan.getId());
		businessJob.setBusinessTarget(businessTarget);
		businessJob.setBusinessCheckPlan(businessCheckPlan);
		JobDataMap jobDataMap = new JobDataMap();

		//设置Job 需要的参数
		jobDataMap.put("businessTarget", businessTarget);
		jobDataMap.put("businessCheckPlan",businessCheckPlan);
        businessJob.setJobStatus("5");//运行
		businessJobService.save(businessJob,jobDataMap);
	}


	/**
	 * 删除数据
	 * @param businessCheckPlan
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(BusinessCheckPlan businessCheckPlan) {
		super.delete(businessCheckPlan);

	}
	
}