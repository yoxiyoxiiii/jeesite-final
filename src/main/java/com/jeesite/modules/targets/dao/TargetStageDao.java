/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.targets.entity.TargetStage;

/**
 * 阶段目标DAO接口
 * @author 阶段目标
 * @version 2019-04-10
 */
@MyBatisDao
public interface TargetStageDao extends CrudDao<TargetStage> {
	
}