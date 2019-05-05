/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.customers.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.basic.customers.entity.CustomersC;

/**
 * 客户管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@MyBatisDao
public interface CustomersCDao extends CrudDao<CustomersC> {
	
}