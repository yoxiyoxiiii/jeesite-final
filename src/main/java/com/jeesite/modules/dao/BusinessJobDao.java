/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessJob;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 定时任务DAO接口
 * @author BusinessJob
 * @version 2019-04-28
 */
@MyBatisDao
public interface BusinessJobDao extends CrudDao<BusinessJob> {

    List<BusinessJob> findByBusinessCheckPlanId(String businessCheckPlanId);

    void updateJobStatus(@Param("checkPlanId") String checkPlanId, @Param("jobStatus") String jobStatus);


    BusinessJob findByTargetId(@Param("businessTargetId") String businessTargetId);
}