/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.targets.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.targets.entity.TargetJobs;
import com.jeesite.modules.targets.service.TargetJobsService;
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
 * 目的的定时任务（定时采集/定时考核）Controller
 * @author 目的的定时任务（定时采集/定时考核）
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/targets/targetJobs")
public class TargetJobsController extends BaseController {

	@Autowired
	private TargetJobsService targetJobsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TargetJobs get(String id, boolean isNewRecord) {
		return targetJobsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("targets:targetJobs:view")
	@RequestMapping(value = {"list", ""})
	public String list(TargetJobs targetJobs, Model model) {
		model.addAttribute("targetJobs", targetJobs);
		return "modules/targets/targetJobsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("targets:targetJobs:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TargetJobs> listData(TargetJobs targetJobs, HttpServletRequest request, HttpServletResponse response) {
		targetJobs.setPage(new Page<>(request, response));
		Page<TargetJobs> page = targetJobsService.findPage(targetJobs);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("targets:targetJobs:view")
	@RequestMapping(value = "form")
	public String form(TargetJobs targetJobs, Model model) {
		model.addAttribute("targetJobs", targetJobs);
		return "modules/targets/targetJobsForm";
	}

	/**
	 * 保存目的的定时任务（定时采集/定时考核）
	 */
	@RequiresPermissions("targets:targetJobs:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TargetJobs targetJobs) {
		targetJobsService.save(targetJobs);
		return renderResult(Global.TRUE, text("保存目的的定时任务（定时采集/定时考核）成功！"));
	}
	
	/**
	 * 删除目的的定时任务（定时采集/定时考核）
	 */
	@RequiresPermissions("targets:targetJobs:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TargetJobs targetJobs) {
		targetJobsService.delete(targetJobs);
		return renderResult(Global.TRUE, text("删除目的的定时任务（定时采集/定时考核）成功！"));
	}
	
}