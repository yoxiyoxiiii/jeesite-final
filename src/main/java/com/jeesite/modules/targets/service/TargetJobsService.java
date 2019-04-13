/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.targets.dao.TargetJobsDao;
import com.jeesite.modules.targets.entity.TargetJobs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 目的的定时任务（定时采集/定时考核）Service
 * @author 目的的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TargetJobsService extends CrudService<TargetJobsDao, TargetJobs> {
	
	/**
	 * 获取单条数据
	 * @param targetJobs
	 * @return
	 */
	@Override
	public TargetJobs get(TargetJobs targetJobs) {
		return super.get(targetJobs);
	}
	
	/**
	 * 查询分页数据
	 * @param targetJobs 查询条件
	 * @param targetJobs.page 分页对象
	 * @return
	 */
	@Override
	public Page<TargetJobs> findPage(TargetJobs targetJobs) {
		return super.findPage(targetJobs);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param targetJobs
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TargetJobs targetJobs) {
		super.save(targetJobs);
	}
	
	/**
	 * 更新状态
	 * @param targetJobs
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TargetJobs targetJobs) {
		super.updateStatus(targetJobs);
	}
	
	/**
	 * 删除数据
	 * @param targetJobs
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TargetJobs targetJobs) {
		super.delete(targetJobs);
	}
	
}