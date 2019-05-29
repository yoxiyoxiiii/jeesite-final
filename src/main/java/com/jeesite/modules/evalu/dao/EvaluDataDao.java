/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.evalu.entity.EvaluData;

import java.util.List;
import java.util.Map;

/**
 * 民主测评记录DAO接口
 * @author sanye
 * @version 2019-05-30
 */
@MyBatisDao
public interface EvaluDataDao extends CrudDao<EvaluData> {
    public EvaluData findIsNew(EvaluData params);
}