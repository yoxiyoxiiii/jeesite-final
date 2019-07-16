/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dto.BusinessTargetDataInfoDto;
import com.jeesite.modules.entity.BusinessTargetDataInfo;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 上报的数据DAO接口
 * @author 上报的数据
 * @version 2019-06-07
 */
@MyBatisDao
public interface BusinessTargetDataInfoDao extends CrudDao<BusinessTargetDataInfo> {

    BusinessTargetDataInfo findByItemName(@Param("targetId") String targetId,
                                          @Param("dataItemName") String dataItemName);

    List<BusinessTargetDataInfoDto> findByUserCode(@Param("userCode")String userCode);

    void updateStatusBy(@Param("userCode") String userCode,
                        @Param("dataItemId") String dataItemId,
                        @Param("status") String status);

    void updateStatusByItems(@Param("targetId") String targetId,
                             @Param("dataItemId") String dataItemId,
                             @Param("userCode") String userCode,
                             @Param("stageId") String stageId,
                             @Param("dataInfo") String dataInfo,
                             @Param("status") String status);
}