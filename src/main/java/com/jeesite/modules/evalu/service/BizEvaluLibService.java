/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.evalu.entity.BizEvaluLib;
import com.jeesite.modules.evalu.dao.BizEvaluLibDao;

/**
 * 民主测评明细树表Service
 * @author sanye
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly=true)
public class BizEvaluLibService extends TreeService<BizEvaluLibDao, BizEvaluLib> {
	
	/**
	 * 获取单条数据
	 * @param bizEvaluLib
	 * @return
	 */
	@Override
	public BizEvaluLib get(BizEvaluLib bizEvaluLib) {
		return super.get(bizEvaluLib);
	}
	
	/**
	 * 查询列表数据
	 * @param bizEvaluLib
	 * @return
	 */
	@Override
	public List<BizEvaluLib> findList(BizEvaluLib bizEvaluLib) {
		return super.findList(bizEvaluLib);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param bizEvaluLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BizEvaluLib bizEvaluLib) {
		super.save(bizEvaluLib);
	}
	
	/**
	 * 更新状态
	 * @param bizEvaluLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BizEvaluLib bizEvaluLib) {
		super.updateStatus(bizEvaluLib);
	}
	
	/**
	 * 删除数据
	 * @param bizEvaluLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BizEvaluLib bizEvaluLib) {
		super.delete(bizEvaluLib);
	}
	
}