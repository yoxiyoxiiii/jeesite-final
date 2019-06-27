/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.entity.BusinessStageTarget2;
import com.jeesite.modules.entity.BusinessTarget2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 指标DAO接口
 * @author BusinessTarget2
 * @version 2019-05-03
 */
@MyBatisDao
public interface BusinessTarget2Dao extends CrudDao<BusinessTarget2> {

    List<BusinessTarget2> findIn(List<String> targets);

    List<BusinessTarget2> findByTypeCode(String targetTypeCode);

    List<BusinessTarget2> findList();

    List<BusinessTarget2> findByTypeCodeByPage(@Param(value = "targetTypeCode")String targetTypeCode,
                                               @Param(value = "pageNo") Integer pageNo,
                                               @Param(value = "pageSize")Integer pageSize);

    List<BusinessTarget2> findPageByCheckPlanId(@Param(value = "checkPlanId")String checkPlanId,
                                                @Param(value = "pageNo") Integer pageNo,
                                                @Param(value = "pageSize") Integer pageSize);

    List<BusinessTarget2> findListByPlanId(String planId);

    BusinessStageTarget2 findTargetStageBy(@Param(value = "id") String id,
                                           @Param(value = "currentStageNumber")Integer currentStageNumber);

    /**
     * 根据target ID 统计 有阶段目标数
     * @param id
     * @return
     */
    int countStages(@Param("id") String id);
}