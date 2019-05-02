/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplanuser.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesscheckplanuser.entity.BusinessCheckPlanUser;

import java.util.List;

/**
 * 考核名单DAO接口
 * @author 考核名单
 * @version 2019-04-28
 */
@MyBatisDao
public interface BusinessCheckPlanUserDao extends CrudDao<BusinessCheckPlanUser> {

    List<BusinessCheckPlanUser> findByBusinessCheckPlanId(String businessCheckPlanId);
}