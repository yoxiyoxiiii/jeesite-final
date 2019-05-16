/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.evalu.entity.EvaluData;
import com.jeesite.modules.evalu.dao.EvaluDataDao;

/**
 * 民主测评记录Service
 * @author sanye
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly=true)
public class EvaluDataService extends CrudService<EvaluDataDao, EvaluData> {
	
	/**
	 * 获取单条数据
	 * @param evaluData
	 * @return
	 */
	@Override
	public EvaluData get(EvaluData evaluData) {
		return super.get(evaluData);
	}
	
	/**
	 * 查询分页数据
	 * @param evaluData 查询条件
	 * @param evaluData.page 分页对象
	 * @return
	 */
	@Override
	public Page<EvaluData> findPage(EvaluData evaluData) {
		return super.findPage(evaluData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param evaluData
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EvaluData evaluData) {
		super.save(evaluData);
	}
	
	/**
	 * 更新状态
	 * @param evaluData
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EvaluData evaluData) {
		super.updateStatus(evaluData);
	}
	
	/**
	 * 删除数据
	 * @param evaluData
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EvaluData evaluData) {
		super.delete(evaluData);
	}
	
}