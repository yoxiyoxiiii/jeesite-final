/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.businesscheckplan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.lang.DateUtils;
import com.jeesite.modules.msg.entity.MsgPush;
import com.jeesite.modules.msg.entity.content.PcMsgContent;
import com.jeesite.modules.msg.service.MsgPushService;
import com.jeesite.modules.msg.utils.MsgPushUtils;
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
import com.jeesite.modules.businesscheckplan.entity.BusinessCheckPlan;
import com.jeesite.modules.businesscheckplan.service.BusinessCheckPlanService;

/**
 * 考核计划Controller
 * @author BusinessCheckPlan
 * @version 2019-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/businesscheckplan/businessCheckPlan")
public class BusinessCheckPlanController extends BaseController {

	@Autowired
	private BusinessCheckPlanService businessCheckPlanService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessCheckPlan get(String id, boolean isNewRecord) {
		return businessCheckPlanService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessCheckPlan businessCheckPlan, Model model) {
		model.addAttribute("businessCheckPlan", businessCheckPlan);
		return "modules/businesscheckplan/businessCheckPlanList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessCheckPlan> listData(BusinessCheckPlan businessCheckPlan, HttpServletRequest request, HttpServletResponse response) {
		businessCheckPlan.setPage(new Page<>(request, response));
		Page<BusinessCheckPlan> page = businessCheckPlanService.findPage(businessCheckPlan);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:view")
	@RequestMapping(value = "form")
	public String form(BusinessCheckPlan businessCheckPlan, Model model) {
		model.addAttribute("businessCheckPlan", businessCheckPlan);
		return "modules/businesscheckplan/businessCheckPlanForm";
	}

	/**
	 * 保存考核计划
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessCheckPlan businessCheckPlan) {
		businessCheckPlanService.save(businessCheckPlan);
		return renderResult(Global.TRUE, text("保存考核计划成功！"));
	}
	
	/**
	 * 停用考核计划
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessCheckPlan businessCheckPlan) {
		businessCheckPlan.setStatus(BusinessCheckPlan.STATUS_DISABLE);
		businessCheckPlanService.updateStatus(businessCheckPlan);
		return renderResult(Global.TRUE, text("停用考核计划成功"));
	}
	
	/**
	 * 启用考核计划
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessCheckPlan businessCheckPlan) {
		businessCheckPlan.setStatus(BusinessCheckPlan.STATUS_NORMAL);
		businessCheckPlanService.updateStatus(businessCheckPlan);
		return renderResult(Global.TRUE, text("启用考核计划成功"));
	}
	
	/**
	 * 删除考核计划
	 */
	@RequiresPermissions("businesscheckplan:businessCheckPlan:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessCheckPlan businessCheckPlan) {
		businessCheckPlanService.delete(businessCheckPlan);
		return renderResult(Global.TRUE, text("删除考核计划成功！"));
	}


}