/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.basic.factory.web;

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
import com.jeesite.modules.basic.factory.entity.FactoryC;
import com.jeesite.modules.basic.factory.service.FactoryCService;

/**
 * 厂家管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/factory/factoryC")
public class FactoryCController extends BaseController {

	@Autowired
	private FactoryCService factoryCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public FactoryC get(String id, boolean isNewRecord) {
		return factoryCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("factory:factoryC:view")
	@RequestMapping(value = {"list", ""})
	public String list(FactoryC factoryC, Model model) {
		model.addAttribute("factoryC", factoryC);
		return "basic/factory/factoryCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("factory:factoryC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<FactoryC> listData(FactoryC factoryC, HttpServletRequest request, HttpServletResponse response) {
		factoryC.setPage(new Page<>(request, response));
		Page<FactoryC> page = factoryCService.findPage(factoryC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("factory:factoryC:view")
	@RequestMapping(value = "form")
	public String form(FactoryC factoryC, Model model) {
		model.addAttribute("factoryC", factoryC);
		return "basic/factory/factoryCForm";
	}

	/**
	 * 保存生产厂家
	 */
	@RequiresPermissions("factory:factoryC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated FactoryC factoryC) {
		factoryCService.save(factoryC);
		return renderResult(Global.TRUE, text("保存生产厂家成功！"));
	}
	
	/**
	 * 停用生产厂家
	 */
	@RequiresPermissions("factory:factoryC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(FactoryC factoryC) {
		factoryC.setStatus(FactoryC.STATUS_DISABLE);
		factoryCService.updateStatus(factoryC);
		return renderResult(Global.TRUE, text("停用生产厂家成功"));
	}
	
	/**
	 * 启用生产厂家
	 */
	@RequiresPermissions("factory:factoryC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(FactoryC factoryC) {
		factoryC.setStatus(FactoryC.STATUS_NORMAL);
		factoryCService.updateStatus(factoryC);
		return renderResult(Global.TRUE, text("启用生产厂家成功"));
	}
	
	/**
	 * 删除生产厂家
	 */
	@RequiresPermissions("factory:factoryC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(FactoryC factoryC) {
		factoryCService.delete(factoryC);
		return renderResult(Global.TRUE, text("删除生产厂家成功！"));
	}
	
}