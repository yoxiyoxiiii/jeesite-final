/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.task.entity.TaskData;
import com.jeesite.modules.task.service.TaskDataService;
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
 * 上报的具体数据Controller
 * @author 上报的具体数据
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/task/taskData")
public class TaskDataController extends BaseController {

	@Autowired
	private TaskDataService taskDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TaskData get(String id, boolean isNewRecord) {
		return taskDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("task:taskData:view")
	@RequestMapping(value = {"list", ""})
	public String list(TaskData taskData, Model model) {
		model.addAttribute("taskData", taskData);
		return "modules/task/taskDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("task:taskData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TaskData> listData(TaskData taskData, HttpServletRequest request, HttpServletResponse response) {
		taskData.setPage(new Page<>(request, response));
		Page<TaskData> page = taskDataService.findPage(taskData);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("task:taskData:view")
	@RequestMapping(value = "form")
	public String form(TaskData taskData, Model model) {
		model.addAttribute("taskData", taskData);
		return "modules/task/taskDataForm";
	}

	/**
	 * 保存上报的具体数据
	 */
	@RequiresPermissions("task:taskData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TaskData taskData) {
		taskDataService.save(taskData);
		return renderResult(Global.TRUE, text("保存上报的具体数据成功！"));
	}
	
	/**
	 * 删除上报的具体数据
	 */
	@RequiresPermissions("task:taskData:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TaskData taskData) {
		taskDataService.delete(taskData);
		return renderResult(Global.TRUE, text("删除上报的具体数据成功！"));
	}
	
}