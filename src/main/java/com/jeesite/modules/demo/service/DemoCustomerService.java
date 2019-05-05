/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.demo.entity.DemoCustomer;
import com.jeesite.modules.demo.dao.DemoCustomerDao;

/**
 * demo_customerService
 * @author sanye
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly=true)
public class DemoCustomerService extends CrudService<DemoCustomerDao, DemoCustomer> {
	
	/**
	 * 获取单条数据
	 * @param demoCustomer
	 * @return
	 */
	@Override
	public DemoCustomer get(DemoCustomer demoCustomer) {
		return super.get(demoCustomer);
	}
	
	/**
	 * 查询分页数据
	 * @param demoCustomer 查询条件
	 * @param demoCustomer.page 分页对象
	 * @return
	 */
	@Override
	public Page<DemoCustomer> findPage(DemoCustomer demoCustomer) {
		return super.findPage(demoCustomer);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param demoCustomer
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DemoCustomer demoCustomer) {
		super.save(demoCustomer);
	}
	
	/**
	 * 更新状态
	 * @param demoCustomer
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DemoCustomer demoCustomer) {
		super.updateStatus(demoCustomer);
	}
	
	/**
	 * 删除数据
	 * @param demoCustomer
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DemoCustomer demoCustomer) {
		super.delete(demoCustomer);
	}
	
}