/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplat.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesschecktemplat.entity.BusinessCheckTemplate;

/**
 * 考核模板DAO接口
 * @author 考核模板
 * @version 2019-04-25
 */
@MyBatisDao
public interface BusinessCheckTemplateDao extends CrudDao<BusinessCheckTemplate> {
	
}