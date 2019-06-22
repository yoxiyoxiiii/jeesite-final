/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.StageTarget;

import java.util.List;

/**
 * 阶段目标DAO接口
 * @author StageTarget
 * @version 2019-04-22
 */
@MyBatisDao
public interface StageTargetDao extends CrudDao<StageTarget> {

    List<StageTarget> findList();
}