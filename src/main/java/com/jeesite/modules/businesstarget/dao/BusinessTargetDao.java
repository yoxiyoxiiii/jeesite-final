/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstarget.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesstarget.entity.BusinessTarget;

import java.util.List;

/**
 * 指标DAO接口
 * @author BusinessTarget
 * @version 2019-04-20
 */
@MyBatisDao
public interface BusinessTargetDao extends CrudDao<BusinessTarget> {

    List<BusinessTarget> findList();

    List<BusinessTarget> findListByCheckTemplateId(String checkTemplateId);
}