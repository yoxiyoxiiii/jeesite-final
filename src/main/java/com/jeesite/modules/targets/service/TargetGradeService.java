/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.targets.dao.TargetGradeDao;
import com.jeesite.modules.targets.entity.TargetGrade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 目标分档Service
 * @author 目标分档
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TargetGradeService extends CrudService<TargetGradeDao, TargetGrade> {
	
	/**
	 * 获取单条数据
	 * @param targetGrade
	 * @return
	 */
	@Override
	public TargetGrade get(TargetGrade targetGrade) {
		return super.get(targetGrade);
	}
	
	/**
	 * 查询分页数据
	 * @param targetGrade 查询条件
	 * @param targetGrade.page 分页对象
	 * @return
	 */
	@Override
	public Page<TargetGrade> findPage(TargetGrade targetGrade) {
		return super.findPage(targetGrade);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param targetGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TargetGrade targetGrade) {
		super.save(targetGrade);
	}
	
	/**
	 * 更新状态
	 * @param targetGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TargetGrade targetGrade) {
		super.updateStatus(targetGrade);
	}
	
	/**
	 * 删除数据
	 * @param targetGrade
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TargetGrade targetGrade) {
		super.delete(targetGrade);
	}
	
}