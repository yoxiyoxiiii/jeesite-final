/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test1.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test1.entity.Test3;

/**
 * test1DAO接口
 * @author Test1
 * @version 2019-05-03
 */
@MyBatisDao
public interface Test3Dao extends CrudDao<Test3> {
	
}