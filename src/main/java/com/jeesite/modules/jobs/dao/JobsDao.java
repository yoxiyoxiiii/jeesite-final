/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jobs.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.jobs.entity.Jobs;

/**
 * 目标对应的定时任务（定时采集/定时考核）DAO接口
 * @author 目标对应的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@MyBatisDao
public interface JobsDao extends CrudDao<Jobs> {
	
}