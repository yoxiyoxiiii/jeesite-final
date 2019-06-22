/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.EvaluLib;

/**
 * 民主测评明细树表DAO接口
 * @author sanye
 * @version 2019-05-17
 */
@MyBatisDao
public interface EvaluLibDao extends TreeDao<EvaluLib> {
	
}