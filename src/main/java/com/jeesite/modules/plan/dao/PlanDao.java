/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plan.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.plan.entity.Plan;

/**
 * 考核计划DAO接口
 * @author 考核计划
 * @version 2019-04-10
 */
@MyBatisDao
public interface PlanDao extends CrudDao<Plan> {
	
}