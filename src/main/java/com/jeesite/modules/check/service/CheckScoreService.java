/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.check.dao.CheckScoreDao;
import com.jeesite.modules.check.entity.CheckScore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考核评分名单Service
 * @author 考核评分名单
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class CheckScoreService extends CrudService<CheckScoreDao, CheckScore> {
	
	/**
	 * 获取单条数据
	 * @param checkScore
	 * @return
	 */
	@Override
	public CheckScore get(CheckScore checkScore) {
		return super.get(checkScore);
	}
	
	/**
	 * 查询分页数据
	 * @param checkScore 查询条件
	 * @param checkScore.page 分页对象
	 * @return
	 */
	@Override
	public Page<CheckScore> findPage(CheckScore checkScore) {
		return super.findPage(checkScore);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param checkScore
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CheckScore checkScore) {
		super.save(checkScore);
	}
	
	/**
	 * 更新状态
	 * @param checkScore
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CheckScore checkScore) {
		super.updateStatus(checkScore);
	}
	
	/**
	 * 删除数据
	 * @param checkScore
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CheckScore checkScore) {
		super.delete(checkScore);
	}
	
}