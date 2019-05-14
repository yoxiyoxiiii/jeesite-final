/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettypetree.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.businesstargettypetree.entity.BusinessTargetTypeTree;
import com.jeesite.modules.businesstargettypetree.dao.BusinessTargetTypeTreeDao;

/**
 * 目标分类Service
 * @author BusinessTargetTypeTree
 * @version 2019-05-05
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetTypeTreeService extends TreeService<BusinessTargetTypeTreeDao, BusinessTargetTypeTree> {
	
	/**
	 * 获取单条数据
	 * @param businessTargetTypeTree
	 * @return
	 */
	@Override
	public BusinessTargetTypeTree get(BusinessTargetTypeTree businessTargetTypeTree) {
		return super.get(businessTargetTypeTree);
	}
	
	/**
	 * 查询列表数据
	 * @param businessTargetTypeTree
	 * @return
	 */
	@Override
	public List<BusinessTargetTypeTree> findList(BusinessTargetTypeTree businessTargetTypeTree) {
		return super.findList(businessTargetTypeTree);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetTypeTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTargetTypeTree businessTargetTypeTree) {
		super.save(businessTargetTypeTree);
	}
	
	/**
	 * 更新状态
	 * @param businessTargetTypeTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTargetTypeTree businessTargetTypeTree) {
		super.updateStatus(businessTargetTypeTree);
	}
	
	/**
	 * 删除数据
	 * @param businessTargetTypeTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTargetTypeTree businessTargetTypeTree) {
		super.delete(businessTargetTypeTree);
	}
	
}