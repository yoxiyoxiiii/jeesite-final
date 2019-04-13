/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.targets.entity.Targets;

/**
 * targetsDAO接口
 * @author target
 * @version 2019-04-10
 */
@MyBatisDao
public interface TargetsDao extends CrudDao<Targets> {
	
}