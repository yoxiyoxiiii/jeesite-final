/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettaskmonitor.service;

import com.jeesite.common.service.TreeService;
import com.jeesite.modules.businesstargettaskmonitor.dao.BusinessTargetTaskMonitorDao;
import com.jeesite.modules.businesstargettaskmonitor.entity.BusinessTargetTaskMonitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 任务监控Service
 * @author 任务监控/任务列表
 * @version 2019-06-09
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetTaskMonitorService extends TreeService<BusinessTargetTaskMonitorDao, BusinessTargetTaskMonitor> {
	
	/**
	 * 获取单条数据
	 * @param businessTargetTaskMonitor
	 * @return
	 */
	@Override
	public BusinessTargetTaskMonitor get(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		return super.get(businessTargetTaskMonitor);
	}
	
	/**
	 * 查询列表数据
	 * @param businessTargetTaskMonitor
	 * @return
	 */
	@Override
	public List<BusinessTargetTaskMonitor> findList(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		return super.findList(businessTargetTaskMonitor);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetTaskMonitor
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		super.save(businessTargetTaskMonitor);
	}
	
	/**
	 * 更新状态
	 * @param businessTargetTaskMonitor
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		super.updateStatus(businessTargetTaskMonitor);
	}
	
	/**
	 * 删除数据
	 * @param businessTargetTaskMonitor
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTargetTaskMonitor businessTargetTaskMonitor) {
		super.delete(businessTargetTaskMonitor);
	}

	public List<BusinessTargetTaskMonitor> findAll() {

		return super.dao.findAll();
	}

	public BusinessTargetTaskMonitor findByIds(String targetId, String officeCode, String businessCheckPlanId) {

		return super.dao.findByIds(targetId, officeCode,  businessCheckPlanId);
	}

	public void updateByIds(String targetId,
							String officeCode,
							String businessCheckPlanId,
							Integer upItemCount,
							String status
	) {
		super.dao.updateByIds(targetId, officeCode,  businessCheckPlanId,upItemCount, status);
	}
}