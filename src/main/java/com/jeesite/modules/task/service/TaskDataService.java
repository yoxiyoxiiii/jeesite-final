/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.task.dao.TaskDataDao;
import com.jeesite.modules.task.entity.TaskData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 上报的具体数据Service
 * @author 上报的具体数据
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TaskDataService extends CrudService<TaskDataDao, TaskData> {
	
	/**
	 * 获取单条数据
	 * @param taskData
	 * @return
	 */
	@Override
	public TaskData get(TaskData taskData) {
		return super.get(taskData);
	}
	
	/**
	 * 查询分页数据
	 * @param taskData 查询条件
	 * @param taskData.page 分页对象
	 * @return
	 */
	@Override
	public Page<TaskData> findPage(TaskData taskData) {
		return super.findPage(taskData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param taskData
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TaskData taskData) {
		super.save(taskData);
	}
	
	/**
	 * 更新状态
	 * @param taskData
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TaskData taskData) {
		super.updateStatus(taskData);
	}
	
	/**
	 * 删除数据
	 * @param taskData
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TaskData taskData) {
		super.delete(taskData);
	}
	
}