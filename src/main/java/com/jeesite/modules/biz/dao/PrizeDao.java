/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.biz.entity.Prize;

/**
 * 奖扣记录DAO接口
 * @author sanye
 * @version 2019-05-03
 */
@MyBatisDao
public interface PrizeDao extends CrudDao<Prize> {
	
}