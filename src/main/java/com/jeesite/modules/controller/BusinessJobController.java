/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.BusinessJob;
import com.jeesite.modules.service.BusinessJobService;
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
 * 定时任务Controller
 * @author BusinessJob
 * @version 2019-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/businessjob/businessJob")
public class BusinessJobController extends BaseController {

	@Autowired
	private BusinessJobService businessJobService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessJob get(String id, boolean isNewRecord) {
		return businessJobService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businessjob:businessJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessJob businessJob, Model model) {
		model.addAttribute("businessJob", businessJob);
		return "modules/businessjob/businessJobList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businessjob:businessJob:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessJob> listData(BusinessJob businessJob, HttpServletRequest request, HttpServletResponse response) {
		businessJob.setPage(new Page<>(request, response));
		Page<BusinessJob> page = businessJobService.findPage(businessJob);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businessjob:businessJob:view")
	@RequestMapping(value = "form")
	public String form(BusinessJob businessJob, Model model) {
		model.addAttribute("businessJob", businessJob);
		return "modules/businessjob/businessJobForm";
	}

	/**
	 * 保存定时任务
	 */
	@RequiresPermissions("businessjob:businessJob:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessJob businessJob) {
		businessJobService.save(businessJob);
		return renderResult(Global.TRUE, text("保存定时任务成功！"));
	}
	
	/**
	 * 删除定时任务
	 */
	@RequiresPermissions("businessjob:businessJob:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessJob businessJob) {
		businessJobService.delete(businessJob);
		return renderResult(Global.TRUE, text("删除定时任务成功！"));
	}
	
}