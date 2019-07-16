/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.BusinessPlanUserTaskDao;
import com.jeesite.modules.entity.BusinessPlanUserTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 目标生成的任务Service
 * @author yj
 * @version 2019-05-02
 */
@Service
@Transactional(readOnly=true)
public class BusinessPlanUserTaskService extends CrudService<BusinessPlanUserTaskDao, BusinessPlanUserTask> {
	
	/**
	 * 获取单条数据
	 * @param businessPlanUserTask
	 * @return
	 */
	@Override
	public BusinessPlanUserTask get(BusinessPlanUserTask businessPlanUserTask) {
		return super.get(businessPlanUserTask);
	}
	
	/**
	 * 查询分页数据
	 * @param businessPlanUserTask 查询条件
	 * @return
	 */
	@Override
	public Page<BusinessPlanUserTask> findPage(BusinessPlanUserTask businessPlanUserTask) {
		return super.findPage(businessPlanUserTask);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessPlanUserTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessPlanUserTask businessPlanUserTask) {
		super.save(businessPlanUserTask);
	}
	
	/**
	 * 更新状态
	 * @param businessPlanUserTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessPlanUserTask businessPlanUserTask) {
		super.updateStatus(businessPlanUserTask);
	}
	
	/**
	 * 删除数据
	 * @param businessPlanUserTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessPlanUserTask businessPlanUserTask) {
		super.delete(businessPlanUserTask);
	}

	@Transactional(readOnly=false)
	public void updateStatus(String targetId, String dataItemId,String userCode, String status) {
		super.dao.updateStatusBy(targetId, dataItemId, userCode, status);
	}

	@Transactional(readOnly=false)
	public void updateStatusByItems(String targetId, String stageId,String userCode, String status) {
		super.dao.updateStatusByItems(targetId, stageId, userCode, status);
	}

	public List<BusinessPlanUserTask> findPageDisCount(int pNo, int pSize) {
		return super.dao.findPageDisCount(pNo-1, pSize);
	}
}