/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.TestData;

/**
 * 测试数据DAO接口
 * @author ThinkGem
 * @version 2018-04-22
 */
@MyBatisDao
public interface TestDataDao extends CrudDao<TestData> {
	
	/**
	 * 演示Map参数和返回值，支持分页
	 */
	public List<Map<String, Object>> findListForMap(Map<String, Object> params);
	
}