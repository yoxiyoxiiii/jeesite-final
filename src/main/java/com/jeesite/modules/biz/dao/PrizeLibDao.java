/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.biz.entity.PrizeLib;

/**
 * 奖扣分类DAO接口
 * @author sanye
 * @version 2019-05-02
 */
@MyBatisDao
public interface PrizeLibDao extends CrudDao<PrizeLib> {
	
}