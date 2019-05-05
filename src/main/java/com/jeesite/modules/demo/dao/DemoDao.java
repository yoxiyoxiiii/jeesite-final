/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.demo.entity.Demo;

/**
 * 样例数据DAO接口
 * @author sanye
 * @version 2019-04-30
 */
@MyBatisDao
public interface DemoDao extends CrudDao<Demo> {
	
}