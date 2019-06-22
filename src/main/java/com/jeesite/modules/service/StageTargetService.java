/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.StageTargetDao;
import com.jeesite.modules.entity.StageTarget;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 阶段目标Service
 * @author StageTarget
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly=true)
public class StageTargetService extends CrudService<StageTargetDao, StageTarget> {
	
	/**
	 * 获取单条数据
	 * @param stageTarget
	 * @return
	 */
	@Override
	public StageTarget get(StageTarget stageTarget) {
		return super.get(stageTarget);
	}
	
	/**
	 * 查询分页数据
	 * @param stageTarget 查询条件
	 * @param stageTarget.page 分页对象
	 * @return
	 */
	@Override
	public Page<StageTarget> findPage(StageTarget stageTarget) {
		return super.findPage(stageTarget);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param stageTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(StageTarget stageTarget) {
		super.save(stageTarget);
	}
	
	/**
	 * 更新状态
	 * @param stageTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(StageTarget stageTarget) {
		super.updateStatus(stageTarget);
	}
	
	/**
	 * 删除数据
	 * @param stageTarget
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(StageTarget stageTarget) {
		super.delete(stageTarget);
	}

	public List<StageTarget> findList() {

		return super.dao.findList();
	}
}