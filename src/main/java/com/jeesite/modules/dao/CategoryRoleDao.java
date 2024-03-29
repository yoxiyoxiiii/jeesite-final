/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.cms.entity.CategoryRole;

/**
 * 栏目与角色关联表DAO接口
 * @author 长春叭哥
 * @version 2018-10-15
 */
@MyBatisDao
public interface CategoryRoleDao extends CrudDao<CategoryRole> {
	
}