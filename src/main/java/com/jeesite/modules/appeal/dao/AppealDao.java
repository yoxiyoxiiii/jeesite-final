/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.appeal.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.appeal.entity.Appeal;

/**
 * 申诉DAO接口
 * @author sanye
 * @version 2019-06-04
 */
@MyBatisDao
public interface AppealDao extends CrudDao<Appeal> {
	
}