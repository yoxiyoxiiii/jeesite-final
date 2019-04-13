/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plan.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.plan.entity.Plan;
import com.jeesite.modules.plan.service.PlanService;
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
 * 考核计划Controller
 * @author 考核计划
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/plan/plan")
public class PlanController extends BaseController {

	@Autowired
	private PlanService planService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Plan get(String id, boolean isNewRecord) {
		return planService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("plan:plan:view")
	@RequestMapping(value = {"list", ""})
	public String list(Plan plan, Model model) {
		model.addAttribute("plan", plan);
		return "modules/plan/planList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("plan:plan:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Plan> listData(Plan plan, HttpServletRequest request, HttpServletResponse response) {
		plan.setPage(new Page<>(request, response));
		Page<Plan> page = planService.findPage(plan);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("plan:plan:view")
	@RequestMapping(value = "form")
	public String form(Plan plan, Model model) {
		model.addAttribute("plan", plan);
		return "modules/plan/planForm";
	}

	/**
	 * 保存考核计划
	 */
	@RequiresPermissions("plan:plan:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Plan plan) {
		planService.save(plan);
		return renderResult(Global.TRUE, text("保存考核计划成功！"));
	}
	
	/**
	 * 删除考核计划
	 */
	@RequiresPermissions("plan:plan:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Plan plan) {
		planService.delete(plan);
		return renderResult(Global.TRUE, text("删除考核计划成功！"));
	}
	
}