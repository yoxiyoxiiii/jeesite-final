/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.task.entity.Task;
import com.jeesite.modules.task.service.TaskService;
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
 * 任务Controller
 * @author 任务
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/task/task")
public class TaskController extends BaseController {

	@Autowired
	private TaskService taskService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Task get(String id, boolean isNewRecord) {
		return taskService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("task:task:view")
	@RequestMapping(value = {"list", ""})
	public String list(Task task, Model model) {
		model.addAttribute("task", task);
		return "modules/task/taskList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("task:task:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Task> listData(Task task, HttpServletRequest request, HttpServletResponse response) {
		task.setPage(new Page<>(request, response));
		Page<Task> page = taskService.findPage(task);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("task:task:view")
	@RequestMapping(value = "form")
	public String form(Task task, Model model) {
		model.addAttribute("task", task);
		return "modules/task/taskForm";
	}

	/**
	 * 保存任务
	 */
	@RequiresPermissions("task:task:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Task task) {
		taskService.save(task);
		return renderResult(Global.TRUE, text("保存任务成功！"));
	}
	
	/**
	 * 删除任务
	 */
	@RequiresPermissions("task:task:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Task task) {
		taskService.delete(task);
		return renderResult(Global.TRUE, text("删除任务成功！"));
	}
	
}