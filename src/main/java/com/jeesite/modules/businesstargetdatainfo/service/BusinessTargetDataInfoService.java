/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargetdatainfo.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businessplanusertask.entity.BusinessPlanUserTask;
import com.jeesite.modules.businessplanusertask.service.BusinessPlanUserTaskService;
import com.jeesite.modules.businesstargetdatainfo.dao.BusinessTargetDataInfoDao;
import com.jeesite.modules.businesstargetdatainfo.entity.BusinessTargetDataInfo;
import com.jeesite.modules.file.utils.FileUploadUtils;
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
	 * @param businessTargetDataInfo.page 分页对象
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
		this.save(businessTargetDataInfo);
		//更新个人任务状态
		BusinessPlanUserTask businessPlanUserTask =new BusinessPlanUserTask();
		businessPlanUserTask.setTaskStatus(2);
		businessPlanUserTask.setId(userTaskId);
		businessPlanUserTask.setIsNewRecord(false);
		planUserTaskService.save(businessPlanUserTask);
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
	
}