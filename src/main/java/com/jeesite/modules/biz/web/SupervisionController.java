/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.biz.web;

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
import com.jeesite.modules.biz.entity.Supervision;
import com.jeesite.modules.biz.service.SupervisionService;

/**
 * 督查督办Controller
 * @author sanye
 * @version 2019-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/supervision")
public class SupervisionController extends BaseController {

	@Autowired
	private SupervisionService supervisionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Supervision get(String id, boolean isNewRecord) {
		return supervisionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("biz:supervision:view")
	@RequestMapping(value = {"list", ""})
	public String list(Supervision supervision, Model model) {
		model.addAttribute("supervision", supervision);
		return "modules/biz/supervisionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("biz:supervision:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Supervision> listData(Supervision supervision, HttpServletRequest request, HttpServletResponse response) {
		supervision.setPage(new Page<>(request, response));
		Page<Supervision> page = supervisionService.findPage(supervision);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("biz:supervision:view")
	@RequestMapping(value = "form")
	public String form(Supervision supervision, Model model) {
		model.addAttribute("supervision", supervision);
		return "modules/biz/supervisionForm";
	}

	/**
	 * 保存督查督办
	 */
	@RequiresPermissions("biz:supervision:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Supervision supervision) {
		supervisionService.save(supervision);
		return renderResult(Global.TRUE, text("保存督查督办成功！"));
	}
	
	/**
	 * 删除督查督办
	 */
	@RequiresPermissions("biz:supervision:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Supervision supervision) {
		supervisionService.delete(supervision);
		return renderResult(Global.TRUE, text("删除督查督办成功！"));
	}
	
}