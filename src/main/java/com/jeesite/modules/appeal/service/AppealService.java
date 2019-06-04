/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.appeal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.appeal.entity.Appeal;
import com.jeesite.modules.appeal.dao.AppealDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 申诉Service
 * @author sanye
 * @version 2019-06-04
 */
@Service
@Transactional(readOnly=true)
public class AppealService extends CrudService<AppealDao, Appeal> {
	
	/**
	 * 获取单条数据
	 * @param appeal
	 * @return
	 */
	@Override
	public Appeal get(Appeal appeal) {
		return super.get(appeal);
	}
	
	/**
	 * 查询分页数据
	 * @param appeal 查询条件
	 * @param appeal.page 分页对象
	 * @return
	 */
	@Override
	public Page<Appeal> findPage(Appeal appeal) {
		return super.findPage(appeal);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param appeal
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Appeal appeal) {
		super.save(appeal);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(appeal.getId(), "appeal_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(appeal.getId(), "appeal_file");
	}
	
	/**
	 * 更新状态
	 * @param appeal
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Appeal appeal) {
		super.updateStatus(appeal);
	}
	
	/**
	 * 删除数据
	 * @param appeal
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Appeal appeal) {
		super.delete(appeal);
	}
	
}