/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.task.entity.TaskData;

/**
 * 上报的具体数据DAO接口
 * @author 上报的具体数据
 * @version 2019-04-10
 */
@MyBatisDao
public interface TaskDataDao extends CrudDao<TaskData> {
	
}