/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.service;

import java.util.List;

import org.springframework.stereotype.Service;
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
		super.updateStatus(businessCheckPlan);
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