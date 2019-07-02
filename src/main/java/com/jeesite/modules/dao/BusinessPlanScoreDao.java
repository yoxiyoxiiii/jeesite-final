/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessPlanScore;

/**
 * 考核评分DAO接口
 * @author BusinessPlanScore
 * @version 2019-07-02
 */
@MyBatisDao
public interface BusinessPlanScoreDao extends CrudDao<BusinessPlanScore> {
	
}