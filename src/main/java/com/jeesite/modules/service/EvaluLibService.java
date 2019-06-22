/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.TreeService;
import com.jeesite.modules.entity.EvaluLib;
import com.jeesite.modules.dao.EvaluLibDao;

/**
 * 民主测评明细树表Service
 * @author sanye
 * @version 2019-05-17
 */
@Service
@Transactional(readOnly=true)
public class EvaluLibService extends TreeService<EvaluLibDao, EvaluLib> {
	
	/**
	 * 获取单条数据
	 * @param evaluLib
	 * @return
	 */
	@Override
	public EvaluLib get(EvaluLib evaluLib) {
		return super.get(evaluLib);
	}
	
	/**
	 * 查询列表数据
	 * @param evaluLib
	 * @return
	 */
	@Override
	public List<EvaluLib> findList(EvaluLib evaluLib) {
		return super.findList(evaluLib);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param evaluLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EvaluLib evaluLib) {
		super.save(evaluLib);
	}
	
	/**
	 * 更新状态
	 * @param evaluLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EvaluLib evaluLib) {
		super.updateStatus(evaluLib);
	}
	
	/**
	 * 删除数据
	 * @param evaluLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EvaluLib evaluLib) {
		super.delete(evaluLib);
	}
	
}