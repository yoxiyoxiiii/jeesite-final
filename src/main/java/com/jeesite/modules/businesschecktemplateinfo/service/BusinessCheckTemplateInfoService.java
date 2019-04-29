/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplateinfo.service;

import java.util.List;

import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstarget.service.BusinessTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesschecktemplateinfo.entity.BusinessCheckTemplateInfo;
import com.jeesite.modules.businesschecktemplateinfo.dao.BusinessCheckTemplateInfoDao;

/**
 * 考核指标Service
 * @author BusinessCheckTemplateInfo
 * @version 2019-04-25
 */
@Service
@Transactional(readOnly=true)
public class BusinessCheckTemplateInfoService extends CrudService<BusinessCheckTemplateInfoDao, BusinessCheckTemplateInfo> {
	@Autowired
	private BusinessTargetService businessTargetService;
	/**
	 * 获取单条数据
	 * @param businessCheckTemplateInfo
	 * @return
	 */
	@Override
	public BusinessCheckTemplateInfo get(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		return super.get(businessCheckTemplateInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param businessCheckTemplateInfo 查询条件
	 * @param businessCheckTemplateInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessCheckTemplateInfo> findPage(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		return super.findPage(businessCheckTemplateInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessCheckTemplateInfo
	 */
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void save(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		String businessTargetId = businessCheckTemplateInfo.getBusinessTargetId();
		//目标
		BusinessTarget businessTarget = businessTargetService.get(businessTargetId);
		businessCheckTemplateInfo.setBusinessTarget(businessTarget);
		super.save(businessCheckTemplateInfo);
	}
	
	/**
	 * 更新状态
	 * @param businessCheckTemplateInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		super.updateStatus(businessCheckTemplateInfo);
	}
	
	/**
	 * 删除数据
	 * @param businessCheckTemplateInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessCheckTemplateInfo businessCheckTemplateInfo) {
		super.delete(businessCheckTemplateInfo);
	}

	public List<String> findListByCheckTemplateId(String checkTemplateId) {
		return super.dao.findListByCheckTemplateId(checkTemplateId);

	}
}