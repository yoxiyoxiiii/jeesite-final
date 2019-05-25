/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessexpression.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businessexpression.entity.BusinessExpression;

/**
 * business_ expressionDAO接口
 * @author BusinessExpression
 * @version 2019-05-25
 */
@MyBatisDao
public interface BusinessExpressionDao extends CrudDao<BusinessExpression> {
	
}