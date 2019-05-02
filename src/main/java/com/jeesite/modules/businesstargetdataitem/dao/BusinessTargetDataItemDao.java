/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargetdataitem.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.businesstargetdataitem.entity.BusinessTargetDataItem;

import java.util.List;

/**
 * 数据采集项DAO接口
 * @author BusinessTargetDataItem
 * @version 2019-04-22
 */
@MyBatisDao
public interface BusinessTargetDataItemDao extends CrudDao<BusinessTargetDataItem> {

    List<BusinessTargetDataItem> findByBusinessTargetId(String businessTargetId);
}