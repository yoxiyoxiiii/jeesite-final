/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.BusinessCheckPlanUserDao;
import com.jeesite.modules.entity.BusinessCheckPlanUser;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.service.OfficeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考核名单Service
 * @author 考核名单
 * @version 2019-04-28
 */
@Service
@Transactional(readOnly=true)
public class BusinessCheckPlanUserService extends CrudService<BusinessCheckPlanUserDao, BusinessCheckPlanUser> {

	@Autowired
	private OfficeService officeService;

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

	@Transactional(readOnly=false)
	public void saveAll(BusinessCheckPlanUser businessCheckPlanUser, String[] departmentIds) {
		for (int i = 0 ;i<departmentIds.length; i++) {
			BusinessCheckPlanUser checkPlanUser = new BusinessCheckPlanUser();
			BeanUtils.copyProperties(businessCheckPlanUser, checkPlanUser);
			String dpit = departmentIds[i];
			Office office = new Office();
			office.setOfficeCode(dpit);
			Integer treeLevel = officeService.get(office).getTreeLevel();
			if (treeLevel!=0) {
				checkPlanUser.setOffice(office);
				super.save(checkPlanUser);
			}

   		}
	}
}