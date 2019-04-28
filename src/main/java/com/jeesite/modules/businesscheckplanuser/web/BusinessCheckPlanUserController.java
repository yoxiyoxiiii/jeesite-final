/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplanuser.web;

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
import com.jeesite.modules.businesscheckplanuser.entity.BusinessCheckPlanUser;
import com.jeesite.modules.businesscheckplanuser.service.BusinessCheckPlanUserService;

/**
 * 考核名单Controller
 * @author 考核名单
 * @version 2019-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/businesscheckplanuser/businessCheckPlanUser")
public class BusinessCheckPlanUserController extends BaseController {

	@Autowired
	private BusinessCheckPlanUserService businessCheckPlanUserService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessCheckPlanUser get(String id, boolean isNewRecord) {
		return businessCheckPlanUserService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessCheckPlanUser businessCheckPlanUser, Model model) {
		model.addAttribute("businessCheckPlanUser", businessCheckPlanUser);
		return "modules/businesscheckplanuser/businessCheckPlanUserList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessCheckPlanUser> listData(BusinessCheckPlanUser businessCheckPlanUser, HttpServletRequest request, HttpServletResponse response) {
		businessCheckPlanUser.setPage(new Page<>(request, response));
		Page<BusinessCheckPlanUser> page = businessCheckPlanUserService.findPage(businessCheckPlanUser);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:view")
	@RequestMapping(value = "form")
	public String form(BusinessCheckPlanUser businessCheckPlanUser, Model model) {
		model.addAttribute("businessCheckPlanUser", businessCheckPlanUser);
		return "modules/businesscheckplanuser/businessCheckPlanUserForm";
	}

	/**
	 * 保存考核名单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessCheckPlanUser businessCheckPlanUser) {
		businessCheckPlanUserService.save(businessCheckPlanUser);
		return renderResult(Global.TRUE, text("保存考核名单成功！"));
	}
	
	/**
	 * 删除考核名单
	 */
	@RequiresPermissions("businesscheckplanuser:businessCheckPlanUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessCheckPlanUser businessCheckPlanUser) {
		businessCheckPlanUserService.delete(businessCheckPlanUser);
		return renderResult(Global.TRUE, text("删除考核名单成功！"));
	}
	
}