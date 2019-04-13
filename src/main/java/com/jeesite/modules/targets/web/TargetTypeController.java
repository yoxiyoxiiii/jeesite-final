/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.targets.entity.TargetType;
import com.jeesite.modules.targets.service.TargetTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 指标分类Controller
 * @author 指标分类
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/targets/targetType")
public class TargetTypeController extends BaseController {

	@Autowired
	private TargetTypeService targetTypeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TargetType get(String id, boolean isNewRecord) {
		return targetTypeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("targets:targetType:view")
	@RequestMapping(value = {"list", ""})
	public String list(TargetType targetType, Model model) {
		model.addAttribute("targetType", targetType);
		return "modules/targets/targetTypeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("targets:targetType:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TargetType> listData(TargetType targetType, HttpServletRequest request, HttpServletResponse response) {
		targetType.setPage(new Page<>(request, response));
		Page<TargetType> page = targetTypeService.findPage(targetType);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("targets:targetType:view")
	@RequestMapping(value = "form")
	public String form(TargetType targetType, Model model) {
		model.addAttribute("targetType", targetType);
		return "modules/targets/targetTypeForm";
	}

	/**
	 * 保存指标分类
	 */
	@RequiresPermissions("targets:targetType:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TargetType targetType) {
		targetTypeService.save(targetType);
		return renderResult(Global.TRUE, text("保存指标分类成功！"));
	}
	
	/**
	 * 删除指标分类
	 */
	@RequiresPermissions("targets:targetType:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TargetType targetType) {
		targetTypeService.delete(targetType);
		return renderResult(Global.TRUE, text("删除指标分类成功！"));
	}
	
}