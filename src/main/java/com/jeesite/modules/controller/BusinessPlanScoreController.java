/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.controller;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.entity.BusinessPlanScore;
import com.jeesite.modules.service.BusinessPlanScoreService;
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
 * 考核评分Controller
 * @author BusinessPlanScore
 * @version 2019-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/businessplanscore/businessPlanScore")
public class BusinessPlanScoreController extends BaseController {

	@Autowired
	private BusinessPlanScoreService businessPlanScoreService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public BusinessPlanScore get(String id, boolean isNewRecord) {
		return businessPlanScoreService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessPlanScore businessPlanScore, Model model) {
		model.addAttribute("businessPlanScore", businessPlanScore);
		return "modules/businessplanscore/businessPlanScoreList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<BusinessPlanScore> listData(BusinessPlanScore businessPlanScore, HttpServletRequest request, HttpServletResponse response) {
		businessPlanScore.setPage(new Page<>(request, response));
		Page<BusinessPlanScore> page = businessPlanScoreService.findPage(businessPlanScore);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:view")
	@RequestMapping(value = "form")
	public String form(BusinessPlanScore businessPlanScore, Model model) {
		model.addAttribute("businessPlanScore", businessPlanScore);
		return "modules/businessplanscore/businessPlanScoreForm";
	}

	/**
	 * 保存考核评分
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated BusinessPlanScore businessPlanScore) {
		businessPlanScoreService.save(businessPlanScore);
		return renderResult(Global.TRUE, text("保存考核评分成功！"));
	}
	
	/**
	 * 停用考核评分
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(BusinessPlanScore businessPlanScore) {
		businessPlanScore.setStatus(BusinessPlanScore.STATUS_DISABLE);
		businessPlanScoreService.updateStatus(businessPlanScore);
		return renderResult(Global.TRUE, text("停用考核评分成功"));
	}
	
	/**
	 * 启用考核评分
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(BusinessPlanScore businessPlanScore) {
		businessPlanScore.setStatus(BusinessPlanScore.STATUS_NORMAL);
		businessPlanScoreService.updateStatus(businessPlanScore);
		return renderResult(Global.TRUE, text("启用考核评分成功"));
	}
	
	/**
	 * 删除考核评分
	 */
	@RequiresPermissions("businessplanscore:businessPlanScore:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(BusinessPlanScore businessPlanScore) {
		businessPlanScoreService.delete(businessPlanScore);
		return renderResult(Global.TRUE, text("删除考核评分成功！"));
	}
	
}