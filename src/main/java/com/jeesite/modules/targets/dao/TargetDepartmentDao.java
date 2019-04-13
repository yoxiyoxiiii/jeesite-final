/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.targets.entity.TargetDepartment;

/**
 * 单位/部门目标分解DAO接口
 * @author 单位/部门目标分解
 * @version 2019-04-10
 */
@MyBatisDao
public interface TargetDepartmentDao extends CrudDao<TargetDepartment> {
	
}