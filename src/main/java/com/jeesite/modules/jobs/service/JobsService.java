/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jobs.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.jobs.dao.JobsDao;
import com.jeesite.modules.jobs.entity.Jobs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 目标对应的定时任务（定时采集/定时考核）Service
 * @author 目标对应的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class JobsService extends CrudService<JobsDao, Jobs> {
	
	/**
	 * 获取单条数据
	 * @param jobs
	 * @return
	 */
	@Override
	public Jobs get(Jobs jobs) {
		return super.get(jobs);
	}
	
	/**
	 * 查询分页数据
	 * @param jobs 查询条件
	 * @param jobs.page 分页对象
	 * @return
	 */
	@Override
	public Page<Jobs> findPage(Jobs jobs) {
		return super.findPage(jobs);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param jobs
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Jobs jobs) {
		super.save(jobs);
	}
	
	/**
	 * 更新状态
	 * @param jobs
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Jobs jobs) {
		super.updateStatus(jobs);
	}
	
	/**
	 * 删除数据
	 * @param jobs
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Jobs jobs) {
		super.delete(jobs);
	}
	
}