/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.notifyc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.notifyc.entity.NotifyC;
import com.jeesite.modules.basic.notifyc.dao.NotifyCDao;

/**
 * 通知管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-17
 */
@Service
@Transactional(readOnly=true)
public class NotifyCService extends CrudService<NotifyCDao, NotifyC> {
	
	/**
	 * 获取单条数据
	 * @param notifyC
	 * @return
	 */
	@Override
	public NotifyC get(NotifyC notifyC) {
		return super.get(notifyC);
	}
	
	/**
	 * 查询分页数据
	 * @param notifyC 查询条件
	 * @param notifyC.page 分页对象
	 * @return
	 */
	@Override
	public Page<NotifyC> findPage(NotifyC notifyC) {
		return super.findPage(notifyC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param notifyC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NotifyC notifyC) {
		super.save(notifyC);
	}
	
	/**
	 * 更新状态
	 * @param notifyC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NotifyC notifyC) {
		super.updateStatus(notifyC);
	}
	
	/**
	 * 删除数据
	 * @param notifyC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NotifyC notifyC) {
		super.delete(notifyC);
	}
	
}