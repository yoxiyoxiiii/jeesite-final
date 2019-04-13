/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.appeal.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.appeal.entity.Appeal;

/**
 * 评分结果申诉DAO接口
 * @author 评分结果申诉
 * @version 2019-04-10
 */
@MyBatisDao
public interface AppealDao extends CrudDao<Appeal> {
	
}