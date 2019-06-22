package com.jeesite.modules.service; /**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */

import com.jeesite.common.service.TreeService;
import com.jeesite.modules.dao.BusinessTargetTypeDao;
import com.jeesite.modules.entity.BusinessTargetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 目标分类Service
 * @author sanye
 * @version 2019-06-20
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetTypeService extends TreeService<BusinessTargetTypeDao, BusinessTargetType> {
	
	/**
	 * 获取单条数据
	 * @param businessTargetType
	 * @return
	 */
	@Override
	public BusinessTargetType get(BusinessTargetType businessTargetType) {
		return super.get(businessTargetType);
	}
	
	/**
	 * 查询列表数据
	 * @param businessTargetType
	 * @return
	 */
	@Override
	public List<BusinessTargetType> findList(BusinessTargetType businessTargetType) {
		return super.findList(businessTargetType);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetType
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTargetType businessTargetType) {
		super.save(businessTargetType);
	}
	
	/**
	 * 更新状态
	 * @param businessTargetType
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTargetType businessTargetType) {
		super.updateStatus(businessTargetType);
	}
	
	/**
	 * 删除数据
	 * @param businessTargetType
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTargetType businessTargetType) {
		super.delete(businessTargetType);
	}
	
}