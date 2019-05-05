/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.notifyc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.basic.notifyc.entity.NotifyC;

/**
 * 通知管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-17
 */
@MyBatisDao
public interface NotifyCDao extends CrudDao<NotifyC> {
	
}