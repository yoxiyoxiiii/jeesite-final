/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessPlanUserTask;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 目标生成的任务DAO接口
 * @author yj
 * @version 2019-05-02
 */
@MyBatisDao
public interface BusinessPlanUserTaskDao extends CrudDao<BusinessPlanUserTask> {

    void updateStatusBy(@Param("targetId") String targetId,
                        @Param("dataItemId") String dataItemId,
                        @Param("userCode") String userCode,
                        @Param("status") String status);

    List<BusinessPlanUserTask> findPageDisCount(@Param("pNo")int pNo, @Param("pSize")int pSize);

    void updateStatusByItems(@Param("targetId")  String targetId,
                             @Param("dataItemId") String stageId,
                             @Param("userCode") String userCode,
                             @Param("status") String status);
}