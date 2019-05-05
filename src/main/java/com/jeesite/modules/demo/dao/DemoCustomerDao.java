/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.demo.entity.DemoCustomer;

/**
 * demo_customerDAO接口
 * @author sanye
 * @version 2019-04-23
 */
@MyBatisDao
public interface DemoCustomerDao extends CrudDao<DemoCustomer> {
	
}