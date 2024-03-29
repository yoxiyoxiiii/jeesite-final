/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dao.BusinessStageTarget2Dao;
import com.jeesite.modules.dao.BusinessTarget2Dao;
import com.jeesite.modules.dao.BusinessTargetDataItem2Dao;
import com.jeesite.modules.entity.BusinessStageTarget2;
import com.jeesite.modules.entity.BusinessTarget2;
import com.jeesite.modules.entity.BusinessTargetDataItem2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 指标Service
 * @author BusinessTarget2
 * @version 2019-05-03
 */
@Service
@Transactional(readOnly=true)
public class BusinessTarget2Service extends CrudService<BusinessTarget2Dao, BusinessTarget2> {
	
	@Autowired
	private BusinessTargetDataItem2Dao businessTargetDataItem2Dao;
	
	@Autowired
	private BusinessStageTarget2Dao businessStageTarget2Dao;
	
	/**
	 * 获取单条数据
	 * @param businessTarget2
	 * @return
	 */
	@Override
	public BusinessTarget2 get(BusinessTarget2 businessTarget2) {
		BusinessTarget2 entity = super.get(businessTarget2);
		if (entity != null){
			BusinessTargetDataItem2 businessTargetDataItem2 = new BusinessTargetDataItem2(entity);
			businessTargetDataItem2.setStatus(BusinessTargetDataItem2.STATUS_NORMAL);
			entity.setBusinessTargetDataItem2List(businessTargetDataItem2Dao.findList(businessTargetDataItem2));
			BusinessStageTarget2 businessStageTarget2 = new BusinessStageTarget2(entity);
			businessStageTarget2.setStatus(BusinessStageTarget2.STATUS_NORMAL);
			entity.setBusinessStageTarget2List(businessStageTarget2Dao.findList(businessStageTarget2));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param businessTarget2 查询条件
	 * @return
	 */
	@Override
	public Page<BusinessTarget2> findPage(BusinessTarget2 businessTarget2) {
		return super.findPage(businessTarget2);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTarget2
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTarget2 businessTarget2) {
		super.save(businessTarget2);
		// 保存 BusinessTarget2子表
		for (BusinessTargetDataItem2 businessTargetDataItem2 : businessTarget2.getBusinessTargetDataItem2List()){
			if (!BusinessTargetDataItem2.STATUS_DELETE.equals(businessTargetDataItem2.getStatus())){
				businessTargetDataItem2.setTargetId(businessTarget2);
				if (businessTargetDataItem2.getIsNewRecord()){
					businessTargetDataItem2.preInsert();
					businessTargetDataItem2Dao.insert(businessTargetDataItem2);
				}else{
					businessTargetDataItem2.preUpdate();
					businessTargetDataItem2Dao.update(businessTargetDataItem2);
				}
			}else{
				businessTargetDataItem2Dao.delete(businessTargetDataItem2);
			}
		}
		List<BusinessStageTarget2> businessStageTarget2List = businessTarget2.getBusinessStageTarget2List();
		// 保存 BusinessTarget2子表
		for (int i=0; i<businessStageTarget2List.size() ; i++){
			BusinessStageTarget2 businessStageTarget2 = businessStageTarget2List.get(i);
			if (!BusinessStageTarget2.STATUS_DELETE.equals(businessStageTarget2.getStatus())){
				businessStageTarget2.setTargetId(businessTarget2);
				if (businessStageTarget2.getIsNewRecord()){
					businessStageTarget2.preInsert();
					businessStageTarget2.setStageOrder(i+1);//设置排序
					businessStageTarget2Dao.insert(businessStageTarget2);
				}else{
					businessStageTarget2.preUpdate();
					businessStageTarget2Dao.update(businessStageTarget2);
				}
			}else{
				businessStageTarget2Dao.delete(businessStageTarget2);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param businessTarget2
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTarget2 businessTarget2) {
		super.updateStatus(businessTarget2);
	}
	
	/**
	 * 删除数据
	 * @param businessTarget2
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTarget2 businessTarget2) {
		super.delete(businessTarget2);
		BusinessTargetDataItem2 businessTargetDataItem2 = new BusinessTargetDataItem2();
		businessTargetDataItem2.setTargetId(businessTarget2);
		businessTargetDataItem2Dao.deleteByEntity(businessTargetDataItem2);
		BusinessStageTarget2 businessStageTarget2 = new BusinessStageTarget2();
		businessStageTarget2.setTargetId(businessTarget2);
		businessStageTarget2Dao.deleteByEntity(businessStageTarget2);
	}

	public List<BusinessTarget2> findIn(List<String> targets) {

		return super.dao.findIn(targets);
	}

    public List<BusinessTarget2> findByTypeCode(String targetTypeCode) {
		return super.dao.findByTypeCode(targetTypeCode);
    }

	public List<BusinessTarget2> findList() {
		return super.dao.findList();
	}

	public List<BusinessTarget2> findByTypeCode(String targetTypeCode, Integer pageNo, Integer pageSize) {
		return super.dao.findByTypeCodeByPage(targetTypeCode, pageNo, pageSize);
	}

	public List<BusinessTarget2> findPage(String checkPlanId,Integer pageNo, Integer pageSize) {
		return dao.findPageByCheckPlanId(checkPlanId,pageNo, pageSize);
	}

	public List<BusinessTarget2> findListByPlanId(@Param(value = "planId") String planId) {
		return dao.findListByPlanId(planId);
	}

	public BusinessStageTarget2 findTargetStageBy(String id, Integer currentStageNumber) {
		return dao.findTargetStageBy(id, currentStageNumber);
	}

	public int countStages(String id) {
		return dao.countStages(id);
	}
}