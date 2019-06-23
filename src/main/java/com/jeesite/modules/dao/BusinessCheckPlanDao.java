/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessCheckPlan;

/**
 * 考核计划DAO接口
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@MyBatisDao
public interface BusinessCheckPlanDao extends CrudDao<BusinessCheckPlan> {
	
}