/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.EvaluData;
import com.jeesite.modules.dao.EvaluDataDao;

/**
 * 民主测评记录Service
 * @author sanye
 * @version 2019-06-01
 */
@Service
@Transactional(readOnly=true)
public class EvaluDataService extends CrudService<EvaluDataDao, EvaluData> {
	
	/**
	 * 获取单条数据
	 * @param evaluData
	 * @return
	 */
	@Override
	public EvaluData get(EvaluData evaluData) {
		return super.get(evaluData);
	}
	
	/**
	 * 查询分页数据
	 * @param evaluData 查询条件
	 * @param evaluData.page 分页对象
	 * @return
	 */
	@Override
	public Page<EvaluData> findPage(EvaluData evaluData) {
		return super.findPage(evaluData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param evaluData
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EvaluData evaluData) {
		//联合主键判断新增\修改
		EvaluData temp = new EvaluData();
		temp.setEvaluLibId(evaluData.getEvaluLibId());
		temp.setDeptId(evaluData.getDeptId());
		temp.setCreateBy(UserUtils.getUser().getUserCode());
//		temp.setPage(new Page<>(request, response));
		EvaluData tempSearch = dao.findEvaluData(temp);
		if( tempSearch == null ){
			evaluData.setIsNewRecord(true);
		}else{
			evaluData.setIsNewRecord(false);
			evaluData.setId(tempSearch.getId());
		}
		super.save(evaluData);
	}
	
	/**
	 * 更新状态
	 * @param evaluData
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EvaluData evaluData) {
		super.updateStatus(evaluData);
	}
	
	/**
	 * 删除数据
	 * @param evaluData
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EvaluData evaluData) {
		super.delete(evaluData);
	}
	
}