package com.jeesite.modules.basic.statistics.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.entity.OutProduct;

/**
 * 数据统计DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */
@MyBatisDao
public interface DataDao extends CrudDao<Data> {
	
	Data statisticsPayByMonth(@Param("date") String date);
	
	Data statisticsReByMonth(@Param("date") String date);
	
	List<Data> statisticsSalesByYear(@Param("date") String date);
	
	List<Data> statisticsCostByYear(@Param("date") String date);
	
	List<Data> statisticsBenefitsByYear(@Param("date") String date);
	
	List<OutProduct> statisticsProudctsByYear(@Param("date") String date);
	
	List<Data> statisticsSellerRankingByMonth(@Param("date") String date);
}
