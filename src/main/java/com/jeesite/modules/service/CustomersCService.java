/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.CustomersC;
import com.jeesite.modules.dao.CustomersCDao;

/**
 * 客户管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class CustomersCService extends CrudService<CustomersCDao, CustomersC> {
	
	/**
	 * 获取单条数据
	 * @param customersC
	 * @return
	 */
	@Override
	public CustomersC get(CustomersC customersC) {
		return super.get(customersC);
	}
	
	/**
	 * 查询分页数据
	 * @param customersC 查询条件
	 * @param customersC.page 分页对象
	 * @return
	 */
	@Override
	public Page<CustomersC> findPage(CustomersC customersC) {
		return super.findPage(customersC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param customersC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CustomersC customersC) {
		super.save(customersC);
	}
	
	/**
	 * 更新状态
	 * @param customersC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CustomersC customersC) {
		super.updateStatus(customersC);
	}
	
	/**
	 * 删除数据
	 * @param customersC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CustomersC customersC) {
		super.delete(customersC);
	}
	
}