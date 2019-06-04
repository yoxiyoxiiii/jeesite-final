/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.appeal.web;

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
import com.jeesite.modules.appeal.entity.Appeal;
import com.jeesite.modules.appeal.service.AppealService;

/**
 * 申诉Controller
 * @author sanye
 * @version 2019-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/appeal/appeal")
public class AppealController extends BaseController {

	@Autowired
	private AppealService appealService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Appeal get(String id, boolean isNewRecord) {
		return appealService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("appeal:appeal:view")
	@RequestMapping(value = {"list", ""})
	public String list(Appeal appeal, Model model) {
		model.addAttribute("appeal", appeal);
		return "modules/appeal/appealList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("appeal:appeal:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Appeal> listData(Appeal appeal, HttpServletRequest request, HttpServletResponse response) {
		appeal.setPage(new Page<>(request, response));
		Page<Appeal> page = appealService.findPage(appeal);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("appeal:appeal:view")
	@RequestMapping(value = "form")
	public String form(Appeal appeal, Model model) {
		model.addAttribute("appeal", appeal);
		return "modules/appeal/appealForm";
	}

	/**
	 * 保存申诉
	 */
	@RequiresPermissions("appeal:appeal:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Appeal appeal) {
		appealService.save(appeal);
		return renderResult(Global.TRUE, text("保存申诉成功！"));
	}
	
	/**
	 * 停用申诉
	 */
	@RequiresPermissions("appeal:appeal:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Appeal appeal) {
		appeal.setStatus(Appeal.STATUS_DISABLE);
		appealService.updateStatus(appeal);
		return renderResult(Global.TRUE, text("停用申诉成功"));
	}
	
	/**
	 * 启用申诉
	 */
	@RequiresPermissions("appeal:appeal:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Appeal appeal) {
		appeal.setStatus(Appeal.STATUS_NORMAL);
		appealService.updateStatus(appeal);
		return renderResult(Global.TRUE, text("启用申诉成功"));
	}
	
	/**
	 * 删除申诉
	 */
	@RequiresPermissions("appeal:appeal:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Appeal appeal) {
		appealService.delete(appeal);
		return renderResult(Global.TRUE, text("删除申诉成功！"));
	}
	
}