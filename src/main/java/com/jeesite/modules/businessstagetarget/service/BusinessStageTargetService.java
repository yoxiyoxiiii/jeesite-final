/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessstagetarget.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businessstagetarget.entity.BusinessStageTarget;
import com.jeesite.modules.businessstagetarget.dao.BusinessStageTargetDao;

/**
 * 阶段目标Service
 * @author 阶段目标
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly=true)
public class BusinessStageTargetService extends CrudService<BusinessStageTargetDao, BusinessStageTarget> {
	
	/**
	 * 获取单条数据
	 * @param businessStageTarget
	 * @return
	 */
	@Override
	public BusinessStageTarget get(BusinessStageTarget businessStageTarget) {
		return super.get(businessStageTarget);
	}
	
	/**
	 * 查询分页数据
	 * @param businessStageTarget 查询条件
	 * @param businessStageTarget.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessStageTarget> findPage(BusinessStageTarget businessStageTarget) {
		return super.findPage(businessStageTarget);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessStageTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessStageTarget businessStageTarget) {
		super.save(businessStageTarget);
	}
	
	/**
	 * 更新状态
	 * @param businessStageTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessStageTarget businessStageTarget) {
		super.updateStatus(businessStageTarget);
	}
	
	/**
	 * 删除数据
	 * @param businessStageTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessStageTarget businessStageTarget) {
		super.delete(businessStageTarget);
	}
	
}