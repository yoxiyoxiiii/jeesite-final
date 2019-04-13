/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.targets.dao.TargetDepartmentDao;
import com.jeesite.modules.targets.entity.TargetDepartment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单位/部门目标分解Service
 * @author 单位/部门目标分解
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TargetDepartmentService extends CrudService<TargetDepartmentDao, TargetDepartment> {
	
	/**
	 * 获取单条数据
	 * @param targetDepartment
	 * @return
	 */
	@Override
	public TargetDepartment get(TargetDepartment targetDepartment) {
		return super.get(targetDepartment);
	}
	
	/**
	 * 查询分页数据
	 * @param targetDepartment 查询条件
	 * @param targetDepartment.page 分页对象
	 * @return
	 */
	@Override
	public Page<TargetDepartment> findPage(TargetDepartment targetDepartment) {
		return super.findPage(targetDepartment);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param targetDepartment
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TargetDepartment targetDepartment) {
		super.save(targetDepartment);
	}
	
	/**
	 * 更新状态
	 * @param targetDepartment
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TargetDepartment targetDepartment) {
		super.updateStatus(targetDepartment);
	}
	
	/**
	 * 删除数据
	 * @param targetDepartment
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TargetDepartment targetDepartment) {
		super.delete(targetDepartment);
	}
	
}