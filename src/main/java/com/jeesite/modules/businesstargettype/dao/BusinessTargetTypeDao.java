/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettype.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;

import java.util.List;

/**
 * 指标分类DAO接口
 * @author 指标分类
 * @version 2019-04-21
 */
@MyBatisDao
public interface BusinessTargetTypeDao extends CrudDao<BusinessTargetType> {

    List<BusinessTargetType> findList();
}