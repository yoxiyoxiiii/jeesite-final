/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.targets.entity.TargetType;

/**
 * 指标分类DAO接口
 * @author 指标分类
 * @version 2019-04-10
 */
@MyBatisDao
public interface TargetTypeDao extends CrudDao<TargetType> {
	
}