/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.BusinessPlanScore;
import com.jeesite.modules.dao.BusinessPlanScoreDao;

/**
 * 考核评分Service
 * @author BusinessPlanScore
 * @version 2019-07-02
 */
@Service
@Transactional(readOnly=true)
public class BusinessPlanScoreService extends CrudService<BusinessPlanScoreDao, BusinessPlanScore> {
	
	/**
	 * 获取单条数据
	 * @param businessPlanScore
	 * @return
	 */
	@Override
	public BusinessPlanScore get(BusinessPlanScore businessPlanScore) {
		return super.get(businessPlanScore);
	}
	
	/**
	 * 查询分页数据
	 * @param businessPlanScore 查询条件
	 * @param businessPlanScore.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessPlanScore> findPage(BusinessPlanScore businessPlanScore) {
		return super.findPage(businessPlanScore);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessPlanScore
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessPlanScore businessPlanScore) {
		super.save(businessPlanScore);
	}
	
	/**
	 * 更新状态
	 * @param businessPlanScore
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessPlanScore businessPlanScore) {
		super.updateStatus(businessPlanScore);
	}
	
	/**
	 * 删除数据
	 * @param businessPlanScore
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessPlanScore businessPlanScore) {
		super.delete(businessPlanScore);
	}
	
}