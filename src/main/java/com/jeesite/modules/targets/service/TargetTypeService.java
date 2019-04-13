/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.targets.dao.TargetTypeDao;
import com.jeesite.modules.targets.entity.TargetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 指标分类Service
 * @author 指标分类
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TargetTypeService extends CrudService<TargetTypeDao, TargetType> {
	
	/**
	 * 获取单条数据
	 * @param targetType
	 * @return
	 */
	@Override
	public TargetType get(TargetType targetType) {
		return super.get(targetType);
	}
	
	/**
	 * 查询分页数据
	 * @param targetType 查询条件
	 * @param targetType.page 分页对象
	 * @return
	 */
	@Override
	public Page<TargetType> findPage(TargetType targetType) {
		return super.findPage(targetType);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param targetType
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TargetType targetType) {
		super.save(targetType);
	}
	
	/**
	 * 更新状态
	 * @param targetType
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TargetType targetType) {
		super.updateStatus(targetType);
	}
	
	/**
	 * 删除数据
	 * @param targetType
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TargetType targetType) {
		super.delete(targetType);
	}
	
}