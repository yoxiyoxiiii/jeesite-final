/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessTargetTaskMonitor;

import java.util.List;

/**
 * 任务监控DAO接口
 * @author 任务监控/任务列表
 * @version 2019-06-09
 */
@MyBatisDao
public interface BusinessTargetTaskMonitorDao extends TreeDao<BusinessTargetTaskMonitor> {

    List<BusinessTargetTaskMonitor> findAll();

    BusinessTargetTaskMonitor findByIds(String targetId, String officeCode, String businessCheckPlanId);

    void updateByIds(String targetId, String officeCode,
                     String businessCheckPlanId,
                     Integer upItemCount,
                     String status

    );
}