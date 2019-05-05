/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.product.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.service.ProductCService;

/**
 * 货物管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productC")
public class ProductCController extends BaseController {

	@Autowired
	private ProductCService productCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ProductC get(String id, boolean isNewRecord) {
		return productCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("product:productC:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductC productC, Model model) {
		model.addAttribute("productC", productC);
		return "basic/product/productCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("product:productC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ProductC> listData(ProductC productC, HttpServletRequest request, HttpServletResponse response) {
		productC.setPage(new Page<>(request, response));
		Page<ProductC> page = productCService.findPage(productC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("product:productC:view")
	@RequestMapping(value = "form")
	public String form(ProductC productC, Model model) {
		model.addAttribute("productC", productC);
		return "basic/product/productCForm";
	}

	/**
	 * 保存货物
	 */
	@RequiresPermissions("product:productC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ProductC productC) {
		productCService.save(productC);
		return renderResult(Global.TRUE, text("保存货物成功！"));
	}
	
	/**
	 * 停用货物
	 */
	@RequiresPermissions("product:productC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ProductC productC) {
		productC.setStatus(ProductC.STATUS_DISABLE);
		productCService.updateStatus(productC);
		return renderResult(Global.TRUE, text("停用货物成功"));
	}
	
	/**
	 * 启用货物
	 */
	@RequiresPermissions("product:productC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ProductC productC) {
		productC.setStatus(ProductC.STATUS_NORMAL);
		productCService.updateStatus(productC);
		return renderResult(Global.TRUE, text("启用货物成功"));
	}
	
	/**
	 * 删除货物
	 */
	@RequiresPermissions("product:productC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ProductC productC) {
		productCService.delete(productC);
		return renderResult(Global.TRUE, text("删除货物成功！"));
	}
	
}