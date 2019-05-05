/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.factory.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.basic.factory.entity.FactoryC;

/**
 * 厂家管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@MyBatisDao
public interface FactoryCDao extends CrudDao<FactoryC> {
	
}