/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.biz.entity.PrizeLib;
import com.jeesite.modules.biz.dao.PrizeLibDao;

/**
 * 奖扣分类Service
 * @author sanye
 * @version 2019-05-02
 */
@Service
@Transactional(readOnly=true)
public class PrizeLibService extends CrudService<PrizeLibDao, PrizeLib> {
	
	/**
	 * 获取单条数据
	 * @param prizeLib
	 * @return
	 */
	@Override
	public PrizeLib get(PrizeLib prizeLib) {
		return super.get(prizeLib);
	}
	
	/**
	 * 查询分页数据
	 * @param prizeLib 查询条件
	 * @param prizeLib.page 分页对象
	 * @return
	 */
	@Override
	public Page<PrizeLib> findPage(PrizeLib prizeLib) {
		return super.findPage(prizeLib);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param prizeLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PrizeLib prizeLib) {
		super.save(prizeLib);
	}
	
	/**
	 * 更新状态
	 * @param prizeLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PrizeLib prizeLib) {
		super.updateStatus(prizeLib);
	}
	
	/**
	 * 删除数据
	 * @param prizeLib
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PrizeLib prizeLib) {
		super.delete(prizeLib);
	}
	
}