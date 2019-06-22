package com.jeesite.modules.businesstargettype.dao; /**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;

/**
 * 目标分类DAO接口
 * @author sanye
 * @version 2019-06-20
 */
@MyBatisDao
public interface BusinessTargetTypeDao extends TreeDao<BusinessTargetType> {
	
}