/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.BusinessJobDao;
import com.jeesite.modules.entity.BusinessJob;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务Service
 * @author BusinessJob
 * @version 2019-04-28
 */
@Service
@Transactional(readOnly=false)
public class BusinessJobService extends CrudService<BusinessJobDao, BusinessJob> {

	@Autowired
	private QuartzService quartzService;

	/**
	 * 获取单条数据
	 * @param businessJob
	 * @return
	 */
	@Override
	public BusinessJob get(BusinessJob businessJob) {
		return super.get(businessJob);
	}
	
	/**
	 * 查询分页数据
	 * @param businessJob 查询条件
	 * @param businessJob.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessJob> findPage(BusinessJob businessJob) {
		return super.findPage(businessJob);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessJob
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(BusinessJob businessJob, JobDataMap jobDataMap) throws SchedulerException, ClassNotFoundException {
			super.save(businessJob);
		//添加任务到quartz
		quartzService.addJob(businessJob.getJobName(),businessJob.getJobGroup(),businessJob.getCorn(),jobDataMap);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param businessJob
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void restart(BusinessJob businessJob, JobDataMap jobDataMap){
		//重启quartz
		quartzService.resume(businessJob.getJobName(),businessJob.getJobGroup());
	}

	/**
	 * 更新状态
	 * @param businessJob
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessJob businessJob) {
		super.updateStatus(businessJob);
	}
	
	/**
	 * 删除数据
	 * @param businessJob
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delete(BusinessJob businessJob) {
		super.delete(businessJob);
		quartzService.delete(businessJob.getJobName(),businessJob.getJobGroup());
	}

	public List<BusinessJob> findByBusinessCheckPlanId(String businessCheckPlanId) {
		List<BusinessJob> businessJobs = dao.findByBusinessCheckPlanId(businessCheckPlanId);
		return businessJobs;
	}

	public void updateJobStatus(String checkPlanId, String jobStatus) {
		super.dao.updateJobStatus(checkPlanId,jobStatus);
	}

	public BusinessJob findByTargetId(String businessTargetId) {
		return super.dao.findByTargetId(businessTargetId);
	}

	public void pause(BusinessJob businessJob) {
		quartzService.pause(businessJob.getJobName(), businessJob.getJobGroup());
	}
}