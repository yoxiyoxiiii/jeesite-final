/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.targets.dao.TargetsDao;
import com.jeesite.modules.targets.entity.Targets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * targetsService
 * @author target
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TargetsService extends CrudService<TargetsDao, Targets> {
	
	/**
	 * 获取单条数据
	 * @param targets
	 * @return
	 */
	@Override
	public Targets get(Targets targets) {
		return super.get(targets);
	}
	
	/**
	 * 查询分页数据
	 * @param targets 查询条件
	 * @param targets.page 分页对象
	 * @return
	 */
	@Override
	public Page<Targets> findPage(Targets targets) {
		return super.findPage(targets);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param targets
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Targets targets) {
		super.save(targets);
	}
	
	/**
	 * 更新状态
	 * @param targets
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Targets targets) {
		super.updateStatus(targets);
	}
	
	/**
	 * 删除数据
	 * @param targets
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Targets targets) {
		super.delete(targets);
	}
	
}