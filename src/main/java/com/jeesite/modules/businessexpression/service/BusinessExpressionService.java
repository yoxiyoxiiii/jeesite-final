/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessexpression.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.businessexpression.entity.BusinessExpression;
import com.jeesite.modules.businessexpression.dao.BusinessExpressionDao;
import com.jeesite.modules.businessexpression.entity.BusinessExpressionParam;
import com.jeesite.modules.businessexpression.dao.BusinessExpressionParamDao;

/**
 * business_ expressionService
 * @author BusinessExpression
 * @version 2019-05-25
 */
@Service
@Transactional(readOnly=true)
public class BusinessExpressionService extends CrudService<BusinessExpressionDao, BusinessExpression> {
	
	@Autowired
	private BusinessExpressionParamDao businessExpressionParamDao;
	
	/**
	 * 获取单条数据
	 * @param businessExpression
	 * @return
	 */
	@Override
	public BusinessExpression get(BusinessExpression businessExpression) {
		BusinessExpression entity = super.get(businessExpression);
		if (entity != null){
			BusinessExpressionParam businessExpressionParam = new BusinessExpressionParam(entity);
			businessExpressionParam.setStatus(BusinessExpressionParam.STATUS_NORMAL);
			entity.setBusinessExpressionParamList(businessExpressionParamDao.findList(businessExpressionParam));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param businessExpression 查询条件
	 * @param businessExpression.page 分页对象
	 * @return
	 */
	@Override
	public Page<BusinessExpression> findPage(BusinessExpression businessExpression) {
		return super.findPage(businessExpression);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessExpression
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessExpression businessExpression) {
		super.save(businessExpression);
		// 保存 BusinessExpression子表
		for (BusinessExpressionParam businessExpressionParam : businessExpression.getBusinessExpressionParamList()){
			if (!BusinessExpressionParam.STATUS_DELETE.equals(businessExpressionParam.getStatus())){
				businessExpressionParam.setExpressionId(businessExpression);
				if (businessExpressionParam.getIsNewRecord()){
					businessExpressionParam.preInsert();
					businessExpressionParamDao.insert(businessExpressionParam);
				}else{
					businessExpressionParam.preUpdate();
					businessExpressionParamDao.update(businessExpressionParam);
				}
			}else{
				businessExpressionParamDao.delete(businessExpressionParam);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param businessExpression
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessExpression businessExpression) {
		super.updateStatus(businessExpression);
	}
	
	/**
	 * 删除数据
	 * @param businessExpression
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessExpression businessExpression) {
		super.delete(businessExpression);
		BusinessExpressionParam businessExpressionParam = new BusinessExpressionParam();
		businessExpressionParam.setExpressionId(businessExpression);
		businessExpressionParamDao.deleteByEntity(businessExpressionParam);
	}
	
}