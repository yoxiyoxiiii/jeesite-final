/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.evalu.entity.Evalu;

/**
 * 民主测评DAO接口
 * @author sanye
 * @version 2019-05-16
 */
@MyBatisDao
public interface EvaluDao extends CrudDao<Evalu> {
	
}