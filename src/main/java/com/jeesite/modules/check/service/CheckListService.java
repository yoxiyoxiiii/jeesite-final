/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.check.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.check.dao.CheckListDao;
import com.jeesite.modules.check.entity.CheckList;
import com.jeesite.modules.file.utils.FileUploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考核名单Service
 * @author 考核名单
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class CheckListService extends CrudService<CheckListDao, CheckList> {
	
	/**
	 * 获取单条数据
	 * @param checkList
	 * @return
	 */
	@Override
	public CheckList get(CheckList checkList) {
		return super.get(checkList);
	}
	
	/**
	 * 查询分页数据
	 * @param checkList 查询条件
	 * @param checkList.page 分页对象
	 * @return
	 */
	@Override
	public Page<CheckList> findPage(CheckList checkList) {
		return super.findPage(checkList);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param checkList
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CheckList checkList) {
		super.save(checkList);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(checkList.getId(), "checkList_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(checkList.getId(), "checkList_file");
	}
	
	/**
	 * 更新状态
	 * @param checkList
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CheckList checkList) {
		super.updateStatus(checkList);
	}
	
	/**
	 * 删除数据
	 * @param checkList
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CheckList checkList) {
		super.delete(checkList);
	}
	
}