/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstarget.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstarget.dao.BusinessTargetDao;

/**
 * 指标Service
 * @author BusinessTarget
 * @version 2019-04-20
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetService extends CrudService<BusinessTargetDao, BusinessTarget> {
	
	/**
	 * 获取单条数据
	 * @param businessTarget
	 * @return
	 */
	@Override
	public BusinessTarget get(BusinessTarget businessTarget) {
		return super.get(businessTarget);
	}
	
	/**
	 * 查询分页数据
	 * @param businessTarget 查询条件
	 * @param businessTarget.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessTarget> findPage(BusinessTarget businessTarget) {
		return super.findPage(businessTarget);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTarget businessTarget) {
		super.save(businessTarget);
	}
	
	/**
	 * 更新状态
	 * @param businessTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTarget businessTarget) {
		super.updateStatus(businessTarget);
	}
	
	/**
	 * 删除数据
	 * @param businessTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTarget businessTarget) {
		super.delete(businessTarget);
	}

	public List<BusinessTarget> findList() {
		return super.dao.findList();
	}
}