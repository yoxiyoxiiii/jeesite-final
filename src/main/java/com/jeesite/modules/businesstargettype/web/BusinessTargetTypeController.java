/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesstargettype.web;

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
import com.jeesite.modules.businesstargettype.entity.BusinessTargetType;
import com.jeesite.modules.businesstargettype.service.BusinessTargetTypeService;

/**
 * 指标分类Controller
 * @author 指标分类
 * @version 2019-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/businesstargettype/businessTargetType")
public class BusinessTargetTypeController extends BaseController {

	@Autowired
	private BusinessTargetTypeService businessTargetTypeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessTargetType get(String id, boolean isNewRecord) {
		return businessTargetTypeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessTargetType businessTargetType, Model model) {
		model.addAttribute("businessTargetType", businessTargetType);
		return "modules/businesstargettype/businessTargetTypeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessTargetType> listData(BusinessTargetType businessTargetType, HttpServletRequest request, HttpServletResponse response) {
		businessTargetType.setPage(new Page<>(request, response));
		Page<BusinessTargetType> page = businessTargetTypeService.findPage(businessTargetType);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:view")
	@RequestMapping(value = "form")
	public String form(BusinessTargetType businessTargetType, Model model) {
		model.addAttribute("businessTargetType", businessTargetType);
		return "modules/businesstargettype/businessTargetTypeForm";
	}

	/**
	 * 保存指标分类
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessTargetType businessTargetType) {
		businessTargetTypeService.save(businessTargetType);
		return renderResult(Global.TRUE, text("保存指标分类成功！"));
	}
	
	/**
	 * 删除指标分类
	 */
	@RequiresPermissions("businesstargettype:businessTargetType:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessTargetType businessTargetType) {
		businessTargetTypeService.delete(businessTargetType);
		return renderResult(Global.TRUE, text("删除指标分类成功！"));
	}
	
}