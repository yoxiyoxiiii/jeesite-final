/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplanuser.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesscheckplanuser.entity.BusinessCheckPlanUser;
import com.jeesite.modules.businesscheckplanuser.dao.BusinessCheckPlanUserDao;

/**
 * 考核名单Service
 * @author 考核名单
 * @version 2019-04-28
 */
@Service
@Transactional(readOnly=true)
public class BusinessCheckPlanUserService extends CrudService<BusinessCheckPlanUserDao, BusinessCheckPlanUser> {
	
	/**
	 * 获取单条数据
	 * @param businessCheckPlanUser
	 * @return
	 */
	@Override
	public BusinessCheckPlanUser get(BusinessCheckPlanUser businessCheckPlanUser) {
		return super.get(businessCheckPlanUser);
	}
	
	/**
	 * 查询分页数据
	 * @param businessCheckPlanUser 查询条件
	 * @param businessCheckPlanUser.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessCheckPlanUser> findPage(BusinessCheckPlanUser businessCheckPlanUser) {
		return super.findPage(businessCheckPlanUser);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessCheckPlanUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessCheckPlanUser businessCheckPlanUser) {
		super.save(businessCheckPlanUser);
	}
	
	/**
	 * 更新状态
	 * @param businessCheckPlanUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessCheckPlanUser businessCheckPlanUser) {
		super.updateStatus(businessCheckPlanUser);
	}
	
	/**
	 * 删除数据
	 * @param businessCheckPlanUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessCheckPlanUser businessCheckPlanUser) {
		super.delete(businessCheckPlanUser);
	}

	public List<BusinessCheckPlanUser> findByBusinessCheckPlanId(String businessCheckPlanId) {

		return dao.findByBusinessCheckPlanId(businessCheckPlanId);
	}
}