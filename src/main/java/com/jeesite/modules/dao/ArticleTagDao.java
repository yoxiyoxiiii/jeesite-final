/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.cms.entity.ArticleTag;

/**
 * 文章与标签关系DAO接口
 * @author 长春叭哥
 * @version 2018-10-15
 */
@MyBatisDao
public interface ArticleTagDao extends CrudDao<ArticleTag> {
	
}