/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import com.jeesite.modules.businesstargettype.dao.BusinessTargetTypeDao;

/**
 * 指标分类Service
 * @author 指标分类
 * @version 2019-04-21
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetTypeService extends CrudService<BusinessTargetTypeDao, BusinessTargetType> {


	@Autowired
	private BusinessTargetTypeDao businessTargetTypeDao;

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
	 * 查询分页数据
	 * @param businessTargetType 查询条件
	 * @param businessTargetType.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessTargetType> findPage(BusinessTargetType businessTargetType) {
		return super.findPage(businessTargetType);
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

	public List<BusinessTargetType> findList() {
		return businessTargetTypeDao.findList();
	}
}