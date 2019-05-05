/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.accessory.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.basic.accessory.entity.AccessoryC;

/**
 * 附件管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@MyBatisDao
public interface AccessoryCDao extends CrudDao<AccessoryC> {
	
}