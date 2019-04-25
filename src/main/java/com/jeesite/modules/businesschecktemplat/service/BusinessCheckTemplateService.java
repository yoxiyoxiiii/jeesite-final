/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;
import com.jeesite.modules.businesschecktemplat.dao.BusinessCheckTemplateDao;

/**
 * 考核模板Service
 * @author 考核模板
 * @version 2019-04-25
 */
@Service
@Transactional(readOnly=true)
public class BusinessCheckTemplateService extends CrudService<BusinessCheckTemplateDao, BusinessCheckTemplate> {
	
	/**
	 * 获取单条数据
	 * @param businessCheckTemplate
	 * @return
	 */
	@Override
	public BusinessCheckTemplate get(BusinessCheckTemplate businessCheckTemplate) {
		return super.get(businessCheckTemplate);
	}
	
	/**
	 * 查询分页数据
	 * @param businessCheckTemplate 查询条件
	 * @param businessCheckTemplate.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessCheckTemplate> findPage(BusinessCheckTemplate businessCheckTemplate) {
		return super.findPage(businessCheckTemplate);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessCheckTemplate
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessCheckTemplate businessCheckTemplate) {
		super.save(businessCheckTemplate);
	}
	
	/**
	 * 更新状态
	 * @param businessCheckTemplate
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessCheckTemplate businessCheckTemplate) {
		super.updateStatus(businessCheckTemplate);
	}
	
	/**
	 * 删除数据
	 * @param businessCheckTemplate
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessCheckTemplate businessCheckTemplate) {
		super.delete(businessCheckTemplate);
	}
	
}