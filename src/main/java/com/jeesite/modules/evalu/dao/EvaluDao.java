/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.evalu.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.evalu.entity.Evalu;
import com.jeesite.modules.sys.entity.Office;

import java.util.List;
import java.util.Map;

/**
 * 民主测评DAO接口
 * @author sanye
 * @version 2019-05-16
 */
@MyBatisDao
public interface EvaluDao extends CrudDao<Evalu> {


    /**
     * 演示Map参数和返回值，支持分页
     */
    public List<Office> findByIn(Map<String, Object> params);
    public List<Map<String, Object>> findListForMap(Map<String, Object> params);
}