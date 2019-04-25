/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesschecktemplateinfo.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesschecktemplateinfo.entity.BusinessCheckTemplateInfo;

/**
 * 考核指标DAO接口
 * @author BusinessCheckTemplateInfo
 * @version 2019-04-25
 */
@MyBatisDao
public interface BusinessCheckTemplateInfoDao extends CrudDao<BusinessCheckTemplateInfo> {
	
}