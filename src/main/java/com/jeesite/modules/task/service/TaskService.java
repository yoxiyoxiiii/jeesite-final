/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.task.dao.TaskDao;
import com.jeesite.modules.task.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务Service
 * @author 任务
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class TaskService extends CrudService<TaskDao, Task> {
	
	/**
	 * 获取单条数据
	 * @param task
	 * @return
	 */
	@Override
	public Task get(Task task) {
		return super.get(task);
	}
	
	/**
	 * 查询分页数据
	 * @param task 查询条件
	 * @param task.page 分页对象
	 * @return
	 */
	@Override
	public Page<Task> findPage(Task task) {
		return super.findPage(task);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param task
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Task task) {
		super.save(task);
	}
	
	/**
	 * 更新状态
	 * @param task
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Task task) {
		super.updateStatus(task);
	}
	
	/**
	 * 删除数据
	 * @param task
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Task task) {
		super.delete(task);
	}
	
}