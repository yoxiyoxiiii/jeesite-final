/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.entity.BusinessTarget2;
import com.jeesite.modules.dao.BusinessTargetDataItemDao;
import com.jeesite.modules.entity.BusinessTargetDataItem;
import com.jeesite.modules.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据采集项Service
 * @author BusinessTargetDataItem
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly=true)
public class BusinessTargetDataItemService extends CrudService<BusinessTargetDataItemDao, BusinessTargetDataItem> {

	@Autowired
	private StageTargetService stageTargetService;

	/**
	 * 获取单条数据
	 * @param businessTargetDataItem
	 * @return
	 */
	@Override
	public BusinessTargetDataItem get(BusinessTargetDataItem businessTargetDataItem) {
		return super.get(businessTargetDataItem);
	}
	
	/**
	 * 查询分页数据
	 * @param businessTargetDataItem 查询条件
	 * @return
	 */
	@Override
	public Page<BusinessTargetDataItem> findPage(BusinessTargetDataItem businessTargetDataItem) {
		return super.findPage(businessTargetDataItem);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetDataItem
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(BusinessTargetDataItem businessTargetDataItem) {
		String stageId = businessTargetDataItem.getStagetargetId();
		BusinessTarget2 businessTargets = stageTargetService.get(stageId).getBusinessTargets();
		businessTargetDataItem.setTargetId(businessTargets);
		super.save(businessTargetDataItem);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(businessTargetDataItem.getId(), "businessTargetDataItem_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(businessTargetDataItem.getId(), "businessTargetDataItem_file");
	}


	/**
	 * 保存数据（插入或更新）
	 * @param businessTargetDataItem
	 */
	@Override
	@Transactional(readOnly=false)
	public void update(BusinessTargetDataItem businessTargetDataItem) {
	   super.dao.update(businessTargetDataItem);
	}

	/**
	 * 更新状态
	 * @param businessTargetDataItem
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(BusinessTargetDataItem businessTargetDataItem) {
		super.updateStatus(businessTargetDataItem);
	}
	
	/**
	 * 删除数据
	 * @param businessTargetDataItem
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(BusinessTargetDataItem businessTargetDataItem) {
		super.delete(businessTargetDataItem);
	}

	public List<BusinessTargetDataItem> findByBusinessTargetId(String businessTargetId) {
		return dao.findByBusinessTargetId(businessTargetId);
	}

	@Transactional(readOnly=false)
	public void updateItemStatus(String id, String userCode, String status) {
		dao.updateItemStatus(id, userCode,status);
	}
}