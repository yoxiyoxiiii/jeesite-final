/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.product.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.basic.product.entity.ProductC;

/**
 * 货物管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@MyBatisDao
public interface ProductCDao extends CrudDao<ProductC> {
	
}