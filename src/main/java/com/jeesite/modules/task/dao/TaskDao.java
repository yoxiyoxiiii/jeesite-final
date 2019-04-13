/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.task.entity.Task;

/**
 * 任务DAO接口
 * @author 任务
 * @version 2019-04-10
 */
@MyBatisDao
public interface TaskDao extends CrudDao<Task> {
	
}