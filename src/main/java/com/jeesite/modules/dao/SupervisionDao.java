/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.Supervision;

/**
 * 督查督办DAO接口
 * @author sanye
 * @version 2019-06-04
 */
@MyBatisDao
public interface SupervisionDao extends CrudDao<Supervision> {
	
}