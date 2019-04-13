/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.check.entity.CheckScore;

/**
 * 考核评分名单DAO接口
 * @author 考核评分名单
 * @version 2019-04-10
 */
@MyBatisDao
public interface CheckScoreDao extends CrudDao<CheckScore> {
	
}