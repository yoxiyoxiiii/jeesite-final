/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.BusinessTargetDataInfoDao;
import com.jeesite.modules.entity.*;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 上报的数据Service
 * @author 上报的数据
 * @version 2019-06-07
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetDataInfoService extends CrudService<BusinessTargetDataInfoDao, BusinessTargetDataInfo> {

	@Autowired
	private BusinessPlanUserTaskService planUserTaskService;
	@Autowired
	private BusinessCheckPlanUserService checkPlanUserService;
	@Autowired
	private BusinessCheckPlanService businessCheckPlanService;
	@Autowired
	private BusinessTargetTaskMonitorService businessTargetTaskMonitorService;
	@Autowired
	private BusinessPlanUserTaskService businessPlanUserTaskService;
	/**
	 * 获取单条数据
	 * @param businessTargetDataInfo
	 * @return
	 */
	@Override
	public BusinessTargetDataInfo get(BusinessTargetDataInfo businessTargetDataInfo) {
		return super.get(businessTargetDataInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param businessTargetDataInfo 查询条件
	 * @return
	 */
	@Override
	public Page<BusinessTargetDataInfo> findPage(BusinessTargetDataInfo businessTargetDataInfo) {
		return super.findPage(businessTargetDataInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetDataInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTargetDataInfo businessTargetDataInfo) {
		super.save(businessTargetDataInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(businessTargetDataInfo.getId(), "businessTargetDataInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(businessTargetDataInfo.getId(), "businessTargetDataInfo_file");
		//更新个人任务状态

	}
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetDataInfo
	 */
	@Transactional(readOnly=false)
	public void save(BusinessTargetDataInfo businessTargetDataInfo,String userTaskId) {
		businessTargetDataInfo.setDataStatus("1");//待审
		//更新个人任务状态
		BusinessPlanUserTask businessPlanUserTask =planUserTaskService.get(userTaskId);
		businessPlanUserTask.setTaskStatus(3);
		businessPlanUserTask.setId(userTaskId);
		businessPlanUserTask.setIsNewRecord(false);
		planUserTaskService.save(businessPlanUserTask);
		Office office = businessPlanUserTask.getOffice();
		BusinessCheckPlanUser businessCheckPlanUser = new BusinessCheckPlanUser();
		businessCheckPlanUser.setIsNewRecord(false);
		businessCheckPlanUser.setPlanStatus("2");//设置考核名单中，上报数据后部门的数据上报状态
		businessCheckPlanUser.setOffice(office);
		String businessCheckPlanId = businessPlanUserTask.getBusinessCheckPlanId();
		BusinessCheckPlan businessCheckPlan = new BusinessCheckPlan();
		businessCheckPlan.setId(businessCheckPlanId);
		businessCheckPlanUser.setBusinessCheckPlan(businessCheckPlan);
		businessCheckPlanUser.setId(businessCheckPlanId);
		checkPlanUserService.save(businessCheckPlanUser);//更新部门任务上报状态
		//关联期数
		businessTargetDataInfo.setBusinessStageTarget2(businessPlanUserTask.getBusinessStageTarget2());
		//BusinessTargetTaskMonitor taskMonitor = businessTargetTaskMonitorService.findByIds(businessPlanUserTask.getBusinessTarget().getId(), office.getOfficeCode(), businessCheckPlanId);
		this.save(businessTargetDataInfo);
		BusinessTargetTaskMonitor taskMonitor = businessTargetTaskMonitorService.get(businessPlanUserTask.getMonitorId());
		Integer dataItemCount = taskMonitor.getDataItemCount();
		taskMonitor.addUpItemCount();//+1
		Integer upItemCount = taskMonitor.getUpItemCount();
		if (dataItemCount<= upItemCount) {
			taskMonitor.setStatus("3");//设置考核细则数据填报完整。
			upItemCount = dataItemCount;
		}
		 String status = taskMonitor.getStatus();
		//businessTargetTaskMonitorService.save(taskMonitor);//更新
		//设置数据上报的数量 + 1
		businessTargetTaskMonitorService.updateByIds(
				businessPlanUserTask.getBusinessTarget().getId(),
				office.getOfficeCode(),
				businessCheckPlanId,
				upItemCount,
				status
		);


	}

	/**
	 * 更新状态
	 * @param businessTargetDataInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTargetDataInfo businessTargetDataInfo) {
		super.updateStatus(businessTargetDataInfo);
	}
	
	/**
	 * 删除数据
	 * @param businessTargetDataInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTargetDataInfo businessTargetDataInfo) {
		super.delete(businessTargetDataInfo);
	}

	@Transactional(readOnly=false)
	public void saveReport(BusinessTargetDataInfo businessTargetDataInfo) {
		businessTargetDataInfo.setDataStatus("1");//待审核
		businessTargetDataInfo.setIsNewRecord(false);
		String userCode = businessTargetDataInfo.getUser().getUserCode();
		String targetId = businessTargetDataInfo.getBusinessTarget().getId();
		String dataItemId = businessTargetDataInfo.getBusinessTargetDataItem().getId();
		businessPlanUserTaskService.updateStatus(targetId,dataItemId, userCode, "5");//重报
		businessTargetTaskMonitorService.updateBy(userCode, targetId,businessTargetDataInfo.getBusinessStageTarget2().getId(),"5");//将监控状态修改未 已重报
		this.save(businessTargetDataInfo);
	}
}