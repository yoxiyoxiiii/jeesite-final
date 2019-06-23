/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessTargetDataInfo;

/**
 * 上报的数据DAO接口
 * @author 上报的数据
 * @version 2019-06-07
 */
@MyBatisDao
public interface BusinessTargetDataInfoDao extends CrudDao<BusinessTargetDataInfo> {
	
}