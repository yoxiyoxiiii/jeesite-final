/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.Evalu;
import com.jeesite.modules.entity.EvaluData;
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
    public List<Office> findByIn(Map<String, Object> params);
    public List<Map<String, Object>> findListForMap(Map<String, Object> params);

    //获取对比表单
    public List<EvaluData> findGrid(Map<String, Object> params);
    public List<Map<String, Object>> findReport(Map<String, Object> params);
    public List<Map<String, Object>> findUsers(Map<String, Object> params);
}