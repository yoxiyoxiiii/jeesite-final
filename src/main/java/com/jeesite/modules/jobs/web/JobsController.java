/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jobs.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.jobs.entity.Jobs;
import com.jeesite.modules.jobs.service.JobsService;
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
 * 目标对应的定时任务（定时采集/定时考核）Controller
 * @author 目标对应的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/jobs/jobs")
public class JobsController extends BaseController {

	@Autowired
	private JobsService jobsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Jobs get(String id, boolean isNewRecord) {
		return jobsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("jobs:jobs:view")
	@RequestMapping(value = {"list", ""})
	public String list(Jobs jobs, Model model) {
		model.addAttribute("jobs", jobs);
		return "modules/jobs/jobsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("jobs:jobs:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Jobs> listData(Jobs jobs, HttpServletRequest request, HttpServletResponse response) {
		jobs.setPage(new Page<>(request, response));
		Page<Jobs> page = jobsService.findPage(jobs);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("jobs:jobs:view")
	@RequestMapping(value = "form")
	public String form(Jobs jobs, Model model) {
		model.addAttribute("jobs", jobs);
		return "modules/jobs/jobsForm";
	}

	/**
	 * 保存目标对应的定时任务（定时采集/定时考核）
	 */
	@RequiresPermissions("jobs:jobs:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Jobs jobs) {
		jobsService.save(jobs);
		return renderResult(Global.TRUE, text("保存目标对应的定时任务（定时采集/定时考核）成功！"));
	}
	
	/**
	 * 删除目标对应的定时任务（定时采集/定时考核）
	 */
	@RequiresPermissions("jobs:jobs:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Jobs jobs) {
		jobsService.delete(jobs);
		return renderResult(Global.TRUE, text("删除目标对应的定时任务（定时采集/定时考核）成功！"));
	}
	
}