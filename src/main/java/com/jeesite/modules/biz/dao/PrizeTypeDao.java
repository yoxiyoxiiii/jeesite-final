/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.biz.entity.PrizeType;

/**
 * 奖扣类型DAO接口
 * @author sanye
 * @version 2019-05-02
 */
@MyBatisDao
public interface PrizeTypeDao extends CrudDao<PrizeType> {
	
}