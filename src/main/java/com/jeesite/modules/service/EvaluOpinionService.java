/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.EvaluOpinion;
import com.jeesite.modules.dao.EvaluOpinionDao;

/**
 * 民主测评意见Service
 * @author sanye
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly=true)
public class EvaluOpinionService extends CrudService<EvaluOpinionDao, EvaluOpinion> {
	
	/**
	 * 获取单条数据
	 * @param evaluOpinion
	 * @return
	 */
	@Override
	public EvaluOpinion get(EvaluOpinion evaluOpinion) {
		return super.get(evaluOpinion);
	}
	
	/**
	 * 查询分页数据
	 * @param evaluOpinion 查询条件
	 * @param evaluOpinion.page 分页对象
	 * @return
	 */
	@Override
	public Page<EvaluOpinion> findPage(EvaluOpinion evaluOpinion) {
		return super.findPage(evaluOpinion);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param evaluOpinion
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EvaluOpinion evaluOpinion) {
		super.save(evaluOpinion);
	}


	/**
	 * 保存民主测评意见
	 */
	@Transactional(readOnly=false)
	public void saveOpinion(EvaluOpinion evaluOpinion, String evaluId, String depatId) {
		//联合主键判断新增\修改
		EvaluOpinion temp = new EvaluOpinion();
		temp.setEvaluId(evaluId);
		temp.setDeptId(depatId);
		temp.setCreateBy(UserUtils.getUser().getUserCode());
//		temp.setPage(new Page<>(request, response));
		EvaluOpinion tempSearch = dao.findOpinion(temp);
		if( tempSearch == null ){
			evaluOpinion.setIsNewRecord(true);
		}else{
			evaluOpinion.setIsNewRecord(false);
			evaluOpinion.setId(tempSearch.getId());
		}

		super.save(evaluOpinion);
	}

	/**
	 * 更新状态
	 * @param evaluOpinion
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EvaluOpinion evaluOpinion) {
		super.updateStatus(evaluOpinion);
	}
	
	/**
	 * 删除数据
	 * @param evaluOpinion
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EvaluOpinion evaluOpinion) {
		super.delete(evaluOpinion);
	}
	
}