/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.biz.entity.Supervision;
import com.jeesite.modules.biz.dao.SupervisionDao;

/**
 * 督查督办Service
 * @author sanye
 * @version 2019-06-04
 */
@Service
@Transactional(readOnly=true)
public class SupervisionService extends CrudService<SupervisionDao, Supervision> {
	
	/**
	 * 获取单条数据
	 * @param supervision
	 * @return
	 */
	@Override
	public Supervision get(Supervision supervision) {
		return super.get(supervision);
	}
	
	/**
	 * 查询分页数据
	 * @param supervision 查询条件
	 * @param supervision.page 分页对象
	 * @return
	 */
	@Override
	public Page<Supervision> findPage(Supervision supervision) {
		return super.findPage(supervision);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param supervision
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Supervision supervision) {
		super.save(supervision);
	}
	
	/**
	 * 更新状态
	 * @param supervision
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Supervision supervision) {
		super.updateStatus(supervision);
	}
	
	/**
	 * 删除数据
	 * @param supervision
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Supervision supervision) {
		super.delete(supervision);
	}
	
}