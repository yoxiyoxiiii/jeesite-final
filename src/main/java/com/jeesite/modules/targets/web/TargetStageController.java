/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.targets.entity.TargetStage;
import com.jeesite.modules.targets.service.TargetStageService;
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
 * 阶段目标Controller
 * @author 阶段目标
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/targets/targetStage")
public class TargetStageController extends BaseController {

	@Autowired
	private TargetStageService targetStageService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TargetStage get(String id, boolean isNewRecord) {
		return targetStageService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("targets:targetStage:view")
	@RequestMapping(value = {"list", ""})
	public String list(TargetStage targetStage, Model model) {
		model.addAttribute("targetStage", targetStage);
		return "modules/targets/targetStageList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("targets:targetStage:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TargetStage> listData(TargetStage targetStage, HttpServletRequest request, HttpServletResponse response) {
		targetStage.setPage(new Page<>(request, response));
		Page<TargetStage> page = targetStageService.findPage(targetStage);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("targets:targetStage:view")
	@RequestMapping(value = "form")
	public String form(TargetStage targetStage, Model model) {
		model.addAttribute("targetStage", targetStage);
		return "modules/targets/targetStageForm";
	}

	/**
	 * 保存阶段目标
	 */
	@RequiresPermissions("targets:targetStage:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TargetStage targetStage) {
		targetStageService.save(targetStage);
		return renderResult(Global.TRUE, text("保存阶段目标成功！"));
	}
	
	/**
	 * 删除阶段目标
	 */
	@RequiresPermissions("targets:targetStage:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TargetStage targetStage) {
		targetStageService.delete(targetStage);
		return renderResult(Global.TRUE, text("删除阶段目标成功！"));
	}
	
}