/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.targets.dao.TargetStageDao;
import com.jeesite.modules.targets.entity.TargetStage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 阶段目标Service
 * @author 阶段目标
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TargetStageService extends CrudService<TargetStageDao, TargetStage> {
	
	/**
	 * 获取单条数据
	 * @param targetStage
	 * @return
	 */
	@Override
	public TargetStage get(TargetStage targetStage) {
		return super.get(targetStage);
	}
	
	/**
	 * 查询分页数据
	 * @param targetStage 查询条件
	 * @param targetStage.page 分页对象
	 * @return
	 */
	@Override
	public Page<TargetStage> findPage(TargetStage targetStage) {
		return super.findPage(targetStage);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param targetStage
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TargetStage targetStage) {
		super.save(targetStage);
	}
	
	/**
	 * 更新状态
	 * @param targetStage
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TargetStage targetStage) {
		super.updateStatus(targetStage);
	}
	
	/**
	 * 删除数据
	 * @param targetStage
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TargetStage targetStage) {
		super.delete(targetStage);
	}
	
}