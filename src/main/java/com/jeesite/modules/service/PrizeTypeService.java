/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.PrizeType;
import com.jeesite.modules.dao.PrizeTypeDao;

/**
 * 奖扣类型Service
 * @author sanye
 * @version 2019-05-02
 */
@Service
@Transactional(readOnly=true)
public class PrizeTypeService extends CrudService<PrizeTypeDao, PrizeType> {
	
	/**
	 * 获取单条数据
	 * @param prizeType
	 * @return
	 */
	@Override
	public PrizeType get(PrizeType prizeType) {
		return super.get(prizeType);
	}
	
	/**
	 * 查询分页数据
	 * @param prizeType 查询条件
	 * @param prizeType.page 分页对象
	 * @return
	 */
	@Override
	public Page<PrizeType> findPage(PrizeType prizeType) {
		return super.findPage(prizeType);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param prizeType
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PrizeType prizeType) {
		super.save(prizeType);
	}
	
	/**
	 * 更新状态
	 * @param prizeType
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PrizeType prizeType) {
		super.updateStatus(prizeType);
	}
	
	/**
	 * 删除数据
	 * @param prizeType
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PrizeType prizeType) {
		super.delete(prizeType);
	}
	
}