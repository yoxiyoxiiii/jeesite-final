/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.accessory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.accessory.entity.AccessoryC;
import com.jeesite.modules.basic.accessory.dao.AccessoryCDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 附件管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly=true)
public class AccessoryCService extends CrudService<AccessoryCDao, AccessoryC> {
	
	/**
	 * 获取单条数据
	 * @param accessoryC
	 * @return
	 */
	@Override
	public AccessoryC get(AccessoryC accessoryC) {
		return super.get(accessoryC);
	}
	
	/**
	 * 查询分页数据
	 * @param accessoryC 查询条件
	 * @param accessoryC.page 分页对象
	 * @return
	 */
	@Override
	public Page<AccessoryC> findPage(AccessoryC accessoryC) {
		return super.findPage(accessoryC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param accessoryC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(AccessoryC accessoryC) {
		super.save(accessoryC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(accessoryC.getId(), "accessoryC_image");
	}
	
	/**
	 * 更新状态
	 * @param accessoryC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(AccessoryC accessoryC) {
		super.updateStatus(accessoryC);
	}
	
	/**
	 * 删除数据
	 * @param accessoryC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(AccessoryC accessoryC) {
		super.delete(accessoryC);
	}
	
}