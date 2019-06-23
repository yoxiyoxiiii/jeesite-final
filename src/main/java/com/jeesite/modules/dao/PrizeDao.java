/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.Prize;

import java.util.List;
import java.util.Map;

/**
 * 奖扣记录DAO接口
 * @author sanye
 * @version 2019-05-03
 */
@MyBatisDao
public interface PrizeDao extends CrudDao<Prize> {

    public List<Map<String, Object>> prizeReport(Map<String, Object> params);
}