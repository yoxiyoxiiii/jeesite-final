/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessCheckPlan;

import java.util.List;
import java.util.Map;

/**
 * 考核计划DAO接口
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@MyBatisDao
public interface BusinessCheckPlanDao extends CrudDao<BusinessCheckPlan> {
    public List<Map<String, Object>> findReport(Map<String, Object> params);
}