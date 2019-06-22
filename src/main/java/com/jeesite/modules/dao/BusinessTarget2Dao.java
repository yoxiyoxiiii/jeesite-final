/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessTarget2;

import java.util.List;

/**
 * 指标DAO接口
 * @author BusinessTarget2
 * @version 2019-05-03
 */
@MyBatisDao
public interface BusinessTarget2Dao extends CrudDao<BusinessTarget2> {

    List<BusinessTarget2> findIn(List<String> targets);

    List<BusinessTarget2> findByTypeCode(String targetTypeCode);

    List<BusinessTarget2> findList();
}