/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.demo.entity.Demo;
import com.jeesite.modules.demo.dao.DemoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 样例数据Service
 * @author sanye
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly=true)
public class DemoService extends CrudService<DemoDao, Demo> {
	
	/**
	 * 获取单条数据
	 * @param demo
	 * @return
	 */
	@Override
	public Demo get(Demo demo) {
		return super.get(demo);
	}
	
	/**
	 * 查询分页数据
	 * @param demo 查询条件
	 * @param demo.page 分页对象
	 * @return
	 */
	@Override
	public Page<Demo> findPage(Demo demo) {
		return super.findPage(demo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param demo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Demo demo) {
		super.save(demo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(demo.getId(), "demo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(demo.getId(), "demo_file");
	}
	
	/**
	 * 更新状态
	 * @param demo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Demo demo) {
		super.updateStatus(demo);
	}
	
	/**
	 * 删除数据
	 * @param demo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Demo demo) {
		super.delete(demo);
	}
	
}