/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessstagetarget.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businessstagetarget.entity.BusinessStageTarget;

/**
 * 阶段目标DAO接口
 * @author 阶段目标
 * @version 2019-04-22
 */
@MyBatisDao
public interface BusinessStageTargetDao extends CrudDao<BusinessStageTarget> {
	
}