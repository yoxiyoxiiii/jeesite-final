/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.factory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.factory.entity.FactoryC;
import com.jeesite.modules.basic.factory.dao.FactoryCDao;

/**
 * 厂家管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class FactoryCService extends CrudService<FactoryCDao, FactoryC> {
	
	/**
	 * 获取单条数据
	 * @param factoryC
	 * @return
	 */
	@Override
	public FactoryC get(FactoryC factoryC) {
		return super.get(factoryC);
	}
	
	/**
	 * 查询分页数据
	 * @param factoryC 查询条件
	 * @param factoryC.page 分页对象
	 * @return
	 */
	@Override
	public Page<FactoryC> findPage(FactoryC factoryC) {
		return super.findPage(factoryC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param factoryC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(FactoryC factoryC) {
		super.save(factoryC);
	}
	
	/**
	 * 更新状态
	 * @param factoryC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(FactoryC factoryC) {
		super.updateStatus(factoryC);
	}
	
	/**
	 * 删除数据
	 * @param factoryC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(FactoryC factoryC) {
		super.delete(factoryC);
	}
	
}