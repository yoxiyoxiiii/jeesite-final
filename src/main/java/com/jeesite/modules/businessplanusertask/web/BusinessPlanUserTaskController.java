/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessplanusertask.web;

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
import com.jeesite.modules.businessplanusertask.entity.BusinessPlanUserTask;
import com.jeesite.modules.businessplanusertask.service.BusinessPlanUserTaskService;

/**
 * 目标生成的任务Controller
 * @author yj
 * @version 2019-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/businessplanusertask/businessPlanUserTask")
public class BusinessPlanUserTaskController extends BaseController {

	@Autowired
	private BusinessPlanUserTaskService businessPlanUserTaskService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessPlanUserTask get(String id, boolean isNewRecord) {
		return businessPlanUserTaskService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businessplanusertask:businessPlanUserTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessPlanUserTask businessPlanUserTask, Model model) {
		model.addAttribute("businessPlanUserTask", businessPlanUserTask);
		return "modules/businessplanusertask/businessPlanUserTaskList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businessplanusertask:businessPlanUserTask:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessPlanUserTask> listData(BusinessPlanUserTask businessPlanUserTask, HttpServletRequest request, HttpServletResponse response) {
		businessPlanUserTask.setPage(new Page<>(request, response));
		Page<BusinessPlanUserTask> page = businessPlanUserTaskService.findPage(businessPlanUserTask);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businessplanusertask:businessPlanUserTask:view")
	@RequestMapping(value = "form")
	public String form(BusinessPlanUserTask businessPlanUserTask, Model model) {
		model.addAttribute("businessPlanUserTask", businessPlanUserTask);
		return "modules/businessplanusertask/businessPlanUserTaskForm";
	}

	/**
	 * 保存目标生成的任务
	 */
	@RequiresPermissions("businessplanusertask:businessPlanUserTask:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessPlanUserTask businessPlanUserTask) {
		businessPlanUserTaskService.save(businessPlanUserTask);
		return renderResult(Global.TRUE, text("保存目标生成的任务成功！"));
	}
	
	/**
	 * 删除目标生成的任务
	 */
	@RequiresPermissions("businessplanusertask:businessPlanUserTask:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessPlanUserTask businessPlanUserTask) {
		businessPlanUserTaskService.delete(businessPlanUserTask);
		return renderResult(Global.TRUE, text("删除目标生成的任务成功！"));
	}
	
}