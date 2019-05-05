/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 货物管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly=true)
public class ProductCService extends CrudService<ProductCDao, ProductC> {
	
	/**
	 * 获取单条数据
	 * @param productC
	 * @return
	 */
	@Override
	public ProductC get(ProductC productC) {
		return super.get(productC);
	}
	
	/**
	 * 查询分页数据
	 * @param productC 查询条件
	 * @param productC.page 分页对象
	 * @return
	 */
	@Override
	public Page<ProductC> findPage(ProductC productC) {
		return super.findPage(productC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param productC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ProductC productC) {
		super.save(productC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(productC.getId(), "productC_image");
	}
	
	/**
	 * 更新状态
	 * @param productC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ProductC productC) {
		super.updateStatus(productC);
	}
	
	/**
	 * 删除数据
	 * @param productC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ProductC productC) {
		super.delete(productC);
	}
	
}