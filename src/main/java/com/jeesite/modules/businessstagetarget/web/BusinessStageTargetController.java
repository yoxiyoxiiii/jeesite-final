/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businessstagetarget.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.businesstarget.entity.BusinessTarget;
import com.jeesite.modules.businesstarget.service.BusinessTargetService;
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
import com.jeesite.modules.businessstagetarget.entity.BusinessStageTarget;
import com.jeesite.modules.businessstagetarget.service.BusinessStageTargetService;

import java.util.List;

/**
 * 阶段目标Controller
 * @author 阶段目标
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/businessstagetarget/businessStageTarget")
public class BusinessStageTargetController extends BaseController {

	@Autowired
	private BusinessStageTargetService businessStageTargetService;

	@Autowired
	private BusinessTargetService businessTargetService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessStageTarget get(String id, boolean isNewRecord) {
		return businessStageTargetService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businessstagetarget:businessStageTarget:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessStageTarget businessStageTarget, Model model) {
		model.addAttribute("businessStageTarget", businessStageTarget);
		return "modules/businessstagetarget/businessStageTargetList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businessstagetarget:businessStageTarget:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessStageTarget> listData(BusinessStageTarget businessStageTarget, HttpServletRequest request, HttpServletResponse response) {
		businessStageTarget.setPage(new Page<>(request, response));
		Page<BusinessStageTarget> page = businessStageTargetService.findPage(businessStageTarget);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businessstagetarget:businessStageTarget:view")
	@RequestMapping(value = "form")
	public String form(BusinessStageTarget businessStageTarget, BusinessTarget businessTarget, Model model) {

		List<BusinessTarget> businessTargetList = businessTargetService.findList(businessTarget);
		if (businessTargetList.size() == 0 ) {
			businessTargetList = businessTargetService.findList();
		}

		model.addAttribute("businessStageTarget", businessStageTarget);
		model.addAttribute("businessTargetList", businessTargetList);

		return "modules/businessstagetarget/businessStageTargetForm";
	}

	/**
	 * 保存阶段目标
	 */
	@RequiresPermissions("businessstagetarget:businessStageTarget:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessStageTarget businessStageTarget) {
		businessStageTargetService.save(businessStageTarget);
		return renderResult(Global.TRUE, text("保存阶段目标成功！"));
	}
	
	/**
	 * 删除阶段目标
	 */
	@RequiresPermissions("businessstagetarget:businessStageTarget:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessStageTarget businessStageTarget) {
		businessStageTargetService.delete(businessStageTarget);
		return renderResult(Global.TRUE, text("删除阶段目标成功！"));
	}
	
}